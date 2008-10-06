/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
*/
package org.puremvc.java.multicore.patterns.command;

	/**
	 * A utility class used by MacroCommandTest.
	 * 
	 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTest MacroCommandTest
	 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestCommand MacroCommandTestCommand
	 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestSub1Command MacroCommandTestSub1Command
	 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestSub2Command MacroCommandTestSub2Command
	 */
public class MacroCommandTestVO {
	
	public int input;
	public int result1;
	public int result2;
	
	/**
	 * Constructor.
	 * 
	 * @param input the number to be fed to the MacroCommandTestCommand
	 */
	public MacroCommandTestVO (int input){
		this.input = input;
	}

}
