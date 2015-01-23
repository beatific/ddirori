package org.beatific.ddirori.context;

import org.beatific.ddirori.bean.BeanDefinitionNotFoundException;
import org.beatific.ddirori.bean.BeanLoader;

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
		
	}
	
	public void destroy() {
		ApplicationContextUtils.setApplicationContext(null);
		destory();
	}

}
