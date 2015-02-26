package org.beatific.ddirori.context.event;

import java.util.EventObject;

import org.beatific.ddirori.context.ApplicationContext;

public class ApplicationEvent extends EventObject {

	private static final long serialVersionUID = 1496863722302911114L;

	public ApplicationEvent(Object source) {
		super(source);
	}
	
	public ApplicationContext getApplicationContext() {
		Object source = getSource();
		if(source instanceof ApplicationContext)return (ApplicationContext)source;
		else return null;
	}

}
