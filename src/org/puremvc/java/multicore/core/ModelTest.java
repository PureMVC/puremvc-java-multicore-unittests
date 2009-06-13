/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import junit.framework.Assert;

import org.junit.Test;
import org.puremvc.java.multicore.core.model.Model;
import org.puremvc.java.multicore.interfaces.IModel;
import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

/**
 * Test the PureMVC Model class.
 */
public class ModelTest {

	/**
	 * Tests the Model Multiton Factory Method 
	 */
	@Test
	public void testGetInstance() {
		// Test Factory Method
		IModel model = Model.getInstance("ModelTestKey1");

		// test assertions
		Assert.assertNotNull("Expecting instance not null", model);
		Assert.assertNotNull("Expecting instance implements IModel", (IModel) model);
	}

	/**
	 * Tests the proxy registration and retrieval methods.
	 * 
	 * <P>
	 * Tests <code>registerProxy</code> and <code>retrieveProxy</code> in the same test.
	 * These methods cannot currently be tested separately
	 * in any meaningful way other than to show that the
	 * methods do not throw exception when called. </P>
	 */
	@Test
	public void testRegisterAndRetrieveProxy() {

		// register a proxy and retrieve it.
		IModel model = Model.getInstance("ModelTestKey2");
		model.registerProxy(new Proxy("colors", new String[] { "red", "green", "blue" }));
		Proxy proxy = (Proxy) model.retrieveProxy("colors");
		String[] data = (String[]) proxy.getData();

		// test assertions
		Assert.assertNotNull("Expecting data not null", data);
		Assert.assertNotNull("Expecting data type is Array", (String[]) data);
		Assert.assertEquals("Expecting data.length == 3", data.length, 3);
		Assert.assertEquals("Expecting data[0] == 'red'", data[0], "red");
		Assert.assertEquals("Expecting data[1] == 'green'", data[1], "green");
		Assert.assertEquals("Expecting data[2] == 'blue'", data[2], "blue");
	}

	/**
	 * Tests the proxy removal method.
	 */
	@Test
	public void testRegisterAndRemoveProxy() {

		// register a proxy, remove it, then try to retrieve it
		IModel model = Model.getInstance("ModelTestKey");
		IProxy proxy = new Proxy("sizes", new String[] { "7", "13", "21" });
		model.registerProxy(proxy);

		// remove the proxy
		IProxy removedProxy = model.removeProxy("sizes");

		// assert that we removed the appropriate proxy
		Assert.assertEquals("Expecting removedProxy.getProxyName() == 'sizes'", removedProxy.getProxyName() , "sizes");

		// ensure that the proxy is no longer retrievable from the model
		proxy = model.retrieveProxy("sizes");

		// test assertions
		Assert.assertNull("Expecting proxy is null", proxy);
	}

	/**
	 * Tests the hasProxy Method
	 */
	@Test
	public void testHasProxy() {

		// register a proxy
		IModel model = Model.getInstance("ModelTestKey4");
		IProxy proxy = new Proxy("aces", new String[] { "clubs", "spades", "hearts", "diamonds" });
		model.registerProxy(proxy);

		// assert that the model.hasProxy method returns true
		// for that proxy name
		Assert.assertTrue("Expecting model.hasProxy('aces') == true", model.hasProxy("aces"));

		// remove the proxy
		model.removeProxy("aces");

		// assert that the model.hasProxy method returns false
		// for that proxy name
		Assert.assertFalse("Expecting model.hasProxy('aces') == false", model.hasProxy("aces"));
	}

	/**
	 * Tests that the Model calls the onRegister and onRemove methods
	 */
	public void testOnRegisterAndOnRemove() {

		// Get a Multiton View instance
		IModel model = Model.getInstance("ModelTestKey4");

		// Create and register the test mediator
		IProxy proxy = new ModelTestProxy();
		model.registerProxy(proxy);

		// assert that onRegsiter was called, and the proxy responded by setting its data accordingly
		Assert.assertEquals("Expecting proxy.getData() == ModelTestProxy.ON_REGISTER_CALLED", proxy.getData(), ModelTestProxy.ON_REGISTER_CALLED);

		// Remove the component
		model.removeProxy(ModelTestProxy.NAME);

		// assert that onRemove was called, and the proxy responded by setting its data accordingly
		Assert.assertEquals("Expecting proxy.getData() == ModelTestProxy.ON_REMOVE_CALLED", proxy.getData(), ModelTestProxy.ON_REMOVE_CALLED);

	}

}
