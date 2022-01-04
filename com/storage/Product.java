package com.storage;

/**
 *Product class stores the details about the Product.
 *
 *
 *@author Bharath S
 */
public class Product{
    private int prodID;
    private String prodName;
    private int prodPrice;
    private int prodQuantity;
    private boolean byOneGetOne;

    /**
     *This is setter method for prodID.
     *
     *@param id id is the product ID passed to this setter.
     */
    public void setProdID(int id){
        this.prodID=id;
    }

    /**
     *This is getter method for prodID.
     *
     *@return this method returns the prodID of the object calling this getter.
     */
    public int getProdID(){
        return this.prodID;
    }

    /**
     *This is setter method for prodName.
     *
     *@param name name is the product name passed to this setter.
     */
    public void setProdName(String name){
        this.prodName=name;
    }

    /**
     *This is getter method for prodName.
     *
     *@return this method returns the prodName of the object calling this getter.
     */
    public String getProdName(){
        return this.prodName;
    }
    
    /**
     *This is setter method for prodPrice.
     *
     *@param price price is the product price passed to this setter.
     */
    public void setProdPrice(int price){
        this.prodPrice=price;
    }

    /**
     *This is getter method for prodPrice.
     *
     *@return this method returns the prodPrice of the object calling this getter.
     */
    public int getProdPrice(){
        return this.prodPrice;
    }

    /**
     *This is setter method for prodQuantity.
     *
     *@param quantity quantity is the product quantity passed to this setter.
     */
    public void setProdQuantity(int quantity){
        this.prodQuantity=quantity;
    }

    /**
     *This is getter method for prodQuantity.
     *
     *@return this method returns the prodQuantity of the object calling this getter.
     */
    public int getProdQuantity(){
        return this.prodQuantity;
    }

    /**
     *This method is to set byOneGetOne as true.
     *
     */
    public void setBuyOneGetOne(){
        this.byOneGetOne=true;
    }

    /**
     *This method is to set byOneGetOne as false.
     *
     */
    public void resetBuyOneGetOne(){
        this.byOneGetOne=false;
    }

    /**
     *This is getter method for byOneGetOne.
     *
     *@return this method returns the byOneGetOne of the object calling this getter.
     */
    public boolean getBuyOneGetOne(){
        return this.byOneGetOne;
    }
}