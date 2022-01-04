package com.interact;
import java.util.Scanner;
import com.activity.AdminActivity;
import com.activity.PeopleActivity;
import com.activity.CustomerActivity;
import com.activity.Inventory;
import com.storage.Customer;
import com.storage.People;
import com.activity.ConstantValues;
import com.storage.Product;
import com.storage.Admin;
import com.storage.Bill;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *Interact class has the methods used for interaction of customers and admins on console screen.
 *
 *@author Bharath S
 */
public class Interact{
	
    /**
     *This static method generates various reports of customers.
     *
     *@param c c is the customer object whose report needs to be generated.
     */
    public static void custReports(Customer c){
        wl:
        while(true){
            Scanner sc=new Scanner(System.in);
            int opt,r;
            System.out.println();
            System.out.println("1.View your all Bills 2.View total Discounts and offers gained 3.Money in the wallet 4.Exit");
            System.out.println();
            opt=sc.nextInt();
            switch(opt){
                case 1:
                CustomerActivity.viewAllMyBills(c);
                break;
                case 2:
                r=CustomerActivity.getTotalDiscount(c);
                System.out.println("Total Discount: "+Integer.valueOf(r));
                break;
                case 3:
                r=CustomerActivity.getBalance(c);
                System.out.println("Money reamining in wallet: "+Integer.valueOf(r));
                break;
                default:
                break wl;
            }
        }
    }

    /**
     *This static method  generates various reports of SMBillingSystem for admins.
     *
     */
    public static void adminReports(){
        ArrayList<Customer> alc;
        int top=0;
        int r;
        wl:
        while(true){
            Scanner sc=new Scanner(System.in);
            int opt;
            Product popular;
            System.out.println();
            System.out.println("1.View customers who have not purchased any items\n 2.View top Customers 3.Products soon out of stock 4.Products out of stock \n5.Products not sold 6.Most popular product 7.Get today's revenue \n8.Exit");
            System.out.println();
            opt=sc.nextInt();
            switch(opt){
                case 1:
                alc=AdminActivity.viewNewSignUp();
                if(alc.size()==0) {
                System.out.println("No customers present");
                }
                else{
                    for(Customer c : alc){
                        System.out.println("Name: "+c.getName()+" Balance: "+c.getBalance());
                    }
                }
                break;
                case 2:
                alc=CustomerActivity.viewTopCustomers();
                if(alc.size()==0) {
                    System.out.println("No customers found");
                }

                for(Customer c : alc){
                    if(top<2 && c.getLoyalPoints()!=0) {
                        System.out.println(c.getName()+" "+c.getBalance()+" "+c.getLoyalPoints()+" ");
                    }
                        top++;
                }
                break;
                case 3:
                Inventory.getProductsSoonOutOfStock();
                break;
                case 4:
                Inventory.getProductsOutOfStock();
                break;
                case 5:
                Inventory.getProductsNotSold();
                break;
                case 6:
                popular=Inventory.getPopularProduct();
                if(popular!=null) {
                    System.out.println(popular.getProdID()+" "+popular.getProdName()+" "+popular.getProdPrice()+" "+popular.getProdQuantity());
                }
                else {
                    System.out.println("No product found");
                }
                break;
                case 7:
                r=Bill.getTodaysRevenue();
                System.out.println("Today's revenue generated is: "+r);
                default:
                break wl;
            }
        }
    }

