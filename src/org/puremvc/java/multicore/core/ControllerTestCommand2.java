/*
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
public class ControllerTestCommand2 extends SimpleCommand {
	
	/**
	 * Constructor.
	 */
	public ControllerTestCommand2()
	{
		super();
	}
	
	/**
	 * Fabricate a result by multiplying the input by 2 and adding to the existing result
	 * <P>
	 * This tests accumulation effect that would show if the command were executed more than once.
	 * @param note the note carrying the ControllerTestVO
	 */
	@Override 
	public void execute( INotification note )
	{
		
		ControllerTestVO vo = (ControllerTestVO)note.getBody();
		
		// Fabricate a result
		vo.result = vo.result+(2 * vo.input);

	}
}
