package org.beatific.ddirori.context;

import org.beatific.ddirori.bean.BeanDefinitionNotFoundException;
import org.beatific.ddirori.bean.BeanLoader;
import org.beatific.ddirori.context.event.impl.DDiroriStartEvent;
import org.beatific.ddirori.context.event.impl.DDiroriStopEvent;

public abstract class ApplicationContext extends BeanLoader {

    public ApplicationContext(String basePackage) {
		
		super(basePackage);
	}
    
    public ApplicationContext(String basePackage, boolean isUseDDiroriExpression) {
		
		super(basePackage, isUseDDiroriExpression);
	}

	public void init() {
		ApplicationContextUtils.setApplicationContext(this);
		
		try {
			initContainer();
		} catch (BeanDefinitionNotFoundException e) {
			throw new ContextInitializeException("This Context is failed to initialize.");
		}
		
		publishEvent(new DDiroriStartEvent(this));
		
	}
	
	public void destroy() {
		ApplicationContextUtils.setApplicationContext(null);
		
		publishEvent(new DDiroriStopEvent(this));
		
		destory();
	}

}
