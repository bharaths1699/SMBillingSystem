package com.activity;
import com.storage.People;
import com.storage.Product;
import java.util.ArrayList;

/**
 *CommonActivity class has the methods which are commonly used for various operations of both customers and admins.
 *
 *@author Bharath S
 */
public class PeopleActivity{
    private static ArrayList<People> people=new ArrayList<People>();

    /**
     *This static method adds admins and customers to the list of people.
     *
     *@param com com is the object of common class.
     */
    public static void addPeople(People com){
        people.add(com);
    }

    /**
     *This static method returns the list of people i.e admins and customers.
     *
     *@return this method returns the arraylist of the people
     */
    public static ArrayList<People> getPeople(){
        return people;
    }

    /**
     *This static method returns common object which has the passed username.
     *
     *@param userName username is passed to get the common object with this username.
     *@return this method returns common object with passed username.
     */
    public static People getObj(String userName){
        for(People com : people){
            if(com.getName().equals(userName)){
                return com;
            }
        }
        return null;
    }    

    /**
     *This static method authenticates the common object and throws exception if there are errors.
     *
     *@param userName username is passed for authentication.
     *@param password password is passed for authentication.
     *@return this method returns common object if authentication is sucessfull else null is returned.
     */
    public static People signIn(String userName, String password){
        try{
        People com=getObj(userName);
        if(com==null) {
        throw new ObjectNotFoundException("This is user name is not found");  
        }

        String pw=com.getPassword();
        if(pw.equals(password)) {
        return com;
        }
        else {
        throw new WrongPWException("This is user name is not found");  
        }
        }

        catch(Exception e){
            System.out.println(e.getMessage());  
        }
        return null;
    }

    /**
     *This static method is used to change the password of common object.
     *
     *@param userName username is passed for authentication.
     *@param oldPW oldPW is used for authentication of common object before changing his password.
     *@param newPW newPW is used to set the new common object password.
     *@return this method returns constant integers based on the condition of passed parameters.
     */
    public static int changePW(String userName,String oldPW, String newPW){
        People com=getObj(userName);
        if(com==null) {
        return ConstantValues.USERNAME_ABSENT;
        }

        String pw=com.getPassword();
        if(!pw.equals(oldPW)) {
        return ConstantValues.OLD_PW_WRONG;
        }

        if(!checkPWStrength(newPW)) {
        return ConstantValues.NEW_PW_WEAK;
        }

        com.setPassword(newPW);
        return ConstantValues.CHANGE_PW_SUCESSFULL;
    }

    /**
     *This is static method and it checks password strength.
     *
     *@param password password is passed to check its strength.
     *@return this method returns true if password has good strength else it returns false.
     */
    public static boolean checkPWStrength(String password){
        boolean rulesBroken=true;
        
        if(password.length()<8) {
        return false;
        }

        for(int i=0;i<password.length();i++){
            if(password.charAt(i)>='A' && password.charAt(i)<='Z'){
                rulesBroken=false;
                break;
            }
        }
        
        if(rulesBroken) {
        return false;
        }

        rulesBroken=true;

        for(int i=0;i<password.length();i++){
            if(password.charAt(i)>='a' && password.charAt(i)<='z'){
                rulesBroken=false;
                break;
            }
        }

        if(rulesBroken) {
        return false;
        }

        rulesBroken=true;

        for(int i=0;i<password.length();i++){
            if(password.charAt(i)>='0' && password.charAt(i)<='9'){
                rulesBroken=false;
                break;
            }
        }

        if(rulesBroken) {
        return false;
        }

        rulesBroken=true;

        for(int i=0;i<password.length();i++){
            if(!(password.charAt(i)>='A' && password.charAt(i)<='Z') && !(password.charAt(i)>='a' && password.charAt(i)<='z') && !(password.charAt(i)>='0' && password.charAt(i)<='9')){
                rulesBroken=false;
                break;
            }
        }

        if(rulesBroken) {
        return false;
        }

        return true;
    }

    /**
     *This static method displays all the products in the Inventory, by calling the viewProducts method of the Inventory class.
     */
    public static ArrayList<Product> viewProducts(){
        return Inventory.viewProducts();
    }
}