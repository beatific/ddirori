package org.beatific.ddirori.meta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.bean.Constructor;
import org.beatific.ddirori.type.TagType;

public class BeanDefinition {

	private final TagType tagType;
	private final String tagName;
	private final Constructor constructor;
	private Integer level; 
	
	private final String BEAN_NAME_IDENTIFIER = "id"; 
	
	private BeanDefinition parentElementDefinition;
	private final List<BeanDefinition> childElementDeifinitions = new ArrayList<BeanDefinition>(); 
	private final Map<String, String>attributes = new LinkedHashMap<String, String>();
	
	public BeanDefinition(org.beatific.ddirori.bean.annotation.Action annotation, Constructor constructor) {
		this.tagType = annotation.type();
		this.tagName = annotation.tag();
		this.constructor = constructor;
	}
	
	Integer getLevel() {
		return level;
	}

	void setLevel(Integer level) {
		this.level = level;
	}

	public TagType getTag() {
		return tagType;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public Constructor getConstructor() {
		return constructor;
	}
	
	public String getBeanName() {
		String id = attributes.get(BEAN_NAME_IDENTIFIER);
		if(id == null) {
			id = tagName;
		}
		return id;
	}
	
	public BeanDefinition getParentElementDefinition() {
		return parentElementDefinition;
	}
	public void setParentElementDefinition(BeanDefinition parentElementDefinition) {
		this.parentElementDefinition = parentElementDefinition;
	}
	public List<BeanDefinition> getChildElementDeifinitions() {
		return childElementDeifinitions;
	}
	public void addChildElementDeifinition(
			BeanDefinition childElementDeifinition) {
		this.childElementDeifinitions.add(childElementDeifinition);
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	void setAttribute(String key, String value) {
		this.attributes.put(key, value);
	}

	
}
