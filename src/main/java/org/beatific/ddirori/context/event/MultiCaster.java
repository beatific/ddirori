package org.beatific.ddirori.context.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beatific.ddirori.context.event.annotation.EventListener;
import org.beatific.ddirori.utils.AnnotationUtils;

public class MultiCaster {

	private final Log logger = LogFactory.getLog(getClass());
	private final static String DEFAULT_PACKAGE = "org.beatific.ddirori";
	private List<String> basePackage = new ArrayList<String>();
	public MultiCaster(String[] basePackage) {
		
		this.basePackage.addAll(Arrays.asList(basePackage));
		this.basePackage.add(DEFAULT_PACKAGE);
	}
	
	private List<AbstractEventHandler<?>> handlers = new ArrayList<AbstractEventHandler<?>>(); 
			
	private void registerHandler(AbstractEventHandler<?> handler) {
		this.handlers.add(handler);
	}
	
	public void cast(ApplicationEvent event) {
		for(AbstractEventHandler<? extends ApplicationEvent> handler : this.handlers) {
			handler.handle(event);
		}
	}
	
	public void load() {
		for (Class<?> clazz : AnnotationUtils.findClassByAnnotation(
				this.basePackage.toArray(new String[0]), EventListener.class)) {
			
			logger.debug("EventListener load[" + clazz.getName()+"]");
			try {
				Object handler = clazz.newInstance();
				if (handler instanceof AbstractEventHandler)
					registerHandler((AbstractEventHandler<?>) handler);
				else
					throw new HandlerLoadException(
							"Only EventHandler instance can attach EventListener annotation["
									+ clazz.getName() + "]");
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new HandlerLoadException(
						"EventHandler instantiation is not failed["
								+ clazz.getName() + "]", e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new HandlerLoadException(
						"EventHandler instantiation is not failed["
								+ clazz.getName() + "]", e);
			} 
		}
	}
}
