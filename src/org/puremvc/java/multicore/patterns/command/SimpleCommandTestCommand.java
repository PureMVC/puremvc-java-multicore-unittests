/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.command;

import org.puremvc.java.multicore.interfaces.INotification;

/**
 * A SimpleCommand subclass used by SimpleCommandTest.
 * 
 * @see org.puremvc.java.multicore.patterns.command.SimpleCommandTest
 *      SimpleCommandTest
 * @see org.puremvc.java.multicore.patterns.command.SimpleCommandTestVO
 *      SimpleCommandTestVO
 */
public class SimpleCommandTestCommand extends SimpleCommand {

	/**
	 * Constructor.
	 */
	public SimpleCommandTestCommand() {
		super();
	}

	/**
	 * Fabricate a result by multiplying the input by 2
	 * 
	 * @param event
	 *            the <code>INotification</code> carrying the
	 *            <code>SimpleCommandTestVO</code>
	 */
	@Override
	public void execute(INotification note) {

		SimpleCommandTestVO vo = (SimpleCommandTestVO) note.getBody();

		// Fabricate a result
		vo.result = 2 * vo.input;

	}
}
