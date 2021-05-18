package com.online.shopping_gui.view;

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
        table = new Table();

        productTable = new JTable(list.convertProductList(), table.column);
        productTable.setPreferredScrollableViewportSize(new Dimension(400, 400));
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(sorter);

        scrollPane = new JScrollPane(productTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        this.setPreferredSize(new Dimension(500, 500));
    }
}