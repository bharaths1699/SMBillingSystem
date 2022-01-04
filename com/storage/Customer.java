package com.storage;
import java.util.ArrayList;
import java.util.HashMap;
import com.storage.People;
import com.storage.Bill;

/**
 *Customer class stores the details about the Customer and it inherits class Common.
 *
 *
 *@author Bharath S
 */
public class Customer extends People{
    private HashMap<Integer,Integer> cart=new HashMap<Integer,Integer>();
    private ArrayList<Bill> billList=new ArrayList<Bill>();
    private int balance;
    private int loyalPoints;
    private boolean loyalPointsBenefit;
    private int totalDiscount;

    /**
     *This is setter method for cart HashMap.
     *
     *@param cart cart is the HashMap passed to this setter to initialize it to cart of the object calling this getter.
     */
    public void setCart(HashMap<Integer,Integer> cart){
        this.cart=cart;
    }

    /**
     *This is getter method for cart.
     *
     *@return this method returns the HashMap cart of the object calling this getter.
     */
    public HashMap<Integer,Integer> getCart(){
        return this.cart;
    }

    /**
     *This is method to add bill to bills list of customer.
     *
     *@param Bill b is passed which needs to be added  to bill list.
     */
    public void addToBillList(Bill b){
        this.billList.add(b);
    }
    
    /**
     *This is getter method for bill list.
     *
     *@return this method returns the bills list of the object calling this getter.
     */
    public ArrayList<Bill> getBillList(){
        return this.billList;
    }

    /**
     *This is setter method for customer balance.
     *
     *@param balance balance is passed to set this as customer object balance.
     */
    public void setBalance(int balance){
        this.balance=balance;
    }

    /**
     *This is getter method for customer balance.
     *
     *@return this method returns the customer balance amount of customer object calling this.
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     *This is setter method for customer loyalpoints.
     *
     *@param quantity quantity is passed to add this as customer object loyal points.
     */
    public void setLoyalPoints(int quantity){
        this.loyalPoints+=quantity;
    }

    /**
     *This is getter method for customer loyal points.
     *
     *@return this method returns the customer loyal points of customer object calling this.
     */
    public int getLoyalPoints(){
        return this.loyalPoints;
    }

    /**
     *This method reset the loyal pints of customer as zero.
     *
     */
    public void resetLoyalPoints(){
        this.loyalPoints=0;
    }

    /**
     *This is setter method for customer setloyalpointsbenefit.
     *
     *@param val val is boolean value to check if the customer wants to claim the benefit of loyal points.
     */
    public void setLoyalPointsBenefit(boolean val){
        this.loyalPointsBenefit=val;
    }

    /**
     *This is getter method for customer setloyalpointsbenefit.
     *
     *@return this method returns the customer's setloyalpointsbenefit of customer object calling this.
     */
    public boolean getLoyalPointsBenefit(){
        return this.loyalPointsBenefit;
    }

    /**
     *This method for setting total discount earned by customer.
     *
     *@param d d is the new discount value that needs to be added to the total discount.
     */
    public void addToTotalDiscount(int d){
        this.totalDiscount+=d;
    }

    /**
     *This method for returning total discount earned by customer.
     *
     *@return this method returns the total discount earned by the customer.
     */
    public int getTotalDiscount(){
        return this.totalDiscount;
    }
}