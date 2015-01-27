package org.beatific.ddirori.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.type.TagType;

public abstract class BeanDefinition implements Cloneable {

	protected TagType tagType;
	protected String tagName;
	protected Constructor constructor;
	private Integer level; 
	
	private final String BEAN_NAME_IDENTIFIER = "id"; 
	
	private BeanDefinition parentElementDefinition;
	private List<BeanDefinition> childElementDeifinitions; 
	private Map<String, String>stringAttributes;
	private Map<String, Object>attributes = null;
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new BeanClonException("Can't creat clone of BeanDefinition[" + this.getTagName() + "]");
		}
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
		String id = stringAttributes.get(BEAN_NAME_IDENTIFIER);
		if(id == null) {
			id = tagName;
		}
		return id;
	}
	
	public BeanDefinition parent() {
		return parentElementDefinition;
	}
	public void setParentElementDefinition(BeanDefinition parentElementDefinition) {
		this.parentElementDefinition = parentElementDefinition;
	}
	public List<BeanDefinition> children() {
		return childElementDeifinitions;
	}
	public void addChildElementDeifinition(
			BeanDefinition childElementDeifinition) {
		if(childElementDeifinitions == null)childElementDeifinitions = new ArrayList<BeanDefinition>();
		this.childElementDeifinitions.add(childElementDeifinition);
	}
	
	public Map<String, String> getStringAttributes() {
		return stringAttributes;
	}
	
	public void addStringAttribute(String key, String value) {
		if(this.stringAttributes == null)stringAttributes = new LinkedHashMap<String, String>();
		this.stringAttributes.put(key, value);
	}
	
	public Map<String, Object> attributes() {
		return attributes;
	}
	
	Map<String, Object> loadAttributes(AttributeLoader loader) {

		this.attributes = loader.load(stringAttributes);
		return attributes();
	}
	
	@Override
	public String toString() {
		return "BeanDefinition [tagType=" + tagType + ", tagName=" + tagName
				+ ", constructor=" + constructor + ", level=" + level
				+ ", stringAttributes=" + stringAttributes + ", attributes="
				+ attributes + "]";
	}


	static abstract class AttributeLoader {
		public abstract Map<String, Object> load(Map<String, String> attributes);
	}
}
