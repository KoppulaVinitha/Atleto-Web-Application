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

public class GymException extends Exception {


		public GymException(String message)
		{
			super("GymException-"+message);
		}
		
		public GymException(String message, Throwable cause)
		{
			super("GymException-"+message,cause);
		}
		
	}


