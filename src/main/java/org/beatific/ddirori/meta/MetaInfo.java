package org.beatific.ddirori.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.attribute.Parser;
import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.BeanDefinitionNotFoundException;
import org.beatific.ddirori.maps.impl.OneToNMap;

public class MetaInfo {

	private Integer level = 0;
	private final Map<String, BeanDefinition> meta = new HashMap<String, BeanDefinition>();
	private final OneToNMap<Integer, BeanDefinition>orderedMeta = new OneToNMap<Integer, BeanDefinition>();
	private final OneToNMap<BeanDefinition, String>attributeMeta = new OneToNMap<BeanDefinition, String>();

	public BeanDefinition getMeta(String tagName) throws BeanDefinitionNotFoundException {
		BeanDefinition def = meta.get(tagName);
		if(def == null) throw new BeanDefinitionNotFoundException("This BeanDefinition is not found by TagName[" + tagName + "]");
		return def;
	}
	
	public void setDefinition(String tagName, BeanDefinition definition) {
		meta.put(tagName, definition);
	}
	
	public List<BeanDefinition> getMetaByLevel(Integer level) throws BeanDefinitionNotFoundException {
		
		List<BeanDefinition> defs = orderedMeta.get(level);
		if(defs == null || defs.size() == 0) throw new BeanDefinitionNotFoundException("This BeanDefinition is not found by Level[" + level + " ]");
		return defs;
	}
	
	public void loadLevel() {
	    Integer level = orderedMeta.size() + 1;
	    this.level = level;
	    loadLevelByMetaAnalysis(level);
	    if(attributeMeta.size() > 0) {
	    	throw new ReferenceNotFoundException("This Reference Bean is not Found[" + Parser.getRelatedObjectNames(attributeMeta.keySet().toArray(new String[0])[0]).get(0) + "]");
	    }
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setAttributeDefinition(BeanDefinition definition, String attribute) {
		this.attributeMeta.put(definition, attribute);
	}

	protected void loadLevelByMetaAnalysis(Integer level) {
		
		if(attributeMeta.size() == 0) return;
		for(BeanDefinition definition : attributeMeta.keySet()) {
			List<String> attributes = attributeMeta.get(definition);
			boolean existsBelowLevel = true;
			for(String attribute : attributes) {
				List<String>objectNames = Parser.getRelatedObjectNames(attribute);
				for(String objectName : objectNames) {
					if(!constinsObject(objectName)) {
						existsBelowLevel = false;
						break;
					}
				}
				if(existsBelowLevel == false)break;
			}
			
			if(existsBelowLevel) {
				orderedMeta.put(level, definition);
			}
			
		}
		removeOrderedAttribute();
		this.level = level;
		loadLevelByMetaAnalysis(level+1);
	}
	
	private boolean constinsObject(String objectName) {
		for(BeanDefinition definition : this.orderedMeta.values()) {
			if(definition.getBeanName().equals(objectName))return true;
		}
		return false;
	}
	private void removeOrderedAttribute() {
		for(BeanDefinition definition : orderedMeta.values()) {
			attributeMeta.remove(definition);
		}
	}

	@Override
	public String toString() {
		return "MetaInfo [level=" + level + ", meta=" + meta + ", orderedMeta="
				+ orderedMeta + ", attributeMeta=" + attributeMeta + "]";
	}
	

}
