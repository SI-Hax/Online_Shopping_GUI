package test.view;

import test.model.ProductList;
import test.utilities.ProductFileIO;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

/**
 * This class contains the Products Table
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 17/05/2021
 */
public class ProductsView extends JPanel {

    private JTable productTable;
    private JScrollPane scrollPane;
    private Table table;
    ProductList list;

    public ProductsView() {
        list = ProductFileIO.importProductData();
        boolean sorter = true;
        table = new Table();

        productTable = new JTable(list.convertProductList(), table.column);
        productTable.setPreferredScrollableViewportSize(new Dimension(400, 400));
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(sorter);

        scrollPane = new JScrollPane(productTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        this.setPreferredSize(new Dimension(500, 500));

        // Table listener
        // maybe we can use this to change value of the database
        productTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println("Row: " + productTable.getSelectedRow() + " | Column: " + productTable.getSelectedColumn() +
                        " | Current Value: " + productTable.getValueAt(productTable.getEditingRow(),productTable.getSelectedColumn()).toString());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.add(new ProductsView());
        frame.setVisible(true);
    }
}
