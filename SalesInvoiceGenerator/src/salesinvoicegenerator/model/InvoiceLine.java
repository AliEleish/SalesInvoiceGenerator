/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class InvoiceLine {

  
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private InvoiceHeader invoice;
    
    public InvoiceLine(String itemname , double itemprice , int itemcount , InvoiceHeader inv)
    {
        setItemName(itemname);
        setItemPrice(itemprice);
        setItemCount(itemcount);
        setInvoice(inv);
    }
    
    public String convertToCSV(){
        return invoice.getInvoiceNumber() + "," + itemName + "," + itemPrice + "," + itemCount;
    }
    
     


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getItemTotal() {
        return  getItemCount() * getItemPrice();
        
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

  
    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }
    
}
