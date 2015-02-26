package org.beatific.ddirori.bean.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beatific.ddirori.bean.BeanCreationException;
import org.beatific.ddirori.bean.BeanNotFoundException;

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

	@Override
	protected Object findBean(Class<?> clazz) {
		List<Object> list = new ArrayList<Object>();
		for(Map.Entry<String, Object> entry : container.entrySet()) {
			if(clazz.isInstance(entry.getValue())) {
				list.add(entry.getValue());
			}
		}
		
		if(list.size() == 0) throw new BeanNotFoundException("This Bean is NOT founded[" + clazz.getName() + "]");
		
		return list.toArray(new Object[0]);
	}

	@Override
	public Map<String, Object> getBeanWithAnnotation(
			Class<? extends Annotation> annotationClass) {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		for(Map.Entry<String, Object> entry : container.entrySet()) {
			
			logger.debug("isClassWithAnnotation before : Class[" + entry.getValue().getClass().getName() + "], Annotation[" + annotationClass.getClass().getName() + "]" );
			if(isClassWithAnnotation(entry.getValue(), annotationClass)) {
				logger.debug("isClassWithAnnotation after : Class[" + entry.getValue().getClass().getName() + "], Annotation[" + annotationClass.getClass().getName() + "]" );
				map.put(entry.getKey(), entry.getValue());
			}
		}
		
		return map;
	}
	
	private boolean isClassWithAnnotation(Object object, Class<? extends Annotation> annotationClass) {
		
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field : fields) {
			Annotation annotation = field.getAnnotation(annotationClass);
			if(annotation != null) return true;
		}
		return false;
	}
}
