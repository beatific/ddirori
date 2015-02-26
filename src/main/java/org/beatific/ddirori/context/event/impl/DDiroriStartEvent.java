package org.beatific.ddirori.context.event.impl;

import org.beatific.ddirori.context.event.ApplicationEvent;

public class DDiroriStartEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8252016214199961803L;

	public DDiroriStartEvent(Object source) {
		super(source);
	}
}
