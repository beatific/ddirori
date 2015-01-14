package org.beatifc.ddirori.meta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.beatifc.ddirori.type.TagType;
import org.beatifc.ddirori.type.Type;

public class BeanDefinition {

	private final TagType tagType;
	private final String tagName;
	private final Action action;
	private Integer level; 
	
	private final String BEAN_NAME_IDENTIFIER = "id"; 
	
	private BeanDefinition parentElementDefinition;
	private final List<BeanDefinition> childElementDeifinitions = new ArrayList<BeanDefinition>(); 
	private final Map<String, String>attributes = new LinkedHashMap<String, String>();
	
	public BeanDefinition(Type type, String tagName, Action action) {
		this.tagType = TagType.valueOf(type);
		this.tagName = tagName;
		this.action = action;
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
	
	public Action getAction() {
		return action;
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
