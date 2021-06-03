package com.online.shopping_gui.model;

import java.util.ArrayList;

/**
 * This class is a representation of a shopping cart, it has:
 * <p>
 * Attributes:</p>
 * <ul>
 * <li>Products : List of products in the cart.</li>
 * <li>Quantity : Amount of a specific product the customer ordered.</li>
 * <li>GrandTotal: Total cost.</li>
 * <li>User: Logged in user who is using the cart.</li>
 * </ul>
 * Methods:
 * <ul>
 * <li>1-Parameter Constructor</li>
 * <li>Getter</li>
 * <li>AddToCart</li>
 * <li>RemoveFromCart</li>
 * <li>ClearCart</li>
 * <li>CartList</li>
 * <li>GenerateInvoice</li>
 * </ul>
 *
 * @author Miguel Emmara - 18022146
 * @author Amos Foong - 18044418
 * @author Roxy Dao - 1073633
 * @version 2.0.4
 * @since 01/04/2021
 *
 */
public class ShoppingCart {

    private ArrayList<Product> products;
    private ArrayList<Integer> quantity;
    private double grandTotal;
    private User user;

    /**
     * 0-Parameter constructor for ShoppingCart, prepares Object attributes so
     * it can be used.
     *
     * @param user : Logged in user who will be using the cart.
     *
     */
    public ShoppingCart() {
        this.products = new ArrayList<Product>();
        this.quantity = new ArrayList<Integer>();
        this.grandTotal = 0D;
    }
    
    /**
     * 1-Parameter constructor for ShoppingCart, prepares Object attributes so
     * it can be used.
     *
     * @param user : Logged in user who will be using the cart.
     *
     */
    public ShoppingCart(User user) {
        this.products = new ArrayList<Product>();
        this.quantity = new ArrayList<Integer>();
        this.grandTotal = 0D;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public int size() {
        return products.size();
    }
    
    public boolean isEmpty() {
        return (products.size() == 0);
    }
    
    public Product getProduct(int index) {
        return this.products.get(index);
    }
    
    /**
     * Returns the total summed value of the cart items.
     *
     * @return Summed value of the cart items.
     *
     */
    public double getGrandTotal() {
        return this.grandTotal;
    }

    /**
     * Adds a product to the cart.
     *
     * @param product : Product to be added.
     * @param amount : Quantity of the specific product.
     *
     */
    public void addToCart(Product product, int amount) {
        if(amount <= product.getStock()) { // If quantity requested is less thn eql to available stock...
            if(!products.contains(product)) { // If cart does not contain the product...
                this.products.add(product); // Add product to the cart.
                this.quantity.add(amount); // Specify quantity needed in the cart.                
            } else { // If product is already in the cart
                int indexOf = products.indexOf(product); // Get index of existing product in cart.
                quantity.set(indexOf, (this.quantity.get(indexOf) + amount)); // Update quantity. 
            }
            product.setStock(product.getStock() - amount); // Decrements product stock.
            this.grandTotal += (product.getPrice() * amount); // Tally up total.
        } else { // Otherwise...
            throw new IllegalArgumentException("Insufficient stock! Please wait while we restock =)");
        }
    }

    /**
     * Removes a specific item from the cart.
     *
     * @param index : Index of item to be removed.
     * @return A new updated stock number.
     */
    public int removeFromCart(int index) {
        int newStock = 0;
        if(!isEmpty() && index >= 0) { // Check if index passed in is within bounds...
            newStock = this.products.get(index).getStock();
            newStock += this.quantity.get(index);
            this.grandTotal -= (this.products.get(index).getPrice() * this.quantity.get(index));
            this.products.get(index).setStock(newStock); // Increments product's stock.
            this.products.remove(index);
            this.quantity.remove(index);            
        } 
        return newStock;
    }

    /**
     * Removes everything from cart.
     *
     */
    public void clearCart() {
        this.products.clear();
        this.quantity.clear();
        this.grandTotal = 0D;
    }

    /**
     * String representation of the current items in the cart.
     *
     * @return String representation of the items in the cart.
     *
     */
    public String cartList() {
        String cartList = "";

        for (int i = 0; i < this.products.size(); i++) {
            cartList += "Product: " + this.products.get(i).getProductName() + " Qty: " + this.quantity.get(i) + " Price: " + this.products.get(i).getPrice() + "\n";
        }

        return cartList;
    }

    /**
     * Generates an invoice and returns a graphical representation of the
     * invoice to caller.
     *
     * @param currentUser : User who is logged on currently.
     * @return String representation of the invoice.
     */
    public String generateInvoice(User currentUser) {
        String invoice = "";
        String [] address = ((Customer)currentUser).getAddress().split(",");

        invoice += "\n-----------------Invoice-----------------\n";
        invoice += "Bill To: " + ((Customer) currentUser).getName() + "\n";
        invoice += "Address: " + address[0] + "\n";
        invoice += "-----------------------------------------\n";
        invoice += String.format("%-5s%-25s%10s\n",  "Qty", "Product", "Price");

        for (int i = 0; i < this.products.size(); i++) {
            invoice += String.format("%-4d  %-23s  $%-7.2f\n", this.quantity.get(i), this.products.get(i).getProductName(), (this.products.get(i).getPrice() * this.quantity.get(i)));
        }
        invoice += "-----------------------------------------\n";
        invoice += String.format("%32s%7.2f\n", "Grand Total: $", this.getGrandTotal());
        invoice += "-----------------------------------------";
        return invoice;
    }
    
    /**
     * Converts both ArrayLists into a 2D Object array.
     *
     * @return 2D Object array containing cart 
     *          information to be displayed in JTable.
     */
    public Object[][] convertShoppingCart() {
        int columns = 3;
        Object[][] cartObjArr = new Object[products.size()][columns];
        
        for(int i = 0; i < products.size(); i++) {
            cartObjArr[i][0] = products.get(i).getProductName();
            cartObjArr[i][1] = quantity.get(i);
            cartObjArr[i][2] = products.get(i).getPrice() * quantity.get(i);
        }
        
        return cartObjArr;
    }
}
