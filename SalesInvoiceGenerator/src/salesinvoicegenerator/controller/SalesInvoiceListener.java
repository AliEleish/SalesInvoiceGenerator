/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoicegenerator.model.InvoiceHeader;
import salesinvoicegenerator.model.InvoiceHeaderTableModel;
import salesinvoicegenerator.model.InvoiceLine;
import salesinvoicegenerator.model.InvoiceLinesTableModel;
import salesinvoicegenerator.view.InvoiceHeaderDialog;
import salesinvoicegenerator.view.InvoiceLineDialog;
import salesinvoicegenerator.view.SalesInvoicePreviewFrame;

/**
 *
 * @author DELL
 */
public class SalesInvoiceListener implements ActionListener , ListSelectionListener {

   private SalesInvoicePreviewFrame salesInvoicePreviewFrame;
   private InvoiceHeaderDialog headerDialog;
   private InvoiceLineDialog   lineDialog;
    
    public SalesInvoiceListener(SalesInvoicePreviewFrame frame) {
        this.salesInvoicePreviewFrame = frame;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("SalesInvoiceButtonsListener");
       
        
       switch (e.getActionCommand()) {
           case "Create New Invoice":
               createNewInvoice();
               break;
           case "Delete Invoice":
               deleteInvoice();
               break;
           case "Create New Line":
              createNewLineFromCreateNewLineButton();
               break;
           case "Delete Line":
               deleteLine();
               break;
           case "Load File":
               loadFilesIntoSalesInvoiceFrame(null , null);
               break;
           case "Save File":
               saveFiles();
               break;
           case "New invoice dialog OK button":
               createInvoiceFromNewInvoiceDialog();
               break;
           case "New invoice dialog Cancel button":
               cancelNewInvoiceFromNewInvoiceDialog();
               break;
           case "New line dialog OK button":
               createNewLineFromNewLineDialog();
               break;
           case "New line dialog Cancel button":
               cancelNewLineFromNewLineDialog();
                   break;
           default:
               break;
       }
        
        
           
    }

    private void createNewInvoice() {
        headerDialog = new InvoiceHeaderDialog(salesInvoicePreviewFrame);
        headerDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int invoicesHeaderTableRowIndex = salesInvoicePreviewFrame.getInvoicesHeadersTable().getSelectedRow();
        if (invoicesHeaderTableRowIndex == -1)
        {
            JOptionPane.showMessageDialog(salesInvoicePreviewFrame, "Please Select an invoice from Invoices table or reload files if there's no more invoices"
                    , "", JOptionPane.ERROR_MESSAGE);
        }
        else if(invoicesHeaderTableRowIndex != -1)
        {
        salesInvoicePreviewFrame.getInvoices().remove(invoicesHeaderTableRowIndex);
        ((InvoiceHeaderTableModel)salesInvoicePreviewFrame.getInvoicesHeadersTable().getModel()).fireTableDataChanged();
     }}

