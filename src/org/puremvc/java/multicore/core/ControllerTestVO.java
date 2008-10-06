/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
*/
package org.puremvc.java.multicore.core;

/**
* A utility class used by ControllerTest.
* 
* @see org.puremvc.java.multicore.core.controller.ControllerTest ControllerTest
* @see org.puremvc.java.multicore.core.controller.ControllerTestCommand ControllerTestCommand
*/
public class ControllerTestVO {
	
	public int input = 0;
	public int result = 0;
	
	/**
	 * Constructor.
	 * 
	 * @param input the number to be fed to the ControllerTestCommand
	 */
	public ControllerTestVO (int input){
		this.input = input;
	}

}
