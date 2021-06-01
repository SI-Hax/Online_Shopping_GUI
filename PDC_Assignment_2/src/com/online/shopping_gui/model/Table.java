package com.online.shopping_gui.model;

import javax.swing.table.AbstractTableModel;

/**
 * This base model class to be used for the Products JTable
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.1
 * @since 18/05/2021
 */
public class Table extends AbstractTableModel {

    public String[] column;

    private Object[][] data;

    public Table(Object[][] data, String[] column) {
        this.column = column;
        this.data = data;
    }
    
    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return this.column.length;
    }
    
    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public String getColumnName(int col) {
        return column[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0,c).getClass();
    }
    
    public void update(Object[][] data)
    {
        this.setData(data);
    }
}
