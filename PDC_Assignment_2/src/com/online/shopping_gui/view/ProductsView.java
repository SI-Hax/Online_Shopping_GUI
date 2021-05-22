package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
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
    
    private final String[] COLUMN_HEADERS = new String[]{"ID", "Product Name", "Price", "Category", "Stock"};
    private ProductList list;
    private Table table;
    private JTable productTable;
    private JScrollPane scrollPane;
    private JPanel tablePanel, lblPanel, btnPanel;
    private JLabel itemSelectedLbl, qtyLbl;
    private JTextField qtyTxtField;
    private JButton addToCartBtn;

    public ProductsView() {
        FlatLightLaf.install();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(900, 500));
        
        list = ProductFileIO.importProductData(); // Import products list.
        
        // Init table panel.
        tablePanel = new JPanel();
        boolean sorter = true;
        table = new Table(list.convertProductList(), COLUMN_HEADERS); // Init Table Model.
        productTable = new JTable(table);
        productTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(sorter);
        scrollPane = new JScrollPane(productTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tablePanel.add(scrollPane);
        
        // Init label panel to display item selected.
        lblPanel = new JPanel();
        itemSelectedLbl = new JLabel("Item Selected: ");
        lblPanel.add(itemSelectedLbl);
        
        // Init bottom panel to allow user to add an item with quantity specified to cart.
        btnPanel = new JPanel();
        qtyLbl = new JLabel("Quantity:");
        btnPanel.add(qtyLbl);
        qtyTxtField = new JTextField(5);
        btnPanel.add(qtyTxtField);
        addToCartBtn = new JButton("Add to Cart");
        btnPanel.add(addToCartBtn);
        
        // Add all panels to the parent container.
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(tablePanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(lblPanel);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnPanel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProductsView pv = new ProductsView();
        frame.add(pv);
        frame.pack();
        frame.setMinimumSize(pv.getPreferredSize()); // Specifies the min size so table's info wont be obscured.
        frame.setVisible(true);
    }
}
