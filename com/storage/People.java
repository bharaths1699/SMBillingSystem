package com.storage;

/**
 *Common class stores the details about the Admins and customers and it is inherited by both Admin and Customer classes.
 *
 *
 *@author Bharath S
 */
public class People{
    private String name;
    private String password;

    /**
     *This is setter method for name.
     *
     *@param id id is the user name passed to this setter.
     */
    public void setName(String id){
        this.name=id;
    }

    /**
     *This is getter method for name.
     *
     *@return this method returns the name of the object calling this getter.
     */
    public String getName(){
        return this.name;
    }

    /**
     *This is setter method for password.
     *
     *@param password password is the password passed to this setter.
     */
    public void setPassword(String password){
        this.password=password;
    }

    /**
     *This is getter method for password.
     *
     *@return this method returns the password of the object calling this getter.
     */
    public String getPassword(){
        return this.password;
    }
}