package com.activity;
import java.util.ArrayList;
import java.util.HashMap;
import com.storage.Product;
import com.activity.ConstantValues;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *Inventory class has the methods which are used for operations of products, customers and admins.
 *
 *@author Bharath S
 */
public class Inventory{
    private static ArrayList<Product> productsList=new ArrayList<Product>();
    private static ArrayList<Product> productsSoonOutOfStock=new ArrayList<Product>();
    private static ArrayList<Product> productsOutOfStock=new ArrayList<Product>();
    private static ArrayList<Product> productsNotSold=new ArrayList<Product>();
    private static HashMap<Product,Integer> soldProducts=new HashMap<Product,Integer>();

    private static int prodID=1;

    /**
     *This static method adds new product to productList.
     *
     *@param name name of the product that needs to be added.
     *@param price price of the product that needs to be added.
     *@param price price of the product that needs to be added.
     *@return this method returns constant integers based on the method execution.
     */
    public static int addProduct(String name, int price, int quantity){
        for(Product p : productsList){ 
            String s=p.getProdName();
            if(name.equals(s)) {
            return ConstantValues.PRODUCT_ALREADY_PRESENT;
            }
        }
        if(price<=0) {
        return ConstantValues.WRONG_PRICE;
        }
        if(quantity<0) {
        return ConstantValues.WRONG_QUANTITY;
        }
        Product newProd=new Product();
        newProd.setProdID(prodID++);
        newProd.setProdName(name);
        newProd.setProdPrice(price);
        newProd.setProdQuantity(quantity);
        productsList.add(newProd);
        productsNotSold.add(newProd);
        updateProductListFile();
        return ConstantValues.PRODUCT_ADDED_SUCESSFULLY;
    }

    /**
     *This static method is used to update product in product list.
     *
     *@param id id is passed to specify the product.
     *@param name name is passed to specify the new name of the product.
     *@param price price is passed to specify the new price of the product.
     *@param quantity quantity is passed to specify the new quantity of the product.
     *@return this method returns constant integers based on the method execution.
     */
    public static int updateProduct(int id,String name, int price, int quantity){
        boolean productWithSameName=false;
        if(price<=0) {
        return ConstantValues.WRONG_PRICE;
        }
        if(quantity<0) {
        return ConstantValues.WRONG_QUANTITY;
        }

        for(Product p : productsList){
            if(p.getProdName().equals(name) && p.getProdID()!=id){
                return ConstantValues.PRODUCT_ALREADY_PRESENT;
            }
        }

        for(Product p : productsList){ 
            if(p.getProdID()==id){
                p.setProdName(name);
                p.setProdPrice(price);
                p.setProdQuantity(quantity);
                CustomerActivity.productUpdatedUpdateCarts(p);
                updateProductListFile();
                removeProductsSoonOutOfStock(p);
                removeProductsOutOfStock(p);
                return ConstantValues.PRODUCT_UPDATED_SUCESSFULLY;
            }
        }
        return ConstantValues.PRODUCT_ID_ABSENT;
    }

    /**
     *This static method displays all the products in the Inventory, by iterating the product list of Inventory class.
     */
    public static ArrayList<Product> viewProducts(){
        return productsList;
    }

    /**
     *This is getter method for productsList in Inventory class.
     *
     *@return this method returns the ArrayList of productsList in Inventory class.
     */    
    public static ArrayList<Product> getProductsList(){
        return productsList;
    }