    private void createNewLineFromCreateNewLineButton() {
       int selectedInv = salesInvoicePreviewFrame.getInvoicesHeadersTable().getSelectedRow();
       if( selectedInv != -1)
       {
        lineDialog = new InvoiceLineDialog(salesInvoicePreviewFrame);
        lineDialog.setVisible(true);
     }
       else{
        JOptionPane.showMessageDialog(salesInvoicePreviewFrame, "Please select an invoice from Invoices table", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
    private void deleteLine() {
      int invoiceLinesTableSelectedRow = salesInvoicePreviewFrame.getInvoiceLinesTable().getSelectedRow();
      if(invoiceLinesTableSelectedRow != -1)
      {
        int headerRow = salesInvoicePreviewFrame.getInvoicesHeadersTable().getSelectedRow();
        InvoiceLinesTableModel invoiceLinesTableModel = (InvoiceLinesTableModel)salesInvoicePreviewFrame.getInvoiceLinesTable().getModel();
        invoiceLinesTableModel.getInvoiceLines().remove(invoiceLinesTableSelectedRow);
        invoiceLinesTableModel.fireTableDataChanged();
        ((InvoiceHeaderTableModel)salesInvoicePreviewFrame.getInvoicesHeadersTable().getModel()).fireTableDataChanged();
        salesInvoicePreviewFrame.getInvoicesHeadersTable().setRowSelectionInterval(headerRow, headerRow);
      
      }
     }

    public void loadFilesIntoSalesInvoiceFrame(String InvoiceHeaderPath , String InvoiceLinePath) {
             File InvoiceHeaderFile = null , InvoiceLinesFile = null;
             int fileChooserResult;
             
          if(InvoiceHeaderPath == null && InvoiceLinePath == null)
          {
             
              JFileChooser fileChooser = new JFileChooser();
              fileChooserResult = fileChooser.showOpenDialog(salesInvoicePreviewFrame);
                if(fileChooserResult == JFileChooser.APPROVE_OPTION)
                {
                   InvoiceHeaderFile = fileChooser.getSelectedFile();
                   fileChooserResult = fileChooser.showOpenDialog(salesInvoicePreviewFrame);
                   if(fileChooserResult == JFileChooser.APPROVE_OPTION)
                     InvoiceLinesFile = fileChooser.getSelectedFile();
                }
              
          }
          else{
                InvoiceHeaderFile = new File(InvoiceHeaderPath);
                InvoiceLinesFile = new File(InvoiceLinePath);
          }
          
          if(InvoiceHeaderFile != null && InvoiceLinesFile !=null)
          {
              try{
               List<String> InvoiceHeaderFileLines = Files.lines(Paths.get(InvoiceHeaderFile.getAbsolutePath()))
                      .collect(Collectors.toList());
               List<String> InvoiceLinesFileLines = Files.lines(Paths.get(InvoiceLinesFile.getAbsolutePath()))
                      .collect(Collectors.toList());
                 // ArrayList<InvoiceHeader> invoices = new ArrayList<>();
               for(String InvoiceHeaderFileLine : InvoiceHeaderFileLines)
               {
                  String[] InvoiceHeaderLineParts = InvoiceHeaderFileLine.split(",");
                  String numberASString = InvoiceHeaderLineParts[0];
                  String dateASString = InvoiceHeaderLineParts[1];
                  String custName = InvoiceHeaderLineParts[2];
                  int invNumber = Integer.parseInt(numberASString);
                  Date invDate = SalesInvoicePreviewFrame.simpleDateFormat.parse(dateASString);
                  
                  InvoiceHeader invoice = new InvoiceHeader(invNumber, invDate, custName);
                  salesInvoicePreviewFrame.getInvoices().add(invoice);
               }
           
               for(String InvoiceLinesFileLine : InvoiceLinesFileLines)
                  {
                  String[] InvoiceLinesLineParts = InvoiceLinesFileLine.split(",");
                  int invoiceNumber = Integer.parseInt(InvoiceLinesLineParts[0]);
                  String itemName = InvoiceLinesLineParts[1];
                  double itemPrice = Double.parseDouble(InvoiceLinesLineParts[2]);
                  int itemCount = Integer.parseInt(InvoiceLinesLineParts[3]);
                  InvoiceHeader invoice = getInvoiceByInvoiceNumber(invoiceNumber);
                  InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, itemCount, invoice);
                  invoice.getLines().add(invoiceLine);
                  }
               salesInvoicePreviewFrame.getInvoicesHeadersTable().setModel(new InvoiceHeaderTableModel(salesInvoicePreviewFrame.getInvoices()));
               
                       }catch(Exception e)
          {
              e.printStackTrace();
          }
              
          }
     }

    private void saveFiles() {
        String invoicesData="";
        String linesData="";
       
        for(InvoiceHeader inv : salesInvoicePreviewFrame.getInvoices())
        {
           invoicesData += inv.convertToCSV();
           invoicesData += "\n";
           for(InvoiceLine invLine : inv.getLines())
           {
               linesData += invLine.convertToCSV();
               linesData += "\n";
           }
            }
        JFileChooser jfc = new JFileChooser();
        if(jfc.showSaveDialog(salesInvoicePreviewFrame) == JFileChooser.APPROVE_OPTION)
        {
          File invoiceHeaderFile = jfc.getSelectedFile();
          if(jfc.showSaveDialog(salesInvoicePreviewFrame) == JFileChooser.APPROVE_OPTION)
          {
           File invoiceLineFile = jfc.getSelectedFile(); 
           try{
           FileWriter headerFW = new FileWriter(invoiceHeaderFile); 
           headerFW.write(invoicesData);
           headerFW.flush();
           headerFW.close();
           FileWriter linesFW = new FileWriter(invoiceLineFile);
           linesFW.write(linesData);
           linesFW.flush();
           linesFW.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(salesInvoicePreviewFrame, "Error while saving data", "Error", JOptionPane.ERROR_MESSAGE);
        }
          }
       }
    }     
    
    private InvoiceHeader getInvoiceByInvoiceNumber(int invoicenumber)
    {
        for(InvoiceHeader invoice : salesInvoicePreviewFrame.getInvoices())
        {
            if(invoicenumber == invoice.getInvoiceNumber())
                return invoice;
                }
        return null;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
      int rowNumber = salesInvoicePreviewFrame.getInvoicesHeadersTable().getSelectedRow();
      
      if(rowNumber > -1 && rowNumber < salesInvoicePreviewFrame.getInvoices().size())
      {
      InvoiceHeader invoice = salesInvoicePreviewFrame.getInvoices().get(rowNumber);
      salesInvoicePreviewFrame.getInvoiceNumLblVal().setText(String.valueOf(invoice.getInvoiceNumber()));
      salesInvoicePreviewFrame.getInvoiceDateLblVal().setText(SalesInvoicePreviewFrame.simpleDateFormat.format(invoice.getInvoiceDate()));
      salesInvoicePreviewFrame.getCustNameLblVal().setText(invoice.getCustomerName());
      salesInvoicePreviewFrame.getInvoiceTotalLblVal().setText(String.valueOf(invoice.getInvoiceTotal()));
      
      List<InvoiceLine> invoiceLines = invoice.getLines();
      salesInvoicePreviewFrame.getInvoiceLinesTable().setModel(new InvoiceLinesTableModel(invoiceLines));
    }else{
      salesInvoicePreviewFrame.getInvoiceNumLblVal().setText("");
      salesInvoicePreviewFrame.getInvoiceDateLblVal().setText("");
      salesInvoicePreviewFrame.getCustNameLblVal().setText("");
      salesInvoicePreviewFrame.getInvoiceTotalLblVal().setText("");
      
      salesInvoicePreviewFrame.getInvoiceLinesTable().setModel(new InvoiceLinesTableModel(new ArrayList<InvoiceLine>()));   
      }
    }

    private void createInvoiceFromNewInvoiceDialog() {
     String customerName = headerDialog.getCustNameField().getText();
     String Date = headerDialog.getInvDateField().getText();
     headerDialog.setVisible(false);
     headerDialog.dispose();
     try{
         Date invoiceDate = salesInvoicePreviewFrame.simpleDateFormat.parse(Date);
         InvoiceHeader invoice = new InvoiceHeader(getNextInvoiceNum() , invoiceDate , customerName);
         salesInvoicePreviewFrame.getInvoices().add(invoice); 
         ((InvoiceHeaderTableModel)salesInvoicePreviewFrame.getInvoicesHeadersTable().getModel()).fireTableDataChanged();
     }catch(ParseException pe)
     {
      JOptionPane.showMessageDialog(headerDialog, "Please enter valid date format", "Error date format", JOptionPane.ERROR_MESSAGE);
     }
     
    
    }

    private int getNextInvoiceNum()
    {
            int num =1;        
            List<InvoiceHeader> invoices = salesInvoicePreviewFrame.getInvoices();
            for(InvoiceHeader inv : invoices)
            {
              if(inv.getInvoiceNumber() > num)
              {
                 num = inv.getInvoiceNumber();
              }
            }
    return num + 1;
    }
    private void cancelNewInvoiceFromNewInvoiceDialog() {
     headerDialog.setVisible(false);
     headerDialog.dispose();    
    }
    private void createNewLineFromNewLineDialog() {
        int selectedInvoice = salesInvoicePreviewFrame.getInvoicesHeadersTable().getSelectedRow();
        if(selectedInvoice != -1){
         InvoiceHeader invoice = salesInvoicePreviewFrame.getInvoices().get(selectedInvoice);
         String itemName =  lineDialog.getItemNameTxtField().getText();
         double itemPrice = Double.parseDouble(lineDialog.getItemPriceTxtField().getText());
         int itemCount =  Integer.parseInt(lineDialog.getItemCountTxtField().getText());
         lineDialog.setVisible(false);
         lineDialog.dispose();
         InvoiceLine invLine = new InvoiceLine(itemName, itemPrice, itemCount, invoice);
         invoice.getLines().add(invLine);
         ((InvoiceLinesTableModel)salesInvoicePreviewFrame.getInvoiceLinesTable().getModel()).fireTableDataChanged();
         ((InvoiceHeaderTableModel)salesInvoicePreviewFrame.getInvoicesHeadersTable().getModel()).fireTableDataChanged();
         salesInvoicePreviewFrame.getInvoicesHeadersTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        }
    }

    private void cancelNewLineFromNewLineDialog() {
     lineDialog.setVisible(false);
     lineDialog.dispose();      
    }

}
