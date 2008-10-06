/*
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import org.junit.Assert;
import org.junit.Test;
import org.puremvc.java.multicore.core.view.View;
import org.puremvc.java.multicore.interfaces.IFunction;
import org.puremvc.java.multicore.interfaces.IMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.interfaces.IView;
import org.puremvc.java.multicore.patterns.mediator.Mediator;
import org.puremvc.java.multicore.patterns.observer.Notification;
import org.puremvc.java.multicore.patterns.observer.Observer;

/**
 * Test the PureMVC View class.
 */
public class ViewTest implements IFunction {
	public String lastNotification;

	public boolean onRegisterCalled = false;

	public boolean onRemoveCalled = false;

	public int counter = 0;

	public static final String NOTE1 = "Notification1";

	public static final String NOTE2 = "Notification2";

	public static final String NOTE3 = "Notification3";

	public static final String NOTE4 = "Notification4";

	public static final String NOTE5 = "Notification5";

	public static final String NOTE6 = "Notification6";

	/**
	 * Tests the View Multiton Factory Method 
	 */
	@Test
	public void testGetInstance() {

		// Test Factory Method
		IView view = View.getInstance("ViewTestKey1");

		// test assertions
		Assert.assertNotNull("Expecting instance not null", view);
		Assert.assertNotNull("Expecting instance implements IView", (IView) view);

	}

	/**
	 * Tests registration and notification of Observers.
	 * 
	 * <P>
	 * An Observer is created to callback the viewTestMethod of
	 * this ViewTest instance. This Observer is registered with
	 * the View to be notified of 'ViewTestEvent' events. Such
	 * an event is created, and a value set on its payload. Then
	 * the View is told to notify interested observers of this
	 * Event. 
	 * 
	 * <P>
	 * The View calls the Observer's notifyObserver method
	 * which calls the viewTestMethod on this instance
	 * of the ViewTest class. The viewTestMethod method will set 
	 * an instance variable to the value passed in on the Event
	 * payload. We evaluate the instance variable to be sure
	 * it is the same as that passed out as the payload of the 
	 * original 'ViewTestEvent'.
	 * 
	 */
	@Test
	public void testRegisterAndNotifyObserver() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey2");

		// Create observer, passing in notification method and context
		Observer observer = new Observer(this, this);

		// Register Observer's interest in a particulat Notification with the View 
		view.registerObserver(ViewTestNote.NAME, observer);

		// Create a ViewTestNote, setting 
		// a body value, and tell the View to notify 
		// Observers. Since the Observer is this class 
		// and the notification method is viewTestMethod,
		// successful notification will result in our local 
		// viewTestVar being set to the value we pass in 
		// on the note body.
		INotification note = ViewTestNote.create(10);
		view.notifyObservers(note);

