package org.beatific.ddirori.bean.impl;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringBeanContainer extends AbstractBeanContainerWithTemp {

	private SingletonBeanRegistry registry = null;
	
	public SpringBeanContainer(ApplicationContext context){
		if(context instanceof XmlWebApplicationContext)registry = ((XmlWebApplicationContext)context).getBeanFactory();
		else throw new NotSupportedApplicationContextExcpetion("Daram support only XmlWebApplicationContext for Spring[" + context.getClass().getName() + "]");
	}
	
	@Override
	protected void registerBean(String beanName, Object bean) {
		registry.registerSingleton(beanName, bean);
	}

	@Override
	protected void clearBean() {
		
	}

	@Override
	public Object getBean(String beanName) {
		return registry.getSingleton(beanName);
	}

}
