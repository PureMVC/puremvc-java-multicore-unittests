/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.command;

/**
 * A utility class used by SimpleCommandTest.
 * 
 * @see org.puremvc.java.multicore.patterns.command.SimpleCommandTest SimpleCommandTest
 * @see org.puremvc.java.multicore.patterns.command.SimpleCommandTestCommand SimpleCommandTestCommand
 */
public class SimpleCommandTestVO {

	public int input;

	public int result;

	/**
	 * Constructor.
	 * 
	 * @param input the number to be fed to the SimpleCommandTestCommand
	 */
	public SimpleCommandTestVO(int input) {
		this.input = input;
	}

}
