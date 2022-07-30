/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import salesinvoicegenerator.view.SalesInvoicePreviewFrame;

/**
 *
 * @author DELL
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{

    String[] InvoiceHeaderTableCols = {"Invoice Number" , "Invoice Date" , "Customer Name" , "Invoice Total"};
    List<InvoiceHeader> invoices;

    
    public InvoiceHeaderTableModel(List<InvoiceHeader> invoices)
    {
       this.invoices = invoices;
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
       }

    @Override
    public int getColumnCount() {
      return InvoiceHeaderTableCols.length;    
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
          return InvoiceHeaderTableCols[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      InvoiceHeader inv = invoices.get(rowIndex);
      switch(columnIndex)
      {
          case 0: return inv.getInvoiceNumber();
          case 1: return SalesInvoicePreviewFrame.simpleDateFormat.format(inv.getInvoiceDate());
          case 2: return inv.getCustomerName(); 
          case 3: return  inv.getInvoiceTotal();
      }
      return "";
       }
    
}
