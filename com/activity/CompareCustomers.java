package com.activity;
import com.storage.Customer;
import java.util.Comparator;

/**
 *CompareCustomers class is class implementing Comparator interface, for comparision of customers based on their loyal points.
 *
 *
 *@author Bharath S
 */
public class CompareCustomers implements Comparator<Customer>{
    /**
     *This method compares two customers for the purpose of sorting a customers based on their loyal points.
     *
     *@param c1 c1 is first Customer object which is being compared.
     *@param c2 c2 is second Customer object which is being compared.
     *@return This method returns integer 1 if Customer c1's loyal points is greater than Customer c2's loyal points else it returns -1.
     */
    public int compare(Customer c1, Customer c2){
        if(c1.getLoyalPoints()<c2.getLoyalPoints()){
        return 1;
        }

        return -1;
    }
}