    /**
     *This static method is for various operations of customer in SMBillingSystem.
     *
     *@param c c is the customer object who need to perform various operations.
     */
    public static void custInventory(Customer c){
        ArrayList<Product> alp1;
        HashMap<Integer,Integer> hmc;
        wl:
        while(true){
                Scanner sc=new Scanner(System.in);
                int opt,id,r,quantity,price;
                ArrayList<Product> alp;
                boolean option;
                String name;
                Product p;
                System.out.println();
                System.out.println("1.View Products 2.View Products in Ascending order of Price 3.View Products in Descending order of price \n4.Search Product by Name 5.Search Product by Price 6.Add product to cart \n7.View Cart 8.Print Bill 9.View my details 10.View reports \n11.Exit");
                System.out.println();
                opt=sc.nextInt();
                switch(opt){
                    case 1:
                    alp1=CustomerActivity.viewProducts();
                    for(Product p1 : alp1){
                    System.out.println(" "+p1.getProdID()+" "+p1.getProdName()+" "+p1.getProdPrice()+" "+p1.getProdQuantity()+" ");
                    if(p1.getBuyOneGetOne()) {
                    System.out.println("By one get one");
                    }
                    System.out.println();
                    }
                    break;
                    case 2:
                    alp=CustomerActivity.viewProductsAsc();
                    for(Product p1 : alp){
                        System.out.println(" "+p1.getProdID()+" "+p1.getProdName()+" "+p1.getProdPrice()+" "+p1.getProdQuantity()+" ");
                    }
                    break;
                    case 3:
                    alp=CustomerActivity.viewProductsDsc();
                    for(Product p2 : alp){
                        System.out.println(" "+p2.getProdID()+" "+p2.getProdName()+" "+p2.getProdPrice()+" "+p2.getProdQuantity()+" ");
                    }
                    break;
                    case 4:
                    System.out.println("Enter Product's Name: ");
                    name=sc.next();
                    p=CustomerActivity.searchProductByName(name);
                    if(p==null) {
                    System.out.println("Product with this name not present");
                    }
                    else {
                    System.out.println(" "+p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity()+" ");
                    }
                    break;
                    case 5:
                    System.out.println("Enter Product's Price: ");
                    price=sc.nextInt();
                    p=CustomerActivity.searchProductByPrice(price);
                    if(p==null) {
                    System.out.println("Product  with this price not present");
                    }
                    else {
                    System.out.println(" "+p.getProdID()+" "+p.getProdName()+" "+p.getProdPrice()+" "+p.getProdQuantity()+" ");
                    }
                    break;
                    case 6:
                    System.out.println("Enter Product's Id: ");
                    id=sc.nextInt();
                    System.out.println("Enter Quantity: ");
                    quantity=sc.nextInt();
                    r=CustomerActivity.addCart(id,quantity,c);
                    if(r==ConstantValues.ADDED_TO_CART) {
                    System.out.println("Product added to cart sucessfully");
                    }
                    else if(r==ConstantValues.WRONG_CART_QUANTITY) {
                    System.out.println("This quantity is not available");
                    }
                    else if(r==ConstantValues.MAX_QUANTITY_REACHED) {
                    System.out.println("The purchasing quantity should be less than ninty percent of available Quantity");
                    }
                    else if(r==ConstantValues.PRODUCT_ID_ABSENT) {
                    System.out.println("This Product ID not available");
                    }
                    break;
                    case 7:
                    hmc=CustomerActivity.viewCart(c);
                    if(hmc.size()==0) {
                        System.out.println("Cart is empty");
                    }
                    else{
                        for(Integer id1 : hmc.keySet()){
                            System.out.println(" "+id1+" "+hmc.get(id1)+" ");
                            System.out.println();
                        }
                    }
                    break;
                    case 8:
                    System.out.println("Do you want to apply your loyal points press yes or no: ");
                    name=sc.next();
                    if(name.equals("yes") || name.equals("YES")) {
                    c.setLoyalPointsBenefit(true);
                    }
                    CustomerActivity.printBill(c);
                    break;
                    case 9:
                    ArrayList<Bill> alb=CustomerActivity.viewMyCustomerDetails(c);
                    System.out.println("Name: "+c.getName()+" Balance: "+c.getBalance());
                    if(alb.size()==0) {
                        System.out.println("No purchase made");
                    }
                    else{
                        System.out.println("Past Payed Bills: ");
                        for(Bill b : alb){
                            for(String s : b.getList()){
                                System.out.println(s);
                            }
                        System.out.println();
                    }
                    }
                    break;
                    case 10:
                    custReports(c);
                    break;
                    default:
                    break wl;
                }
        }
    }

