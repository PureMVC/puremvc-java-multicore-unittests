/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
*/
package org.puremvc.java.multicore.patterns.facade;

	/**
	 * A utility class used by FacadeTest.
	 * 
	 * @see org.puremvc.java.multicore.patterns.facade.FacadeTest FacadeTest
	 * @see org.puremvc.java.multicore.patterns.facade.FacadeTestCommand FacadeTestCommand
  
	 */
public class FacadeTestVO {
	public int input;
	public int result;
	
	/**
	 * Constructor.
	 * 
	 * @param input
	 *            the number to be fed to the FacadeTestCommand
	 */
	public FacadeTestVO (int input){
		this.input = input;
	}

}
