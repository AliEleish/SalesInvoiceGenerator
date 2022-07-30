/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.util.ArrayList;
import java.util.Date;
import salesinvoicegenerator.view.SalesInvoicePreviewFrame;

/**
 *
 * @author DELL
 */
public class InvoiceHeader {

   
   private int invoiceNumber;
   private String customerName;
   private Date invoiceDate;
   private ArrayList<InvoiceLine> lines=null;
    
    public InvoiceHeader(int invoiceNumber, Date invoiceDate,  String customerName) {
          setInvoiceNumber(invoiceNumber);
          setCustomerName(customerName);
          setInvoiceDate(invoiceDate);
    }
    
    public String convertToCSV(){
     return invoiceNumber + "," + SalesInvoicePreviewFrame.simpleDateFormat.format(invoiceDate)  + "," + customerName;
    }
    

     public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if(lines == null)
        {
            lines = new ArrayList<InvoiceLine>();
        }
        return lines;
    
    }

    public double getInvoiceTotal()
    {
        double invoiceTotal= 0.0;
        for(int i=0 ; i < getLines().size() ;i++)
        {
            invoiceTotal += getLines().get(i).getItemTotal();
        }
        return invoiceTotal;
    }
    
    

   

    
    
}
