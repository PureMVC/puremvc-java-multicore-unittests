/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Matthieu Mauny <matthieu.mauny@puremvc.org>
 And Anthony Quinault <aquinault@gmail.com>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.observer;

import org.junit.Assert;
import org.junit.Test;
import org.puremvc.java.multicore.interfaces.INotification;

/**
 * Test the PureMVC Notification class.
 * 
 * @see org.puremvc.java.multicore.patterns.observer.Notification Notification
 */
public class NotificationTest {
	/**
	 * Tests setting and getting the name using Notification class accessor
	 * methods.
	 */
	@Test
	public void testNameAccessors() {

		// Create a new Notification and use accessors to set the note name
		INotification note = new Notification("TestNote");

		// test assertions
		Assert.assertEquals("Expecting note.getName() == 'TestNote'", note.getName(), "TestNote");
	}

	/**
	 * Tests setting and getting the body using Notification class accessor
	 * methods.
	 */
	@Test
	public void testBodyAccessors() {

		// Create a new Notification and use accessors to set the body
		INotification note = new Notification(null);
		note.setBody(5);

		// test assertions
		Assert.assertEquals("Expecting note.getBody()as Number == 5", (Integer) note.getBody(), 5);
	}

	/**
	 * Tests setting the name and body using the Notification class Constructor.
	 */
	@Test
	public void testConstructor() {

		// Create a new Notification using the Constructor to set the note name
		// and body
		INotification note = new Notification("TestNote", 5, "TestNoteType");

		// test assertions
		Assert.assertEquals("Expecting note.getName() == 'TestNote'", note.getName(), "TestNote");
		Assert.assertEquals("Expecting note.getBody()as Number == 5", (Integer) note.getBody(), 5);
		Assert.assertEquals("Expecting note.getType() == 'TestNoteType'", note.getType(), "TestNoteType");
	}

	/**
	 * Tests the toString method of the notification
	 */
	@Test
	public void testToString() {

		// Create a new Notification and use accessors to set the note name
		INotification note = new Notification("TestNote", "1,3,5", "TestType");
		String ts = "Notification Name: TestNote Body:1,3,5 Type:TestType";

		// test assertions
		Assert.assertEquals("Expecting note.testToString() == '" + ts + "'", note.toString(), ts);
	}
}