		// test assertions  			
		Assert.assertEquals("Expecting viewTestVar = 10", viewTestVar, 10);
	}

	/**
	 * A test variable that proves the viewTestMethod was
	 * invoked by the View.
	 */
	private Integer viewTestVar;

	/**
	 * A utility method to test the notification of Observers by the view
	 */
	public void onNotification(INotification note) {
		// set the local viewTestVar to the number on the event payload
		viewTestVar = (Integer) note.getBody();
	}

	/**
	 * Tests registering and retrieving a mediator with
	 * the View.
	 */
	@Test
	public void testRegisterAndRetrieveMediator() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey3");

		// Create and register the test mediator
		ViewTestMediator viewTestMediator = new ViewTestMediator(this);
		view.registerMediator(viewTestMediator);

		// Retrieve the component
		IMediator mediator = (IMediator) view.retrieveMediator(ViewTestMediator.NAME);

		// test assertions  			
		Assert.assertNotNull("Expecting comp is ViewTestMediator", (ViewTestMediator) mediator);

	}

	/**
	 * Tests the hasMediator Method
	 */
	@Test
	public void testHasMediator() {

		// register a Mediator
		IView view = View.getInstance("ViewTestKey4");

		// Create and register the test mediator
		Mediator mediator = new Mediator("hasMediatorTest", this);
		view.registerMediator(mediator);

		// assert that the view.hasMediator method returns true
		// for that mediator name
		Assert.assertTrue("Expecting view.hasMediator('hasMediatorTest') == true", view.hasMediator("hasMediatorTest"));

		view.removeMediator("hasMediatorTest");

		// assert that the view.hasMediator method returns false
		// for that mediator name
		Assert.assertFalse("Expecting view.hasMediator('hasMediatorTest') == false", view.hasMediator("hasMediatorTest"));

	}

	/**
	 * Tests registering and removing a mediator 
	 */
	@Test
	public void testRegisterAndRemoveMediator() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey5");

		// Create and register the test mediator
		IMediator mediator = new Mediator("testing", this);
		view.registerMediator(mediator);

		// Remove the component
		IMediator removedMediator = (IMediator) view.removeMediator("testing");

		// assert that we have removed the appropriate mediator
		Assert.assertEquals("Expecting removedMediator.getMediatorName() == 'testing'", removedMediator.getMediatorName(), "testing");

		// assert that the mediator is no longer retrievable
		Assert.assertNull("Expecting view.retrieveMediator( 'testing' ) == null )", view.retrieveMediator("testing"));

	}

	/**
	 * Tests that the View callse the onRegister and onRemove methods
	 */
	@Test
	public void testOnRegisterAndOnRemove() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey6");

		// Create and register the test mediator
		IMediator mediator = new ViewTestMediator4(this);
		view.registerMediator(mediator);

		// assert that onRegsiter was called, and the mediator responded by setting our boolean
		Assert.assertTrue("Expecting onRegisterCalled == true", onRegisterCalled);

		// Remove the component
		view.removeMediator(ViewTestMediator4.NAME);

		// assert that the mediator is no longer retrievable
		Assert.assertTrue("Expecting onRemoveCalled == true", onRemoveCalled);

	}

	/**
	 * Tests successive regster and remove of same mediator.
	 */
	@Test
	public void testSuccessiveRegisterAndRemoveMediator() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey7");

		// Create and register the test mediator, 
		// but not so we have a reference to it
		view.registerMediator(new ViewTestMediator(this));

		// test that we can retrieve it
		Assert.assertNotNull("Expecting view.retrieveMediator( ViewTestMediator.NAME ) is ViewTestMediator", (ViewTestMediator) view
				.retrieveMediator(ViewTestMediator.NAME));

		// Remove the Mediator
		view.removeMediator(ViewTestMediator.NAME);

		// test that retrieving it now returns null			
		Assert.assertNull("Expecting view.retrieveMediator( ViewTestMediator.NAME ) == null", view.retrieveMediator(ViewTestMediator.NAME));

		// test that removing the mediator again once its gone doesn't cause crash 		
		Assert.assertNull("Expecting view.removeMediator( ViewTestMediator.NAME ) doesn't crash", view.removeMediator(ViewTestMediator.NAME));

		// Create and register another instance of the test mediator, 
		view.registerMediator(new ViewTestMediator(this));

		Assert.assertNotNull("Expecting view.retrieveMediator( ViewTestMediator.NAME ) is ViewTestMediator", (IMediator) view
				.retrieveMediator(ViewTestMediator.NAME));

		// Remove the Mediator
		view.removeMediator(ViewTestMediator.NAME);

		// test that retrieving it now returns null			
		Assert.assertNull("Expecting view.retrieveMediator( ViewTestMediator.NAME ) == null", view.retrieveMediator(ViewTestMediator.NAME));

	}

	/**
	 * Tests registering a Mediator for 2 different notifications, removing the
	 * Mediator from the View, and seeing that neither notification causes the
	 * Mediator to be notified. Added for the fix deployed in version 1.7
	 */
	@Test
	public void testRemoveMediatorAndSubsequentNotify() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey8");

		// Create and register the test mediator to be removed.
		view.registerMediator(new ViewTestMediator2(this));

		// test that notifications work
		view.notifyObservers(new Notification(NOTE1, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE1", lastNotification, NOTE1);

		view.notifyObservers(new Notification(NOTE2, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE2", lastNotification, NOTE2);

		// Remove the Mediator
		view.removeMediator(ViewTestMediator2.NAME);

		// test that retrieving it now returns null			
		Assert.assertNull("Expecting view.retrieveMediator( ViewTestMediator2.NAME ) == null", view.retrieveMediator(ViewTestMediator2.NAME));

		// test that notifications no longer work
		// (ViewTestMediator2 is the one that sets lastNotification
		// on this component, and ViewTestMediator)
		lastNotification = null;

		view.notifyObservers(new Notification(NOTE1, null, null));
		Assert.assertFalse("Expecting lastNotification != NOTE1", lastNotification == NOTE1);

		view.notifyObservers(new Notification(NOTE2, null, null));
		Assert.assertFalse("Expecting lastNotification != NOTE2", lastNotification == NOTE2);

	}

	/**
	 * Tests registering one of two registered Mediators and seeing
	 * that the remaining one still responds.
	 * Added for the fix deployed in version 1.7.1
	 */
	@Test
	public void testRemoveOneOfTwoMediatorsAndSubsequentNotify() {

		// Get the Multiton View instance
		IView view = View.getInstance("ViewTestKey9");

		// Create and register that responds to notifications 1 and 2
		view.registerMediator(new ViewTestMediator2(this));

		// Create and register that responds to notification 3
		view.registerMediator(new ViewTestMediator3(this));

		// test that all notifications work
		view.notifyObservers(new Notification(NOTE1, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE1", lastNotification, NOTE1);

		view.notifyObservers(new Notification(NOTE2, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE2", lastNotification, NOTE2);

		view.notifyObservers(new Notification(NOTE3, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE3", lastNotification, NOTE3);

		// Remove the Mediator that responds to 1 and 2
		view.removeMediator(ViewTestMediator2.NAME);

		// test that retrieving it now returns null			
		Assert.assertNull("Expecting view.retrieveMediator( ViewTestMediator2.NAME ) == null", view.retrieveMediator(ViewTestMediator2.NAME));

		// test that notifications no longer work
		// for notifications 1 and 2, but still work for 3
		lastNotification = null;

		view.notifyObservers(new Notification(NOTE1, null, null));
		Assert.assertFalse("Expecting lastNotification != NOTE1", lastNotification == NOTE1);

		view.notifyObservers(new Notification(NOTE2, null, null));
		Assert.assertFalse("Expecting lastNotification != NOTE2", lastNotification == NOTE2);

		view.notifyObservers(new Notification(NOTE3, null, null));
		Assert.assertEquals("Expecting lastNotification == NOTE3", lastNotification, NOTE3);

	}

	/**
	 * Tests registering the same mediator twice. 
	 * A subsequent notification should only illicit
	 * one response. Also, since reregistration
	 * was causing 2 observers to be created, ensure
	 * that after removal of the mediator there will
	 * be no further response.
	 * 
	 * Added for the fix deployed in version 2.0.4
	 */
	@Test
	public void testMediatorReregistration() {

		// Get the Singleton View instance
		IView view = View.getInstance("ViewTestKey10");

		// Create and register that responds to notification 5
		view.registerMediator(new ViewTestMediator5(this));

		// try to register another instance of that mediator (uses the same NAME constant).
		view.registerMediator(new ViewTestMediator5(this));

		// test that the counter is only incremented once (mediator 5's response) 
		counter = 0;
		view.notifyObservers(new Notification(NOTE5, null, null));
		Assert.assertEquals("Expecting counter == 1", 1, counter);

		// Remove the Mediator 
		view.removeMediator(ViewTestMediator5.NAME);

		// test that retrieving it now returns null			
		Assert.assertNull("Expecting view.retrieveMediator( ViewTestMediator5.NAME ) == null", view.retrieveMediator(ViewTestMediator5.NAME));

		// test that the counter is no longer incremented  
		counter = 0;
		view.notifyObservers(new Notification(NOTE5, null, null));
		Assert.assertEquals("Expecting counter == 0", 0, counter);
	}

	/**
	 * Tests the ability for the observer list to 
	 * be modified during the process of notification,
	 * and all observers be properly notified. This
	 * happens most often when multiple Mediators
	 * respond to the same notification by removing
	 * themselves.  
	 * 
	 * Added for the fix deployed in version 2.0.4
	 */
	@Test
	public void testModifyObserverListDuringNotification() {

		// Get the Singleton View instance
		IView view = View.getInstance("ViewTestKey11");

		// Create and register several mediator instances that respond to notification 6 
		// by removing themselves, which will cause the observer list for that notification 
		// to change. versions prior to MultiCore Version 2.0.5 will see every other mediator
		// fails to be notified.  
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/1", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/2", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/3", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/4", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/5", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/6", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/7", this));
		view.registerMediator(new ViewTestMediator6(ViewTestMediator6.NAME + "/8", this));

		// clear the counter
		counter = 0;
		// send the notification. each of the above mediators will respond by removing
		// themselves and incrementing the counter by 1. This should leave us with a
		// count of 8, since 8 mediators will respond.
		view.notifyObservers(new Notification(NOTE6, null, null));
		// verify the count is correct
		Assert.assertEquals("Expecting counter == 8", 8, counter);

		// clear the counter
		counter = 0;
		view.notifyObservers(new Notification(NOTE6, null, null));
		// verify the count is 0
		Assert.assertEquals("Expecting counter == 0", 0, counter);

	}
}
