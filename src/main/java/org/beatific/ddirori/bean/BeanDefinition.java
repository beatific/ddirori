package org.beatific.ddirori.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.type.TagType;

public abstract class BeanDefinition {

	protected TagType tagType;
	protected String tagName;
	protected Constructor constructor;
	private Integer level; 
	
	private final String BEAN_NAME_IDENTIFIER = "id"; 
	
	private BeanDefinition parentElementDefinition;
	private final List<BeanDefinition> childElementDeifinitions = new ArrayList<BeanDefinition>(); 
	private final Map<String, String>stringAttributes = new LinkedHashMap<String, String>();
	private Map<String, Object>attributes = null;
	
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
		this.childElementDeifinitions.add(childElementDeifinition);
	}
	
	public Map<String, String> getStringAttributes() {
		return stringAttributes;
	}
	
	void setStringAttribute(String key, String value) {
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
				+ ", stringAttributes=" + stringAttributes + ", attributes="
				+ attributes + "]";
	}


	static abstract class AttributeLoader {
		public abstract Map<String, Object> load(Map<String, String> attributes);
	}
}
