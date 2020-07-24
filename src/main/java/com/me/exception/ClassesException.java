/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.exception;

/**
 *
 * @author koppu
 */

public class ClassesException extends Exception {

	public ClassesException(String message)
	{
		super("ClassesException-"+message);
	}
	
	public ClassesException(String message, Throwable cause)
	{
		super("ClassesException-"+message,cause);
	}
	
}

