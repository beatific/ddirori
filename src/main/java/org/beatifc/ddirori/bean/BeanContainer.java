package org.beatifc.ddirori.bean;

import java.util.HashMap;
import java.util.Map;

import org.beatifc.ddirori.meta.BeanDefinition;
import org.beatifc.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatifc.ddirori.meta.MetaInfo;

public abstract class BeanContainer {

	private final Map<String, Object> container = new HashMap<String, Object>();
	private final Map<String, Object> temp = new HashMap<String, Object>();
	
	public void init() throws BeanDefinitionNotFoundException {
		MetaInfo meta = buildMetaInfo();
		init(meta);
	}
	
	protected abstract MetaInfo  buildMetaInfo();
	
	protected void init(MetaInfo meta) throws BeanDefinitionNotFoundException {
		for(int i = 0 ; i < meta.getLevel(); i++)
			for(BeanDefinition definition : meta.getMetaByLevel(i))
				load(definition);
	}
	
	protected Object load(BeanDefinition definition) {
		
		Object object = definition.getAction().act(definition.getParentElementDefinition(), definition.getChildElementDeifinitions(), definition.getAttributes());
		
		switch(definition.getTag()) {
		
		case BEAN :
			if(container.containsKey(definition.getBeanName()))
				throw new BeanCreationException("Duplicate Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
			container.put(definition.getBeanName(), load(definition));
			break;
			
		case TEMP :
			if(temp.containsKey(definition.getBeanName()))
				throw new BeanCreationException("Duplicate Temp Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
			temp.put(definition.getBeanName(), load(definition));
			break;
			
		case ATTRIBUTE :
			break;
			
		default : 
			throw new BeanCreationException("Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
		}
		return null;
	}
	
	public Object getBean(String beanName) {
		return container.get(beanName);
	}
	
	public Object getObject(String objectName) {
		Object object = getBean(objectName);
		if(object == null) {
			object = temp.get(objectName);
		}
		if(object == null) {
			throw new BeanNotFoundException("This bean is not found : [" + objectName + "]");
		}
		return object;
	}
	
	public void clearTemp() {
		temp.clear();
	}
	
	public void destory() {
		container.clear();
		temp.clear();
	}
	
	public void setBean(String beanName, Object bean) {
		container.put(beanName, bean);
	}
}
