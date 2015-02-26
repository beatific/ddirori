package org.beatific.ddirori.context.event.impl;

import org.beatific.ddirori.context.event.ApplicationEvent;

public class DDiroriStopEvent extends ApplicationEvent {

	private static final long serialVersionUID = 2388144972720534290L;

	public DDiroriStopEvent(Object source) {
		super(source);
	}
}