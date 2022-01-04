package com.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Collections;
import com.storage.People;
import com.storage.Customer;
import com.storage.Product;
import com.activity.Inventory;
import com.activity.PeopleActivity;
import com.activity.CompareProducts;
import com.activity.ConstantValues;
import com.storage.Bill;
import java.util.Scanner;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import java.io.File;
import java.io.FileOutputStream;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *CustomerActivity class has the methods which are used for operations of customers and it inherits the CommonActivity class.
 *
 *@author Bharath S
 */
public class CustomerActivity extends PeopleActivity{
    private static Scanner sc=new Scanner(System.in);
    private static ArrayList<Customer> newSignUp=new ArrayList<Customer>();

    /**
     *This static method adds new customer.
     *
     *@param userName username of new customer is passed for adding.
     *@param password password of new customer is passed for adding.
     *@return this method returns constant integers based on the condition of passed parameters.
     */
    public static int custSignUp(String userName, String password){
        Customer c=(Customer)(getObj(userName));
        if(c!=null) {
        return ConstantValues.USERNAME_ALREADY_PRESENT;
        }

        if(!checkPWStrength(password)) {
        return ConstantValues.NEW_PW_WEAK;
        }

        Customer newCust=new Customer();
        newCust.setName(userName);
        newCust.setPassword(password);
        newCust.setBalance(500);
        addPeople(newCust);
        newSignUp.add(newCust);
        return ConstantValues.SIGN_UP_SUCESSFULL;
    }

    /**
     *This static method returns arraylist of new customers.
     *
     *@return this method returns constant integers based on the condition of passed parameters.
     */
    public static ArrayList<Customer> viewNewSignUp(){
        return newSignUp;
    }

    /**
     *This static method removes customer from new sign up list after he complete a purchase.
     *
     *@param c c is the customer object who has done a purchase
     */
    public static void removeNewSignUp(Customer c){
        int index=newSignUp.indexOf(c);
        newSignUp.remove(index);
    }
    
    /**
     *This static method displays all the products in the ascending order of price present in Inventory.
     *
     *@param print this parameter takes the boolean value, if value is true then it prints in ascending order else doesn't print(when used to print in descending order).
     *@return this method returns ArrayList of products in ascending order of their price.
     */
    public static ArrayList<Product> viewProductsAsc(){
        ArrayList<Product> prods=new ArrayList<Product>(Inventory.getProductsList());
        Comparator<Product> compProd=new CompareProducts();
        Collections.sort(prods,compProd);
        return prods;
    }

    /**
     *This static method displays all the products in the descending order of price present in Inventory.
     */
    public static ArrayList<Product> viewProductsDsc(){
        ArrayList<Product> prods=viewProductsAsc();
        Collections.reverse(prods);
        return prods;
    }

    /**
     *This static method searches the product using its name, this uses searchProductByName method of Inventory class.
     *
     *@param name name is the string parameter based on which name search takes place.
     *@return this method returns Product object which has this name.
     */
    public static Product searchProductByName(String name){
        return Inventory.searchProductByName(name);
    }

    /**
     *This static method searches the product using its price, this uses searchProductByPrice method of Inventory class.
     *
     *@param price price is the string parameter based on which price search takes place.
     *@return this method returns Product object which has this price.
     */
    public static Product searchProductByPrice(int price){
        return Inventory.searchProductByPrice(price);
    }

    /**
     *This static method adds new product to cart class based on customer demand.
     *
     *@param id id is product id which needs to be added to the cart.
     *@param quantity quantity of product that customer wishes to add to cart.
     *@param c cis customer object whose cart needs to be added with new item or old item needs to be updated.
     *@return this method returns constant integers based on the method execution.
     */
    public static int addCart(int id, int quantity, Customer c){
        int checkIDQuantityResult=Inventory.checkIDQuantity(id,quantity);
        if(checkIDQuantityResult==ConstantValues.CORRECT_CART_QUANTITY){
            if(c.getCart().containsKey(id)) {
            c.getCart().replace(id,quantity);
            }
            else {
            c.getCart().put(id,quantity);
            }
            return ConstantValues.ADDED_TO_CART;
        }
        else {
        return checkIDQuantityResult;
        }
    }

    /**
     *This static method displays all the items in the customers cart based on the customer object passed.
     *
     *@param c c is customer object whose cart needs to be viewed.
     */
    public static HashMap<Integer,Integer> viewCart(Customer c){
        return c.getCart();
    }

