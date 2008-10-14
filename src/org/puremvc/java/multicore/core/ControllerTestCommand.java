/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Matthieu Mauny <matthieu.mauny@puremvc.org>
 And Anthony Quinault <aquinault@gmail.com>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

/**
 * A SimpleCommand subclass used by ControllerTest.
 *
	 * @see org.puremvc.java.multicore.core.controller.ControllerTest ControllerTest
	 * @see org.puremvc.java.multicore.core.controller.ControllerTestVO ControllerTestVO
 */
public class ControllerTestCommand extends SimpleCommand {
	
	/**
	 * Constructor.
	 */
	public ControllerTestCommand()
	{
		super();
	}
	
	/**
	 * Fabricate a result by multiplying the input by 2
	 * 
	 * @param note the note carrying the ControllerTestVO
	 */
	@Override
	public void execute( INotification note )
	{
		
		ControllerTestVO vo = (ControllerTestVO)note.getBody();
		
		// Fabricate a result
		vo.result = 2 * vo.input;

	}
}
