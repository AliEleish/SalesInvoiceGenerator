/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.view;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class InvoiceHeaderDialog extends JDialog {
    private JLabel     invoiceDateLbl;
    private JTextField invoiceDateTxtField;
    private JLabel     custNameLbl;
    private JTextField custNameTxtField;
    private JButton    newInvoiceOKBtn;
    private JButton    newInvoiceCancelBtn;
    
    public InvoiceHeaderDialog(SalesInvoicePreviewFrame frame)
    {
        invoiceDateLbl = new JLabel("Invoice Date");
        custNameLbl    = new JLabel("Customer Name");
        invoiceDateTxtField = new JTextField(20);
        custNameTxtField = new JTextField(20);
        newInvoiceOKBtn = new JButton("OK");
        newInvoiceCancelBtn = new JButton("Cancel");
        
        newInvoiceOKBtn.setActionCommand("New invoice dialog OK button");
        newInvoiceCancelBtn.setActionCommand("New invoice dialog Cancel button");
        
         newInvoiceOKBtn.addActionListener(frame.getSalesInvoiceListener());
        newInvoiceCancelBtn.addActionListener(frame.getSalesInvoiceListener());
        setLayout(new GridLayout(3, 2));
       
        add(invoiceDateLbl);
        add(invoiceDateTxtField);
        add(custNameLbl);
        add(custNameTxtField);
        add(newInvoiceOKBtn);
        add(newInvoiceCancelBtn);
        setModal(true);
         pack();
        
     }
    
    public JTextField getCustNameField()
    {
      return custNameTxtField;
    }
    
    public JTextField getInvDateField()
    {
      return invoiceDateTxtField;
    } 
}
