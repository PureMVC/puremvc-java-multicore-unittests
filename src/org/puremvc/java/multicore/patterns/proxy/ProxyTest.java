/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.patterns.proxy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the PureMVC Proxy class.
 * 
 * @see org.puremvc.java.multicore.interfaces.IProxy IProxy
 * @see org.puremvc.java.multicore.patterns.proxy.Proxy Proxy
 */
public class ProxyTest {
	/**
	 * Tests getting the name using Proxy class accessor method. Setting can only be done in constructor.
	 */
	@Test
	public void testNameAccessor() {

		// Create a new Proxy and use accessors to set the proxy name 
		Proxy proxy = new Proxy("TestProxy");

		// test assertions
		Assert.assertEquals("Expecting proxy.getProxyName() == 'TestProxy'", proxy.getProxyName(), "TestProxy");
	}

	/**
	 * Tests setting and getting the data using Proxy class accessor methods.
	 */
	@Test
	public void testDataAccessors() {

		// Create a new Proxy and use accessors to set the data
		Proxy proxy = new Proxy("colors");
		proxy.setData(new String[] { "red", "green", "blue" });
		String[] data = (String[]) proxy.getData();

		// test assertions
		Assert.assertEquals("Expecting data.length == 3", data.length, 3);
		Assert.assertEquals("Expecting data[0] == 'red'", data[0], "red");
		Assert.assertEquals("Expecting data[1] == 'green'", data[1], "green");
		Assert.assertEquals("Expecting data[2] == 'blue'", data[2], "blue");
	}

	/**
	 * Tests setting the name and body using the Notification class Constructor.
	 */
	@Test
	public void testConstructor() {

		// Create a new Proxy using the Constructor to set the name and data
		Proxy proxy = new Proxy("colors", new String[] { "red", "green", "blue" });
		String[] data = (String[]) proxy.getData();

		// test assertions
		Assert.assertNotNull("Expecting proxy not null", proxy);
		Assert.assertEquals("Expecting proxy.getProxyName() == 'colors'", proxy.getProxyName(), "colors");
		Assert.assertEquals("Expecting data.length == 3", data.length, 3);
		Assert.assertEquals("Expecting data[0] == 'red'", data[0], "red");
		Assert.assertEquals("Expecting data[1] == 'green'", data[1], "green");
		Assert.assertEquals("Expecting data[2] == 'blue'", data[2], "blue");
	}
}
