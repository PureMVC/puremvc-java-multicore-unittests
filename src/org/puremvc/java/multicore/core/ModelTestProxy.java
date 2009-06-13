/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Anthony Quinault <anthony.quinault@puremvc.org>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class ModelTestProxy extends Proxy {
	
	public static final String NAME = "ModelTestProxy";
	public static final String ON_REGISTER_CALLED = "onRegister Called";
	public static final String ON_REMOVE_CALLED = "onRemove Called";

	public ModelTestProxy()
	{
		super(NAME, "");
	}

	@Override 
	public void onRegister()
	{
		setData(ON_REGISTER_CALLED);
	}		

	@Override 
	public void onRemove()
	{
		setData(ON_REMOVE_CALLED);
	}
}
