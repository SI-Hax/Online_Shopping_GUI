package test.view;

import javax.swing.table.AbstractTableModel;

public class Table extends AbstractTableModel {

    protected String[] column =  new String[]{"ID", "Product Name", "Price", "Category", "Stock"};
    private Object[][] data;

    public Table() {
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
}
