package com.activity;

/**
 *ObjectNotFound class extends Exception class and it is user defined exception.
 *
 *
 *@author Bharath S
 */
public class ObjectNotFoundException extends Exception  
{  
    public ObjectNotFoundException(String str)  
    { 
        super(str);  
    }  
}