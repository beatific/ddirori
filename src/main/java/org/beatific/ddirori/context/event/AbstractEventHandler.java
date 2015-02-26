package org.beatific.ddirori.context.event;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractEventHandler<T extends ApplicationEvent> implements EventHandler<T> {
	
	protected abstract void handleMyEvent(T event);
	private final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	public void handle(ApplicationEvent event) {
		logger.debug("MyEvent[" + getGenericName() + "], GivenEvent[" + event.getClass().getName() + "]");
		if(event.getClass().getName() == getGenericName()) handleMyEvent((T)event);
	}
	
	@SuppressWarnings("unchecked")
	private String getGenericName()
	{
	    return ((Class<T>) ((ParameterizedType) getClass()  
	             .getGenericSuperclass()).getActualTypeArguments()[0]).getName();
	}
}