    /**
     *This static method is for various operations of admin in SMBillingSystem.
     *
     */
    public static void adminInventory(){
        wl:
        while(true){
                Scanner sc=new Scanner(System.in);
                String name;
                int id,price,quantity,opt;
                int r=-1;
                Product p;
                ArrayList<Product> alp1;
                ArrayList<People> alc;
                System.out.println();
                System.out.println("1.Add Product 2.Update Product 3.View Products \n4.View customer details 5.Update customer balance 6.View Reports 7.Buy one get one Offer 8.Exit");
                System.out.println();
                opt=sc.nextInt();

                switch(opt){
                    case 1:
                    System.out.println("Enter Product Name: ");
                    name=sc.next();
                    System.out.println("Enter Product Price: ");
                    price=sc.nextInt();
                    System.out.println("Enter Product Quantity: ");
                    quantity=sc.nextInt();
                    r=AdminActivity.addProduct(name,price,quantity);
                    if(r==ConstantValues.PRODUCT_ALREADY_PRESENT) {
                    System.out.println("Product with same name is already present");
                    }
                    else if(r==ConstantValues.WRONG_PRICE) {
                    System.out.println("Price should be greater than zero");
                    }
                    else if(r==ConstantValues.WRONG_QUANTITY) {
                    System.out.println("quantity should not be negative");
                    }
                    else if(r==ConstantValues.PRODUCT_ADDED_SUCESSFULLY) {
                    System.out.println("Product added sucessfully");
                    }
                    break;
                    case 2:
                    System.out.println("Enter Product's ID: ");
                    id=sc.nextInt();
                    System.out.println("Enter Product's new Name: ");
                    name=sc.next();
                    System.out.println("Enter Product's new Price: ");
                    price=sc.nextInt();
                    System.out.println("Enter Product's new Quantity: ");
                    quantity=sc.nextInt();
                    r=AdminActivity.updateProduct(id,name,price,quantity);
                    if(r==ConstantValues.PRODUCT_ALREADY_PRESENT) {
                    System.out.println("Product with same name is already present");
                    }
                    else if(r==ConstantValues.PRODUCT_ID_ABSENT) {
                    System.out.println("Product with this ID is not present");
                    }
                    else if(r==ConstantValues.WRONG_PRICE) {
                    System.out.println("Price should be greater than 0");
                    }
                    else if(r==ConstantValues.WRONG_QUANTITY) {
                    System.out.println("quantity should not be negative");
                    }
                    else if(r==ConstantValues.PRODUCT_UPDATED_SUCESSFULLY) {
                    System.out.println("Product updated sucessfully");
                    }
                    break;
                    case 3:
                    alp1=AdminActivity.viewProducts();
                    for(Product p1 : alp1){
                    System.out.println(" "+p1.getProdID()+" "+p1.getProdName()+" "+p1.getProdPrice()+" "+p1.getProdQuantity()+" ");
                    if(p1.getBuyOneGetOne()) {
                    System.out.println("By one get one");
                    }
                    System.out.println();
                    }
                    break;
                    case 4:
                    alc=AdminActivity.viewCustomerDetails();
                    if(alc!=null) {
                    for(People com : PeopleActivity.getPeople()){
                        if(com instanceof Customer){
                            Customer c=(Customer)(com);
                            System.out.println("Name: "+c.getName()+" Balance: "+c.getBalance());
                        }
                    }
                    }
                    else {
                    System.out.println("Customers not found");
                    }
                    break;
                    case 5:
                    System.out.println("Enter Customer's Name: ");
                    name=sc.next();
                    System.out.println("Enter Customer's Balance to be set: ");
                    price=sc.nextInt();
                    r=AdminActivity.updateCustomerBalance(name,price);
                    if(r==ConstantValues.BALANCE_UPDATE_WRONG_BALANCE) {
                    System.out.println("Balance can't be negative");
                    }
                    else if(r==ConstantValues.BALANCE_UPDATED) {
                    System.out.println("Balance updated sucessfully");
                    }
                    else if(r==ConstantValues.BALANCE_UPDATE_ID_NOT_FOUND) {
                    System.out.println("Customer user name is wrong");
                    }
                    break;
                    case 6:
                    adminReports();
                    break;
                    case 7:
                    System.out.println("Enter the product ID: ");
                    id=sc.nextInt();
                    System.out.println("1.Set add one get one free 2.Disable add one get one: ");
                    r=sc.nextInt();
                    p=Inventory.searchProductByID(id);
                    if(r==1){
                        p.setBuyOneGetOne();
                    }
                    else if(r==2){
                        p.resetBuyOneGetOne();
                    }
                    break;
                    default:
                    break wl;
                }
        }
    }

