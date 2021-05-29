package com.online.shopping_gui.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.online.shopping_gui.utilities.ProductFileIO;
import com.online.shopping_gui.model.ProductList;
import com.online.shopping_gui.model.Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/**
 * This class contains the Products Table.
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.2
 * @since 25/05/2021
 */
public class ProductsTableView extends JPanel {
    private final String[] COLUMN_HEADERS = new String[]{"ID", "Product Name", "Price", "Category", "Stock"};
    private Table table;
    private JTable productTable;
    private JScrollPane scrollPane;
    
    public ProductsTableView() {
        FlatLightLaf.install();
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(710, 350));
        this.setBackground(Color.WHITE);
        ProductList pList = ProductFileIO.importProductData();
        
        boolean sorter = true;
        table = new Table(pList.convertProductList(), COLUMN_HEADERS); // Init Table Model.
        productTable = new JTable(table);
        productTable.setPreferredScrollableViewportSize(new Dimension(700, 300));
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(sorter);
        scrollPane = new JScrollPane(productTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allows user to select only one row at a time.
        productTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if(productTable.getSelectedRow() >= 0) {
                    String selectedCellValue = productTable.getValueAt(productTable.getSelectedRow() , 1).toString();
                    System.out.println(selectedCellValue);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
//                itemSelectedLbl.setText("Item Selected: " + productTable.getValueAt(productTable.getSelectedRow() , 1));
            }
        });
        
        this.add(scrollPane);
    }

    public String[] getCOLUMN_HEADERS() {
        return COLUMN_HEADERS;
    }
    
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    public JTable getProductTable() {
        return productTable;
    }

    public void setProductTable(JTable productTable) {
        this.productTable = productTable;
    }
    
    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
