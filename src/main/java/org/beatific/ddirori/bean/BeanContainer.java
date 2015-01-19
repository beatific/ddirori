package org.beatific.ddirori.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.attribute.AttributeExtractor;
import org.beatific.ddirori.maps.RelationMap;
import org.beatific.ddirori.maps.impl.OneToNMap;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.MetaInfo;

public abstract class BeanContainer {

	private final Map<String, Object> container = new HashMap<String, Object>();
	private final Map<String, Object> temp = new HashMap<String, Object>();
	private final RelationMap<Object, BeanDefinition> relations = new OneToNMap<Object, BeanDefinition>();
	private final AttributeExtractor extractor;
//	private final RelationHolder holder = new RelationHolder();
	protected String basePackage;
	
	public BeanContainer(String basePackage) {
		this.basePackage = basePackage;
		this.extractor = new AttributeExtractor(basePackage) {

			@Override
			protected Object getObject(BeanContainer container, String objectName) {
				return container.getObject(objectName);
			}
			
		};
	}

	protected void initContainer() throws BeanDefinitionNotFoundException {
		MetaInfo meta = buildMetaInfo();
		init(meta);
	}
	
	private List<BeanDefinition> getRelations(Object object) {
		synchronized(relations) {
		    return relations.get(object);
		}
	}
	
	protected abstract MetaInfo buildMetaInfo();
	
	private void init(MetaInfo meta) throws BeanDefinitionNotFoundException {
		for(int i = 0 ; i < meta.getLevel(); i++) 
			for(BeanDefinition definition : meta.getMetaByLevel(i+1))
				load(definition);
	}
	
	private Map<String, Object> loadAttributes(Map<String, String> attributes) {

		Map<String, Object> map = new HashMap<String, Object>();
		for(String key : attributes.keySet()) {
			String value = attributes.get(key);
			map.put(key, extractor.extract(this, value));
		}
		return map;
	}
	
//	private void chainRelation(BeanDefinition definition){
//		
//		for(Object holdedObject : this.holder.getHoldedObjects())
//			synchronized(relations) {
//		        this.relations.put(holdedObject, definition);
//			}
//		this.holder.release();
//	}
	
	private synchronized void registerBean(BeanDefinition definition, Object object) {
		
		if(container.containsKey(definition.getBeanName()) 
			|| temp.containsKey(definition.getBeanName())) {
			throw new BeanCreationException("Duplicate Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
		}
		
        switch(definition.getTag()) {
		
		case BEAN :
			container.put(definition.getBeanName(), object);
			break;
			
		case TEMP :
			temp.put(definition.getBeanName(), object);
			break;
			
		case ATTRIBUTE :
			break;
			
		default : 
			throw new BeanCreationException("Bean Creation Exception : Bean[" + definition.getBeanName() + "]");
		}
	}
	private void load(BeanDefinition definition) {
		
		loadWithoutRelation(definition);
//		chainRelation(definition);
	}
	
    private void loadWithoutRelation(BeanDefinition definition) {
    	
		Object object = definition.getConstructor().create(definition.getParentElementDefinition(), definition.getChildElementDeifinitions(), loadAttributes(definition.getAttributes()));
		registerBean(definition, object);
	}
	
	public synchronized Object getBean(String beanName) {
		return container.get(beanName);
	}
	
	private synchronized Object getObject(String objectName) {
		Object object = getBean(objectName);
		if(object == null) {
			object = temp.get(objectName);
		}
		if(object == null) {
			throw new BeanNotFoundException("This bean is not found : [" + objectName + "]");
		}
		return object;
	}
	
	public synchronized void clearTemp() {
		temp.clear();
	}
	
	protected synchronized void destory() {
		container.clear();
		temp.clear();
	}
	
	public synchronized void setBean(String beanName, Object bean) {
		if(container.containsKey(beanName))
			throw new BeanCreationException("Can't initialize duplicated bean[" + beanName + "]");
		container.put(beanName, bean);
	}
}