    /**
     *This static method is to send bill pdf attachement to the customer.
     *
     *@param to to is the string variable containing email id of the customer.
     */
    private static void sendAttach(String to) {
		//Variable for gmail
        String message="Thank you, your purchase sucessfull. Your Bill is in the attachement";
        String subject="Purchase and Billing sucessfull";
        String from="emailID";
		String host="smtp.gmail.com";
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		//Step 1: to get the session object..
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("emailID", "pw");
			}
		});
		session.setDebug(true);
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		try {
		//from email
		m.setFrom(from);
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		//adding subject to message
		m.setSubject(subject);
		//attachement..
		//file path
		String path="D:\\companies\\Zoho\\Training\\ConsoleApp\\SMBillingSystem\\Bill.pdf";
		MimeMultipart mimeMultipart = new MimeMultipart();
		//text
		//file
		MimeBodyPart textMime = new MimeBodyPart();
		MimeBodyPart fileMime = new MimeBodyPart();
		try {
			textMime.setText(message);
			File file=new File(path);
			fileMime.attachFile(file);
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);	
		} catch (Exception e) {
			//e.printStackTrace();
		}
		m.setContent(mimeMultipart);
		//send 
		//Step 3 : send the message using Transport class
		Transport.send(m);
		}
        catch (Exception e) {
			//e.printStackTrace();
		}
		System.out.println("Bill mailed successfully");
	}

    /**
     *This static method is used to create a Bill pdf of a customer after the billing is complete and he wants the bill to be sent to his mail.
     *
     *@param bill bill is the arraylist of string which contains the bill details.
     */
    private static void createBillPdf(ArrayList<String> bill){
        try{
            String to;
            int r;
            File file=new File("D:\\companies\\Zoho\\Training\\ConsoleApp\\SMBillingSystem\\Bill.pdf");
            file.delete();
            Document document=new Document();
            PdfWriter.getInstance(document,new FileOutputStream("Bill.pdf"));
            document.open();
            for(String s : bill){
            document.add(new Paragraph(s));
            }
            document.close();
            System.out.println("1.Send my Bill through Email \n2.Exit");
            r=sc.nextInt();
            if(r==1){
                System.out.println("Enter your Email ID: ");
                to=sc.next();
                sendAttach(to);
            }
        }
        catch(Exception e){
        }
    }

    /**
     *This static method displays the bill after being set.
     *
     *@param c c is customer object whose bill needs to be printed.
     */
    public static void printBill(Customer c){
        Bill b=new Bill();
        b.setBill(c.getCart(),c);
        int bal=(c.getBalance())-(b.getTotal());
        if(b.getTotal()==0){
        System.out.println("No products added to cart");
        return;
        }
        else if(bal<0){
            System.out.println("Your balance is low so the payment is not complete");
            return;
        }
         else
        {
            Inventory.updateProductListFile();
            System.out.println("Billing sucessfull");
            c.setBalance(bal);
            System.out.println("Do you want to Cancel Billing, press yes or no: ");
            String str=sc.next();
            if(str.equals("yes")){
                System.out.println("Billing cancelled sucessfully");
                c.setBalance(bal+b.getTotal());
                return;
            }
            removeNewSignUp(c);
            c.addToBillList(b);
            c.getCart().clear();
            for(String str1 : b.getList()) {
            System.out.println(str1);
            }
            createBillPdf(b.getList());
        }
    }

    /**
     *This static method updates the cart after it products being updated.
     *
     *@param p p is product which has been updated.
     */
    public static void productUpdatedUpdateCarts(Product p){
        int id=p.getProdID();
        int quantity=p.getProdQuantity();
        for(People com : PeopleActivity.getPeople()){
            if(com instanceof Customer){
                Customer c=(Customer)(com);
                HashMap<Integer,Integer> cart=c.getCart();
                if(cart.containsKey(id)){
                    if(cart.get(id)>quantity) {
                        cart.remove(id);
                    }
                }
            }
        }
    }

    /**
     *This static method is to get the bill list of a customer that needs to be displayed.
     *
     *@param c c is customer object whose bill list needs to be viewed.
     */
    public static ArrayList<Bill> viewMyCustomerDetails(Customer c){
        return c.getBillList();
    }

    /**
     *This static method is to get the customer objects to view their details.
     *
     *@return this method returns customer object whose details needs to be displayed.
     */
    public static ArrayList<People> viewCustomerDetails(){
        return PeopleActivity.getPeople();
    }

    /**
     *This static method is to update customer balance.
     *@param id id is customer user name whose balance needs to be updated.
     *@param balance balance is the new balance that is to be updated to the customer id
     *
     *@return this method returns constant integers based on the method execution.
     */
    public static int updateCustomerBalance(String id,int balance){
        if(balance<0) {
        return ConstantValues.BALANCE_UPDATE_WRONG_BALANCE;
        }
        for(People com : PeopleActivity.getPeople()){
            if(com instanceof Customer){
                Customer c=(Customer)(com);
                if(c.getName().equals(id)){
                    c.setBalance(balance);
                    return ConstantValues.BALANCE_UPDATED;
                }
            }
        }
        return ConstantValues.BALANCE_UPDATE_ID_NOT_FOUND;
    }

    /**
     *This static method is to view the list of all bills of customer c.
     *
     *@param c c is customer object whose list of bills should be viewed.
     */
    public static void viewAllMyBills(Customer c){
        for(Bill b:c.getBillList()){
            for(String s : b.getList()){
                System.out.println(s);
                System.out.println();
            }
        }
    }

     /**
     *This static getter method is of total discount earned by a customer.
     *
     *@param c c is customer object whose total discounts earned is to be viewed.
     */
    public static int getTotalDiscount(Customer c){
        return c.getTotalDiscount();
    }

    /**
     *This static getter method is of balance of a customer.
     *
     *@param c c is customer object whose total discounts earned is to be viewed.
     */
    public static int getBalance(Customer c){
        return c.getBalance();
    }

    /**
     *This static method is to view the top two customers.
     *
     *@return here we are returning the list of customers in decreasing order of their loyal points.
     */
    public static ArrayList<Customer> viewTopCustomers(){
        ArrayList<Customer> custList=new ArrayList<Customer>();
        for(People com : PeopleActivity.getPeople()){
            if(com instanceof Customer){
                Customer c=(Customer)(com);
                custList.add(c);
            }
        }
        Comparator<Customer> compCusts=new CompareCustomers();
        Collections.sort(custList,compCusts);

        return custList;
    }
}