/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.command;

import org.puremvc.java.multicore.interfaces.INotification;

/**
 * A SimpleCommand subclass used by MacroCommandTestCommand.
 *
 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTest MacroCommandTest
 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestCommand MacroCommandTestCommand
 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestVO MacroCommandTestVO
 */
public class MacroCommandTestSub1Command extends SimpleCommand {

	public MacroCommandTestSub1Command()
	{
		super();
	}
	
	/**
	 * Fabricate a result by multiplying the input by 2
	 * 
	 * @param event the <code>IEvent</code> carrying the <code>MacroCommandTestVO</code>
	 */
	@Override 
	public void execute( INotification note ) 
	{
		
		MacroCommandTestVO vo = (MacroCommandTestVO)note.getBody();
		
		// Fabricate a result
		vo.result2 = vo.input * vo.input;

	}
}
