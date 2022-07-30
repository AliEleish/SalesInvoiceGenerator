/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class InvoiceLinesTableModel extends AbstractTableModel  {
    
    String[] InvoiceLinesTableColumns = {"Item Name" , "Item Price" , "Item Count" , "Item Total"};
    List<InvoiceLine> InvoiceLines;
    
public InvoiceLinesTableModel(List<InvoiceLine> invLines)
{
  this.InvoiceLines = invLines;
}

    @Override
    public int getRowCount() {
        return InvoiceLines.size();
       }

    @Override
    public int getColumnCount() {
        return InvoiceLinesTableColumns.length; 
       }

    @Override
    public String getColumnName(int columnIndex)
    {
       return InvoiceLinesTableColumns[columnIndex];
     }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invLine = InvoiceLines.get(rowIndex);
        switch(columnIndex)
          {
              case 0: return invLine.getItemName();
              case 1: return invLine.getItemPrice();
              case 2: return invLine.getItemCount();
              case 3: return invLine.getItemTotal();
                }
        return " ";
       }

    public List<InvoiceLine> getInvoiceLines() {
        return InvoiceLines;
    }
    
}
