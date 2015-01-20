package org.beatific.ddirori.context;

import org.beatific.ddirori.bean.BeanLoader;
import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;

public abstract class ApplicationContext extends BeanLoader {

    public ApplicationContext(String basePackage) {
		
		super(basePackage);
	}

	public void init() {
		try {
			initContainer();
		} catch (BeanDefinitionNotFoundException e) {
			throw new ContextInitializeException("This Context is failed to initialize.");
		}
		ApplicationContextUtils.setApplicationContext(this);
	}
	
	public void destroy() {
		ApplicationContextUtils.setApplicationContext(null);
		destory();
	}

}
