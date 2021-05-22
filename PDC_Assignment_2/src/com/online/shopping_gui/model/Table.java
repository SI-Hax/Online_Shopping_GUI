package com.online.shopping_gui.model;

import javax.swing.table.AbstractTableModel;

/**
 * This base model class to be used for the Products JTable
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.0
 * @since 18/05/2021
 */
public class Table extends AbstractTableModel {

//    public String[] column =  new String[]{"ID", "Product Name", "Price", "Category", "Stock"};
    public String[] column;

    private Object[][] data;

    public Table(Object[][] data, String[] column) {
        this.column = column;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public int getColumnCount() {
        return this.column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return column[col];
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if(col == 5){
            return true;
        } else {
            return false;
        }
    }
}
