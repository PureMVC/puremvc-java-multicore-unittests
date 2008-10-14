/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Matthieu Mauny <matthieu.mauny@puremvc.org>
 And Anthony Quinault <aquinault@gmail.com>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.command;

import org.junit.Assert;
import org.junit.Test;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.observer.Notification;

/**
 * Test the PureMVC SimpleCommand class.
 *
 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestVO MacroCommandTestVO
 * @see org.puremvc.java.multicore.patterns.command.MacroCommandTestCommand MacroCommandTestCommand
 */
public class MacroCommandTest {
	/**
	 * Tests operation of a <code>MacroCommand</code>.
	 * 
	 * <P>
	 * This test creates a new <code>Notification</code>, adding a 
	 * <code>MacroCommandTestVO</code> as the body. 
	 * It then creates a <code>MacroCommandTestCommand</code> and invokes
	 * its <code>execute</code> method, passing in the 
	 * <code>Notification</code>.<P>
	 * 
	 * <P>
	 * The <code>MacroCommandTestCommand</code> has defined an
	 * <code>initializeMacroCommand</code> method, which is 
	 * called automatically by its constructor. In this method
	 * the <code>MacroCommandTestCommand</code> adds 2 SubCommands
	 * to itself, <code>MacroCommandTestSub1Command</code> and
	 * <code>MacroCommandTestSub2Command</code>.
	 * 
	 * <P>
	 * The <code>MacroCommandTestVO</code> has 2 result properties,
	 * one is set by <code>MacroCommandTestSub1Command</code> by
	 * multiplying the input property by 2, and the other is set
	 * by <code>MacroCommandTestSub2Command</code> by multiplying
	 * the input property by itself. 
	 * 
	 * <P>
	 * Success is determined by evaluating the 2 result properties
	 * on the <code>MacroCommandTestVO</code> that was passed to 
	 * the <code>MacroCommandTestCommand</code> on the Notification 
	 * body.</P>
	 * 
	 */
	@Test
	public void testMacroCommandExecute() {

		// Create the VO
		MacroCommandTestVO vo = new MacroCommandTestVO(5);

		// Create the Notification (note)
		INotification note = new Notification("MacroCommandTest", vo, null);

		// Create the SimpleCommand  			
		MacroCommandTestCommand command = new MacroCommandTestCommand();

		// Execute the SimpleCommand
		command.execute(note);

		// test assertions
		Assert.assertEquals("Expecting vo.result1 == 10", vo.result1 , 10);
		Assert.assertEquals("Expecting vo.result2 == 25", vo.result2 , 25);

	}
}
