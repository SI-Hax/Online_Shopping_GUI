package com.online.shopping_gui.utilities;

import com.online.shopping_gui.enumerations.Category;
import com.online.shopping_gui.model.Product;
import com.online.shopping_gui.model.ProductList;
import java.io.*;
import java.util.ArrayList;

/**
 * This class maintains a set of tools (static methods) essential for
 * importing/exporting Products' data from/onto .csv files.
 *
 * <p>
 * Attributes:</p>
 * <ul>
 * <li>Product Database Filepath</li>
 * </ul>
 *
 * Behaviours:
 * <ul>
 * <li>Import Product Data</li>
 * <li>Export Product Data</li>
 * </ul>
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 1.03
 * @since 10/04/2021
 *
 */
public final class ProductFileIO {
    public static final String PRODUCT_FILEPATH = "./Resources/csv_files/product_database.csv";

    /**
     * This method reads Products' information from a .csv file and loads them
     * onto a local ProductList.
     *
     * <p>
     * It processes the information by reading each line, then it splits each
     * value using commas as indicators. It then loads the information onto the
     * ProductList by calling the appropriate method and constructor, passing in
     * the processed data.</p>
     *
     * @return A ProductList containing all the Products in the .csv files.
     * Returns a fresh ProductList if there are errors when reading from file.
     *
     */
    public static ProductList importProductData() {
        ProductList productData = new ProductList();

        // Try-with-resources (Block to import data from product_database.csv).
        try (BufferedReader br = new BufferedReader(new FileReader(ProductFileIO.PRODUCT_FILEPATH))) {
            String line = null;
            String[] data = new String[5];

            br.readLine(); // Skips the column headers.

            while (br.ready()) // While there are more data to be read....
            {
                line = br.readLine(); // Read next line and save it to local String.

                if (!line.isEmpty()) { // Check if read line is not empty...
                    // Splits each line into individual values and store them in the String array.
                    data = line.split(",");

                    // Stores imported Product data onto a local ProductList.
                    productData.addSingleProduct(new Product(data[0], // Product Name
                            Integer.parseInt(data[1]), // ID
                            Double.parseDouble(data[2]), // Price 
                            Category.valueOf(data[3]), // Category
                            Integer.parseInt(data[4]))); // Stock
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file.");
        }

        return productData;
    }

    /**
     * This method writes locally stored Products' information onto a .csv file.
     *
     * <p>
     * Steps:</p>
     * <ol>
     * <li>Instantiate a Buffered PrintWriter Object with overwrite mode.</li>
     * <li>Print Column headers.</li>
     * <li>Procure the ArrayList of Products</li>
     * <li>Traverse through the passed in ArrayList</li>
     * <li>Write information onto the appropriate file.</li>
     * </ol>
     *
     * @param products : The ProductList the system possesses.
     * @return T/F whether the write was successful.
     *
     */
    public static boolean exportProductData(ProductList products) {
        boolean writeSuccess = false;

        // Try-with-resources (Buffered PrintWriter which overwrites existing files with new data).
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ProductFileIO.PRODUCT_FILEPATH, false)))) {
            // Prints out column headers.
            pw.println("Product Name,Product ID,Price,Category,Stock");

            // Gets all the products that are in the ProductList as an ArrayList.
            ArrayList<Product> temp = products.getProductList();

            // For each loop to traverse through the ArrayList.         
            for (Product product : temp) {
                // Writes data from the ArrayList onto the file specified. 
                pw.println(product.toString().trim());
            }

            writeSuccess = true;
        } catch (FileNotFoundException fe) {
            System.err.println("File not found.");
            writeSuccess = false;
        } catch (IOException e) {
            System.err.println("Error writing onto file.");
            writeSuccess = false;
        }

        return writeSuccess;
    }
}
