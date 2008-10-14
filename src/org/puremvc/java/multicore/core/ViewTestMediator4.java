/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Matthieu Mauny <matthieu.mauny@puremvc.org>
 And Anthony Quinault <aquinault@gmail.com>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import org.puremvc.java.multicore.interfaces.IMediator;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

/**
 * A Mediator class used by ViewTest.
 * 
 * @see org.puremvc.java.multicore.core.view.ViewTest ViewTest
 */
public class ViewTestMediator4 extends Mediator implements IMediator {
	/**
	 * The Mediator name
	 */
	public static final String NAME = "ViewTestMediator4";

	/**
	 * Constructor
	 */
	public ViewTestMediator4(Object view) {
		super(NAME, view);
	}

	public ViewTest getViewTest() {
		return (ViewTest) viewComponent;
	}

	@Override
	public void onRegister() {
		getViewTest().onRegisterCalled = true;
	}

	@Override
	public void onRemove() {
		getViewTest().onRemoveCalled = true;
	}

}
