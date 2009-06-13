/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.mediator;

import org.junit.Assert;
import org.junit.Test;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

/**
 * Test the PureMVC Mediator class.
 * 
 * @see org.puremvc.java.multicore.interfaces.IMediator IMediator
 * @see org.puremvc.java.multicore.patterns.mediator.Mediator Mediator
 */
public class MediatorTest {

		/**
		 * Tests getting the name using Mediator class accessor method. 
		 */
		@Test
		public void testNameAccessor() {

		// Create a new Mediator and use accessors to set the mediator name 
			Mediator mediator = new Mediator(null, null);
			
			// test assertions
			Assert.assertEquals( "Expecting mediator.getMediatorName() == Mediator.NAME", mediator.getMediatorName() , Mediator.NAME );
		}

		/**
		 * Tests getting the name using Mediator class accessor method. 
		 */
		@Test
		public void testViewAccessor(){

		// Create a view object
			Object view = new Object();
		
		// Create a new Proxy and use accessors to set the proxy name 
			Mediator mediator = new Mediator( Mediator.NAME, view );
		   			
			// test assertions
			Assert.assertNotNull( "Expecting mediator.getViewComponent() not null", mediator.getViewComponent() );
		}
}
