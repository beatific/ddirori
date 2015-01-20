package org.beatific.ddirori.bean.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beatific.ddirori.bean.BeanCreationException;

public class DDiroriBeanContainer extends AbstractBeanContainerWithTemp {

	private final Map<String, Object> container = new HashMap<String, Object>();
	private Log logger = LogFactory.getLog(DDiroriBeanContainer.class);
	
	protected synchronized void registerBean(String beanName, Object bean) {
		logger.debug("beanName:"+beanName + "/" + bean + "]");
		if(this.container.containsKey(beanName)) throw new BeanCreationException("Can't create duplicated Temp[" + beanName + "]");
		this.container.put(beanName, bean);
	}
	
	public synchronized Object getBean(String beanName) {
		return container.get(beanName);
	}
	
	public synchronized void clearBean() {
		container.clear();
	}
	
}
