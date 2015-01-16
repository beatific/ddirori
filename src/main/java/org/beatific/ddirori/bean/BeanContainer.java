package org.beatific.ddirori.bean;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.attribute.AttributeExtractor;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.MetaInfo;
import org.beatific.ddirori.meta.map.RelationMap;
import org.beatific.ddirori.meta.map.impl.OneToNMap;

public abstract class BeanContainer {

	private final Map<String, Object> container = new HashMap<String, Object>();
	private final Map<String, Object> temp = new HashMap<String, Object>();
	private final RelationMap<Object, Object> relations = new OneToNMap<Object, Object>();
	private final AttributeExtractor extractor = new AttributeExtractor(relations);;
	
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
	
	protected Map<String, Object> loadAttributes(Map<String, String> attributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		for(String key : map.keySet()) {
			String value = attributes.get(key);
			map.put(key, extractor.extract(this, value));
		}
		return map;
	}
	
	protected Object load(BeanDefinition definition) {
		Object object = definition.getConstructor().create(definition.getParentElementDefinition(), definition.getChildElementDeifinitions(), loadAttributes(definition.getAttributes()));
		
		switch(definition.getTag()) {
		
		case BEAN :
			if(container.containsKey(definition.getBeanName()))
				throw new BeanCreationException("Duplicate Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
			container.put(definition.getBeanName(), object);
			break;
			
		case TEMP :
			if(temp.containsKey(definition.getBeanName()))
				throw new BeanCreationException("Duplicate Temp Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
			temp.put(definition.getBeanName(), object);
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
