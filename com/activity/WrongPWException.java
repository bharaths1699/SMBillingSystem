package com.activity;

/**
 *WrongPWException class extends Exception class and it is user defined exception.
 *
 *
 *@author Bharath S
 */
public class WrongPWException extends Exception  
{  
    public WrongPWException(String str)  
    { 
        super(str);  
    }  
}