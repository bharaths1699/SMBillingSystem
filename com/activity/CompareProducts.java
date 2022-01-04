package com.activity;
import com.storage.Product;
import java.util.Comparator;

/**
 *CompareProducts class is class implementing Comparator interface, for comparision of products based on their prices.
 *
 *
 *@author Bharath S
 */
public class CompareProducts implements Comparator<Product>{
    /**
     *This method compares two products for the purpose of sorting a ArrayList of Products by price.
     *
     *@param p1 p1 is first Product object which is being compared.
     *@param p2 p2 is second Product object which is being compared.
     *@return This method returns integer 1 if product p1's price is greater than product p2's price else it returns -1.
     */
    public int compare(Product p1, Product p2){
        if(p1.getProdPrice()>p2.getProdPrice()) {
        return 1;
        }

        return -1;
    }
}