    /**
     *This static method searches the product using its name, this uses productList of Inventory class.
     *
     *@param name name is the string parameter based on which name search takes place.
     *@return this method returns Product object which has this name.
     */
    public static Product searchProductByName(String name){
        for(Product p : productsList){
                if(p.getProdName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     *This static method searches the product using its price, this uses productList of Inventory class.
     *
     *@param price price is the string parameter based on which price search takes place.
     *@return this method returns Product object which has this price.
     */
    public static Product searchProductByPrice(int price){
        for(Product p : productsList){
                if(p.getProdPrice()==price){
                return p;
            }
        }
        return null;
    }
    
    /**
     *This static method searches the product using its id, this uses productList of Inventory class.
     *
     *@param id id is the string parameter based on which id search takes place.
     *@return this method returns Product object which has this id.
     */
    public static Product searchProductByID(int id){
        for(Product p : productsList){
                if(p.getProdID()==id){
                return p;
            }
        }
        return null;
    }

    /**
     *This static method checks if the quantiy of product that needs to be addded to cart is valid.
     *
     *@param id id is the product id that needs to be added to cart.
     *@param quantity quantity is the product quantity that needs to be added to cart.
     *@return this method returns constant integers based on the method execution.
     */
    public static int checkIDQuantity(int id, int quantity){
        for(Product p : productsList){
            if(p.getProdID()==id){
                if(p.getProdQuantity()<quantity || quantity<=0) {
                return ConstantValues.WRONG_CART_QUANTITY;
                }
                else if((p.getProdQuantity()*90/100)<quantity) {
                return ConstantValues.MAX_QUANTITY_REACHED;
                }
                else {
                return ConstantValues.CORRECT_CART_QUANTITY;
                }
            }
        }
        return ConstantValues.PRODUCT_ID_ABSENT;
    }

    /**
     *This static method updates the  Product List File whenever the product list is changed by any operations.
     *
     */
    public static void updateProductListFile(){
        File myFile = new File("ProductList.txt");
        myFile.delete();
        try {
            FileWriter fileWriter = new FileWriter("ProductList.txt");
            for(Product p : productsList) {
            fileWriter.write(p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity());
            }
            fileWriter.close();
        } 
        catch (IOException e) {
        }
    }

    /**
     *This static method adds produt passed to list of soon out of stock.
     *
     *@param p p is product which will soon be out of stock.
     */
    public static void setProductsSoonOutOfStock(Product p){
        productsSoonOutOfStock.add(p);
    }

    /**
     *This is getter method for products soon out of stock in Inventory.
     *
     *@return this method returns the ArrayList of products in which will soon be out of stock.
     */
    public static ArrayList<Product> getProductsSoonOutOfStock(){
        if(productsSoonOutOfStock.size()==0) {
        System.out.println("No products found");
        }
        for(Product p : productsSoonOutOfStock) {
        System.out.println(p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity());
        }
        return productsSoonOutOfStock;
    }

    /**
     *This static method removes produt passed to list of soon out of stock.
     *
     *@param p p is product which should be removed from list of soon out of stock.
     */
    public static void removeProductsSoonOutOfStock(Product p){
        int key=productsSoonOutOfStock.indexOf(p);
        if(key!=-1) {
        productsSoonOutOfStock.remove(key);
        }
    }

    /**
     *This static method adds product passed to list of out of stock.
     *
     *@param p p is product which is out of stock.
     */
    public static void setProductsOutOfStock(Product p){
        removeProductsSoonOutOfStock(p);
        productsOutOfStock.add(p);
    }

    /**
     *This is getter method for products out of stock in Inventory.
     *
     *@return this method returns the ArrayList of products in which will is out of stock.
     */
    public static ArrayList<Product> getProductsOutOfStock(){
        if(productsOutOfStock.size()==0) {
        System.out.println("No products found");
        }
        for(Product p : productsOutOfStock) {
        System.out.println(p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity());
        }
        return productsOutOfStock;
    }

    /**
     *This static method removes product passed to list of soon out of stock.
     *
     *@param p p is product which should be removed from list of soon out of stock.
     */
    public static void removeProductsOutOfStock(Product p){
        int key=productsOutOfStock.indexOf(p);
        if(key!=-1) {
        productsOutOfStock.remove(key);
        }
    }

    /**
     *This is getter method for products not sold list in Inventory.
     *
     *@return this method returns the ArrayList of products which are yet to be sold.
     */
    public static ArrayList<Product> getProductsNotSold(){
        if(productsNotSold.size()==0) {
        System.out.println("No products found");
        }
        for(Product p : productsNotSold) {
        System.out.println(p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity());
        }
        return productsNotSold;
    }

    /**
     *This static method for removes product from not sold list in Inventory.
     *
     *@param p p is product which should be removed from list of products not sold.
     */
    public static void removeProductsNotSold(Product p){
        int key=productsNotSold.indexOf(p);
        productsNotSold.remove(key);
    }

    /**
     *This static method adds produt passed to sold products hashmap.
     *
     *@param p p is the sold product.
     *@param quantity quantity is the sold product's quantity.
     */
    public static void setSoldProducts(Product p,int quantity){
        int q;
        if(!soldProducts.containsKey(p)) {
        soldProducts.put(p,quantity);
        }

        else{
            q=soldProducts.get(p);
            q+=quantity;
            soldProducts.replace(p,q);
        }
    }

    /**
     *This is getter method for popular product in Inventory.
     *
     *@return this method returns the most popular product.
     */
    public static Product getPopularProduct(){
        int highest=Integer.MIN_VALUE;
        Product popular=null;
        if(soldProducts.size()!=0){
        for(Product key : soldProducts.keySet()){
            if(highest<soldProducts.get(key)){
            highest=soldProducts.get(key);
            popular=key;
            }
        }
        }
        return popular;
    }
}