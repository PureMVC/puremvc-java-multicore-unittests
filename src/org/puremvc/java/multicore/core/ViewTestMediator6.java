/*
 PureMVC Java MultiCore Unit Tests by Ima OpenSource <opensource@ima.eu>
 Maintained by Matthieu Mauny <matthieu.mauny@puremvc.org>
 And Anthony Quinault <aquinault@gmail.com>
 PureMVC - Copyright(c) 2006-08 Futurecale, Inc., Some rights reserved.
 Your reuse is governed by Creative Commons Attribution 2.5 License
 */
package org.puremvc.java.multicore.core;

import org.puremvc.java.multicore.interfaces.IMediator;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

/**
 * A Mediator class used by ViewTest.
 * 
 * @see org.puremvc.java.multicore.core.view.ViewTest ViewTest
 */
public class ViewTestMediator6 extends Mediator implements IMediator {
	/**
	 * The Mediator name
	 */
	public static final String NAME = "ViewTestMediator6";

	/**
	 * Constructor
	 */
	public ViewTestMediator6(String name, Object view) {
		super(name, view);
	}

	@Override
	public String[] listNotificationInterests() {
		// be sure that the mediator has some Observers created
		// in order to test removeMediator
		return new String[] { ViewTest.NOTE6 };
	}

	@Override
	public void handleNotification(INotification notification) {
		getFacade().removeMediator(getMediatorName());
	}

	@Override
	public void onRemove() {
		getViewTest().counter++;
	}

	public ViewTest getViewTest() {
		return (ViewTest) viewComponent;
	}

}
