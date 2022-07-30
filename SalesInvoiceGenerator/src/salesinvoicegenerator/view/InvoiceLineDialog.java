/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class InvoiceLineDialog extends JDialog{
    private JLabel itemNameLbl;
    private JLabel itemPriceLbl;
    private JLabel itemCountLbl;
    private JTextField itemNameTxtField;
    private JTextField itemPriceTxtField;
    private JTextField itemCountTxtField;
    private JButton newLineOKBtn;
    private JButton newLineCancelBtn;
    
    public InvoiceLineDialog(SalesInvoicePreviewFrame frame)
    {
      itemNameLbl = new JLabel("Item Name");
      itemPriceLbl =new JLabel("Item Price");
      itemCountLbl =new JLabel("Item Count");
      itemNameTxtField = new JTextField(20);
      itemPriceTxtField = new JTextField(20);
      itemCountTxtField = new JTextField(20);
      newLineOKBtn = new JButton("OK");
      newLineCancelBtn = new JButton("Cancel");
      
      newLineOKBtn.addActionListener(frame.getSalesInvoiceListener());
      newLineCancelBtn.addActionListener(frame.getSalesInvoiceListener());
      
      newLineOKBtn.setActionCommand("New line dialog OK button");
      newLineCancelBtn.setActionCommand("New line dialog Cancel button");
        setLayout(new GridLayout(5, 5));
        
        add(itemNameLbl);
        add(itemNameTxtField);
        add(itemPriceLbl);
        add(itemPriceTxtField);
        add(itemCountLbl);
        add(itemCountTxtField);
        add(newLineOKBtn);
        add(newLineCancelBtn);
        setModal(true);
        pack();
        
    }

    public JTextField getItemNameTxtField() {
        return itemNameTxtField;
    }

    public JTextField getItemPriceTxtField() {
        return itemPriceTxtField;
    }

    public JTextField getItemCountTxtField() {
        return itemCountTxtField;
    }
    
    
}