    /**
     *This static method is for customer interface in SMBillingSystem.
     *
     */
    public static void customer(){
        wl:
        while(true){
                Scanner sc=new Scanner(System.in);
                int opt;
                String userName,password,password1,password2;
                int r=0;
                System.out.println();
                System.out.println("1.Customer Login 2.Customer SignUp 3.Customer Change Password 4.Exit");
                System.out.println();
                opt=sc.nextInt();

                switch(opt){
                    case 1:
                    System.out.println("Enter User Name:");
                    userName=sc.next();
                    System.out.println("Enter User Password:");
                    password=sc.next();
                    Customer c=(Customer)(CustomerActivity.signIn(userName,password));
                    if(c!=null){
                    System.out.println("Login sucessfull");
                    custInventory(c);
                    }
                    else {
                    System.out.println("Login failed");
                    }
                    break;
                    case 2:
                    System.out.println("Enter User Name: ");
                    userName=sc.next();
                    System.out.println("Enter User Password with Upper Case, Lower Case, Digit, Special Character and minimum length of 8: ");
                    password=sc.next();
                    r=CustomerActivity.custSignUp(userName,password);
                    if(r==ConstantValues.USERNAME_ALREADY_PRESENT) {
                    System.out.println("User Name already present");
                    }
                    else if(r==ConstantValues.NEW_PW_WEAK) {
                    System.out.println("Weak password");
                    }
                    else if(r==ConstantValues.SIGN_UP_SUCESSFULL) {
                    System.out.println("Account created sucessfull");
                    }
                    break;
                    case 3:
                    System.out.println("Enter User Name: ");
                    userName=sc.next();
                    System.out.println("Enter Old Password: ");
                    password=sc.next();
                    System.out.println("Enter New Password with Upper Case, Lower Case, Digit, Special Character and minimum length of 8: ");
                    password1=sc.next();
                    System.out.println("Renter New Password: ");
                    password2=sc.next();
                    if(password1.equals(password2)) {
                    r=CustomerActivity.changePW(userName,password,password1);
                    }
                    else{
                    System.out.println("New passwords don't match");
                    break;
                    }
                    if(r==ConstantValues.USERNAME_ABSENT) {
                    System.out.println("Wrong User Name");
                    }
                    else if(r==ConstantValues.OLD_PW_WRONG) {
                    System.out.println("Old password is Wrong");
                    }
                    else if(r==ConstantValues.NEW_PW_WEAK) {
                    System.out.println("New password is Weak");
                    }
                    else if(r==ConstantValues.CHANGE_PW_SUCESSFULL) {
                    System.out.println("Password changed sucessfully");
                    }
                    break;
                    default:
                    break wl;
                }
        }
    } 

    static{
        Admin a1=new Admin();
        a1.setName("bharaths");
        a1.setPassword("Bhar123*");
        AdminActivity.addPeople(a1);
        }

    /**
     *This static method is for admin interface in SMBillingSystem.
     *
     */
    public static void admin(){
        wl:
        while(true){
                Scanner sc=new Scanner(System.in);
                int opt;
                String userName,password,password1,password2;
                int r=0;
                System.out.println();
                System.out.println("1.Admin Login 2.Admin Change Password 3.Exit");
                System.out.println();
                opt=sc.nextInt();

                switch(opt){
                    case 1:
                    System.out.println("Enter User Name: ");
                    userName=sc.next();
                    System.out.println("Enter User Password: ");
                    password=sc.next();
                    Admin a=(Admin)(AdminActivity.signIn(userName,password));
                    if(a!=null){
                    System.out.println("Login sucessfull");
                    adminInventory();
                    }
                    else {
                    System.out.println("Login failed");
                    }
                    break;
                    case 2:
                    System.out.println("Enter User Name: ");
                    userName=sc.next();
                    System.out.println("Enter Old Password: ");
                    password=sc.next();
                    System.out.println("Enter New Password with Upper Case, Lower Case, Digit, Special Character and minimum length of 8: ");
                    password1=sc.next();
                    System.out.println("Renter New Password: ");
                    password2=sc.next();
                    if(password1.equals(password2)) {
                    r=AdminActivity.changePW(userName,password,password1);
                    }
                    else{
                    System.out.println("New passwords dont match");
                    break;
                    }
                    if(r==ConstantValues.USERNAME_ABSENT) {
                    System.out.println("Wrong User Name");
                    }
                    else if(r==ConstantValues.OLD_PW_WRONG) {
                    System.out.println("Old password is Wrong");
                    }
                    else if(r==ConstantValues.NEW_PW_WEAK) {
                    System.out.println("New password is Weak");
                    }
                    else if(r==ConstantValues.CHANGE_PW_SUCESSFULL) {
                    System.out.println("Password changed sucessfully");
                    }
                    break;
                    default:
                    break wl;
                }
        }
    }

    /**
     *This static main method where the execution of the program starts.
     *
     */
    public static void main(String args[]){
    wl:
    while(true){
        Scanner sc=new Scanner(System.in);
        int opt;
        log.fine("\n");
        System.out.println("1.Customer 2.Administrator 3.Exit");
        System.out.println();
        opt=sc.nextInt();
        switch(opt){
            case 1:
            customer();
            break;
            case 2:
            admin();
            break;
            default:
            break wl;
        }
    }
    }
}