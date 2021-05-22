package com.online.shopping_gui.view;

import com.online.shopping_gui.model.Table;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.utilities.ProductFileIO;
import java.awt.Dimension;
import javax.swing.*;

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
        table = new Table(list.convertProductList(), new String[]{"ID", "Product Name", "Price", "Category", "Stock"});
 
        
        productTable = new JTable(table);
        productTable.setPreferredScrollableViewportSize(new Dimension(400, 400));
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(sorter);

        scrollPane = new JScrollPane(productTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        this.setPreferredSize(new Dimension(500, 500));
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ProductsView());
        frame.pack();
        frame.setVisible(true);
    }
}
