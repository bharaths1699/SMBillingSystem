package com.activity;
import java.util.ArrayList;
import com.storage.Admin;
import com.storage.People;
import com.storage.Product;
import com.storage.Customer;
import com.activity.Inventory;
import com.activity.PeopleActivity;
import com.activity.ConstantValues;

/**
 *AdminActivity class has the methods used for operations of admins and it inherits the CommonActivity class.
 *
 *
 *@author Bharath S
 */
public class AdminActivity extends PeopleActivity{

    /**
     *This static method adds new product, by calling the addProduct method of Inventory class after admin authentication.
     *
     *@param name name of the product that needs to be added.
     *@param price price of the product that needs to be added.
     *@param quantity quantity of the product that needs to be added.
     *@return this method returns constant integers based on the method execution.
     */
    public static int addProduct(String name, int price, int quantity){
        return Inventory.addProduct(name,price,quantity);
    }

    /**
     *This static method is used to update product, by calling updateProduct method in Inventory class after admin authentication.
     *
     *@param id id is passed to specify the product.
     *@param name name is passed to specify the new name of the product.
     *@param price price is passed to specify the new price of the product.
     *@param quantity quantity is passed to specify the new quantity of the product.
     *@return this method returns constant integers based on the method execution.
     */
    public static int updateProduct(int id,String name, int price, int quantity){
        return Inventory.updateProduct(id,name,price,quantity);
    }

    /**
     *This static method is used to view all of the customer details and 
     *this method calls viewCustomerDetails method of customer class.
     *
     *@return this method returns ArrayList of all admins and customers i.e people for viewing the customer details.
     */
    public static ArrayList<People> viewCustomerDetails(){
        return CustomerActivity.viewCustomerDetails();
    }

    /**
     *This static method is used to update the customer balance, this is achieved by calling 
     *updateCustomerBalance method of CustomerActivity class.
     *
     *@param id id is passed to specify the customer username.
     *@param balance balance is passed to specify the new balance of the customer.
     *@return this method returns constant integers based on the method execution.
     */
    public static int updateCustomerBalance(String id,int balance){
        return CustomerActivity.updateCustomerBalance(id,balance);
    }

    /**
     *This static method is used to display the newly siged up customers, it is done by calling the viewNewSignUp method of customeractivity. 
     *
     *@return this method returns constant integers based on the method execution.
     */
    public static ArrayList<Customer> viewNewSignUp(){
        return CustomerActivity.viewNewSignUp();
    }
}