package com.storage;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.activity.Inventory;
import com.activity.CustomerActivity;

/**
 *Bill class stores the details about the Bill and the methods related to billing the cart of a customer.
 *
 *@author Bharath S
 */
public class Bill{
    private static int id=1;
    private static int todaysRevenue;
    private static LocalDate todaysDate=LocalDate.now();
    private int billID;
    private ArrayList<String> list=new ArrayList<String>();
    private int total;
    private LocalDate date;

    /**
     *This static method stores todays revenue if the billing is done today.
     *
     *@param total total is the new bills total amount.
     *@param date date is billing date, if it is todays date then it gets added to todaysRevenue.
     */
    public static void addTodaysRevenue(int total,LocalDate date){
        if(!todaysDate.equals(LocalDate.now())){
            todaysDate=LocalDate.now();
            todaysRevenue=0;
        }
        if(date.equals(LocalDate.now())) {
        todaysRevenue+=total;
        }
    }

    /**
     *This static method returns todays total revenue generated.
     *
     *@return this method returns total revenue generated today.
     */
    public static int getTodaysRevenue(){
        if(!todaysDate.equals(LocalDate.now())){
            todaysDate=LocalDate.now();
            todaysRevenue=0;
        }
        return todaysRevenue;
    }

    /**
     *This method creates bill of the customer based on products in their cart.
     *
     *@param cart cart is hashmap containg the product and quantity that needs to be billed.
     *@param c c is Customer object who needs to purchase cart products.
     */
    public void setBill(HashMap<Integer,Integer> cart, Customer c){
        date=LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.list.add("Date: "+date.format(formatter));
        this.billID=id++;
        this.list.add("Bill ID: "+Integer.toString(this.billID));
        ArrayList<Product> pList=Inventory.getProductsList();
        int grandTotal=0,discount;
        for(Product p:pList)
        {   
            StringBuilder sb=new StringBuilder();
            int key=p.getProdID(),k;
            int total;
            int updatedQuantity;
            if(cart.containsKey(key))
            {
                if(p.getBuyOneGetOne()) {
                    updatedQuantity=p.getProdQuantity()-(2*cart.get(key));
                }
                else {
                    updatedQuantity=p.getProdQuantity()-cart.get(key);
                }
                c.setLoyalPoints(cart.get(key));
                total=p.getProdPrice()*cart.get(key);
                p.setProdQuantity(updatedQuantity);
                if(updatedQuantity<10) {
                Inventory.setProductsSoonOutOfStock(p);
                }
                if(updatedQuantity==1) {
                Inventory.setProductsOutOfStock(p);
                }
                grandTotal=grandTotal+total;
                sb.append(p.getProdName());
                sb.append(" ");
                sb.append(p.getProdPrice());
                sb.append(" ");
                if(p.getBuyOneGetOne()) {
                sb.append(cart.get(key)*2);
                }
                else {
                sb.append(cart.get(key));
                }
                sb.append(" ");
                sb.append(total);
                this.list.add(sb.toString());
                Inventory.removeProductsNotSold(p);
                Inventory.setSoldProducts(p,cart.get(key));
            }
            CustomerActivity.productUpdatedUpdateCarts(p);
        }

        if(grandTotal<50){
            grandTotal=grandTotal+20;
            this.list.add("Delivery charge added, since the Grand total is less than Rs.50");
        }

        if(grandTotal>300){
            discount=grandTotal/10;
            grandTotal=grandTotal*90/100;
            c.addToTotalDiscount(discount);
            this.list.add("Congrats, ten percent discount on Bill greater than Rs.300");
        } 

        if(c.getLoyalPointsBenefit()){
            discount=(c.getLoyalPoints()*3);
            c.addToTotalDiscount(discount);
            grandTotal=grandTotal-(c.getLoyalPoints()*3);
            this.list.add("Congrats, You got loyal points benefit of Rs."+Integer.toString(c.getLoyalPoints()*3));
            c.resetLoyalPoints();
            c.setLoyalPointsBenefit(false);
        }

        this.list.add("Grand Total: "+Integer.toString(grandTotal));
        this.total=grandTotal;
        addTodaysRevenue(this.total,this.date);
    }

     /**
     *This method returns the bill date of the bill object calling this function.
     *
     @return this method returns the bill date of the bill object.
     */
    public LocalDate getDate(){
        return this.date;
    }

    /**
     *This method returns the bill id of the bill object calling this function.
     *
     @return this method returns the bill id of the bill object.
     */
    public int getBillID(){
        return this.billID;
    }

    /**
     *This method returns the billed product list of the bill object calling this function.
     *
     @return this method returns arraylist of billed products and all other details.
     */
    public ArrayList<String> getList(){
        return this.list;
    }

    /**
     *This method returns the grand total after billing all products in the cart.
     *
     @return this method returns grand total integer after billing cart.
     */
    public int getTotal(){
        return this.total;
    }
}