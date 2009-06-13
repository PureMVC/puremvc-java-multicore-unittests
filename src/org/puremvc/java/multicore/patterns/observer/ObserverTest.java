/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.observer;

import org.junit.Assert;
import org.junit.Test;
import org.puremvc.java.multicore.interfaces.IFunction;
import org.puremvc.java.multicore.interfaces.INotification;

	/**
	 * Tests PureMVC Observer class.
	 * 
	 * <P>
	 * Since the Observer encapsulates the interested object's
	 * callback information, there are no getters, only setters. 
	 * It is, in effect write-only memory.</P>
	 * 
	 * <P>
	 * Therefore, the only way to test it is to set the 
	 * notification method and context and call the notifyObserver
	 * method.</P>
	 * 
	 */
public class ObserverTest {


	/**
	 * A test variable that proves the notify method was
	 * executed with 'this' as its exectution context
	 */
	private int observerTestVar;
	
	/**
		 * Tests observer class when initialized by accessor methods.
		 * 
		 */
	@Test
		public void testObserverAccessors() {
			
			// Create observer with null args, then
			// use accessors to set notification method and context
		Observer observer = new Observer(null,null);
		observer.setNotifyContext(this);
			observer.setNotifyMethod(new ObserverTestFunction());
			
			// create a test event, setting a payload value and notify 
			// the observer with it. since the observer is this class 
			// and the notification method is observerTestMethod,
			// successful notification will result in our local 
			// observerTestVar being set to the value we pass in 
			// on the note body.
			Notification note = new Notification("ObserverTestNote",10);
		observer.notifyObserver(note);

		// test assertions  			
			Assert.assertEquals( "Expecting observerTestVar = 10", observerTestVar , 10 );
		}

		/**
		 * Tests observer class when initialized by constructor.
		 * 
		 */
	@Test
		public void testObserverConstructor() {
			
			// Create observer passing in notification method and context
			Observer observer = new Observer(new ObserverTestFunction(),this);
			
			// create a test note, setting a body value and notify 
			// the observer with it. since the observer is this class 
			// and the notification method is observerTestMethod,
			// successful notification will result in our local 
			// observerTestVar being set to the value we pass in 
			// on the note body.
			Notification note = new Notification("ObserverTestNote",5);
		observer.notifyObserver(note);

		// test assertions  			
			Assert.assertEquals( "Expecting observerTestVar = 5", observerTestVar , 5 );
		}

		/**
		 * Tests the compareNotifyContext method of the Observer class
		 * 
		 */
	@Test
		public void testCompareNotifyContext() {
			
			// Create observer passing in notification method and context
			Observer observer = new Observer( new ObserverTestFunction(), this );
			
			Object negTestObj = new Object();
			
		// test assertions  			
			Assert.assertFalse("Expecting observer.compareNotifyContext(negTestObj) == false", observer.compareNotifyContext(negTestObj) );
			Assert.assertTrue( "Expecting observer.compareNotifyContext(this) == true", observer.compareNotifyContext(this) );
		}

		
		/**
		 * A function that is used as the observer notification
		 * method. It multiplies the input number by the 
		 * observerTestVar value
		 */

		private class ObserverTestFunction implements IFunction{

			public void onNotification(INotification notification) {
				observerTestVar = (Integer)notification.getBody();
				
			}

		}
}
