/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.facade;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

/**
 * A SimpleCommand subclass used by FacadeTest.
 *
 * @see org.puremvc.java.multicore.patterns.facade.FacadeTest FacadeTest
 * @see org.puremvc.java.multicore.patterns.facade.FacadeTestVO FacadeTestVO
 */
public class FacadeTestCommand extends SimpleCommand {
	/**
	 * Constructor.
	 */
	public FacadeTestCommand() {
		super();
	}

	/**
	 * Fabricate a result by multiplying the input by 2
	 * 
	 * @param note the Notification carrying the FacadeTestVO
	 */
	@Override
	public void execute(INotification note) {

		FacadeTestVO vo = (FacadeTestVO) note.getBody();

		// Fabricate a result
		vo.result = 2 * vo.input;

	}
}
