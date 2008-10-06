/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import junit.framework.Assert;

import org.junit.Test;
import org.puremvc.java.multicore.core.controller.Controller;
import org.puremvc.java.multicore.core.view.View;
import org.puremvc.java.multicore.interfaces.IController;
import org.puremvc.java.multicore.interfaces.IView;
import org.puremvc.java.multicore.patterns.observer.Notification;

/**
 * Test des communication entre ai.
 */
public class ControllerTest {

	/**
	 * Tests the Controller Multiton Factory Method 
	 */
	@Test
	public void testGetInstance() {

		// Test Factory Method
		IController controller = Controller.getInstance("ControllerTestKey1");

		// test assertions
		Assert.assertTrue("Expecting instance not null", controller != null);
		Assert.assertTrue("Expecting instance implements IController", (IController) controller != null);
	}

	/**
	 * Tests Command registration and execution.
	 * 
	 * <P>
	 * This test gets a Multiton Controller instance 
	 * and registers the ControllerTestCommand class 
	 * to handle 'ControllerTest' Notifications.<P>
	 * 
	 * <P>
	 * It then constructs such a Notification and tells the 
	 * Controller to execute the associated Command.
	 * Success is determined by evaluating a property
	 * on an object passed to the Command, which will
	 * be modified when the Command executes.</P>
	 * 
	 */
	@Test
	public void testRegisterAndExecuteCommand() {

		// Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
		IController controller = Controller.getInstance("ControllerTestKey2");
		controller.registerCommand("ControllerTest", new ControllerTestCommand());

		// Create a 'ControllerTest' note
		ControllerTestVO vo = new ControllerTestVO(12);
		Notification note = new Notification("ControllerTest", vo, null);

		// Tell the controller to execute the Command associated with the note
		// the ControllerTestCommand invoked will multiply the vo.input value
		// by 2 and set the result on vo.result
		controller.executeCommand(note);

		// test assertions 
		Assert.assertTrue("Expecting vo.result == 24", vo.result == 24);
	}

	/**
	 * Tests Command registration and removal.
	 * 
	 * <P>
	 * Tests that once a Command is registered and verified
	 * working, it can be removed from the Controller.</P>
	 */
	@Test
	public void testRegisterAndRemoveCommand() {

		// Create the controller, register the ControllerTestCommand to handle 'ControllerTest' notes
		IController controller = Controller.getInstance("ControllerTestKey3");
		controller.registerCommand("ControllerRemoveTest", new ControllerTestCommand());

		// Create a 'ControllerTest' note
		ControllerTestVO vo = new ControllerTestVO(12);
		Notification note = new Notification("ControllerRemoveTest", vo, null);

		// Tell the controller to execute the Command associated with the note
		// the ControllerTestCommand invoked will multiply the vo.input value
		// by 2 and set the result on vo.result
		controller.executeCommand(note);

		// test assertions 
		Assert.assertTrue("Expecting vo.result == 24", vo.result == 24);

		// Reset result
		vo.result = 0;

		// Remove the Command from the Controller
		controller.removeCommand("ControllerRemoveTest");

		// Tell the controller to execute the Command associated with the
		// note. This time, it should not be registered, and our vo result
		// will not change   			
		controller.executeCommand(note);

		// test assertions 
		Assert.assertTrue("Expecting vo.result == 0", vo.result == 0);

	}

	/**
	 * Test hasCommand method.
	 */
	@Test
	public void testHasCommand() {

		// register the ControllerTestCommand to handle 'hasCommandTest' notes
		IController controller = Controller.getInstance("ControllerTestKey4");
		controller.registerCommand("hasCommandTest", new ControllerTestCommand());

		// test that hasCommand returns true for hasCommandTest notifications 
		Assert.assertTrue("Expecting controller.hasCommand('hasCommandTest') == true", controller.hasCommand("hasCommandTest") == true);

		// Remove the Command from the Controller
		controller.removeCommand("hasCommandTest");

		// test that hasCommand returns false for hasCommandTest notifications 
		Assert.assertTrue("Expecting controller.hasCommand('hasCommandTest') == false", controller.hasCommand("hasCommandTest") == false);

	}

	/**
	 * Tests Removing and Reregistering a Command
	 * 
	 * <P>
	 * Tests that when a Command is re-registered that it isn't fired twice.
	 * This involves, minimally, registration with the controller but
	 * notification via the View, rather than direct execution of
	 * the Controller's executeCommand method as is done above in 
	 * testRegisterAndRemove. The bug under test was fixed in AS3 Standard 
	 * Version 2.0.2. If you run the unit tests with 2.0.1 this
	 * test will fail.</P>
	 */
	@Test
	public void testReregisterAndExecuteCommand() {

		// Fetch the controller, register the ControllerTestCommand2 to handle 'ControllerTest2' notes
		IController controller = Controller.getInstance("ControllerTestKey5");
		controller.registerCommand("ControllerTest2", new ControllerTestCommand2());

		// Remove the Command from the Controller
		controller.removeCommand("ControllerTest2");

		// Re-register the Command with the Controller
		controller.registerCommand("ControllerTest2", new ControllerTestCommand2());

		// Create a 'ControllerTest2' note
		ControllerTestVO vo = new ControllerTestVO(12);
		Notification note = new Notification("ControllerTest2", vo, null);

		// retrieve a reference to the View from the same core.
		IView view = View.getInstance("ControllerTestKey5");

		// send the Notification
		view.notifyObservers(note);

		// test assertions 
		// if the command is executed once the value will be 24
		Assert.assertEquals("Expecting vo.result == 24", vo.result, 24);

		// Prove that accumulation works in the VO by sending the notification again
		view.notifyObservers(note);

		// if the command is executed twice the value will be 48
		Assert.assertEquals("Expecting vo.result == 48", vo.result, 48);

	}
}
