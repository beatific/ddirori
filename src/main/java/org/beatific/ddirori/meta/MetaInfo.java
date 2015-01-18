package org.beatific.ddirori.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.attribute.Parser;
import org.beatific.ddirori.maps.RelationMap;
import org.beatific.ddirori.maps.impl.DuplexFindMap;
import org.beatific.ddirori.maps.impl.OneToNMap;

public class MetaInfo {

	private Integer level = 0;
	private final Map<String, BeanDefinition> meta = new HashMap<String, BeanDefinition>();
	private final OneToNMap<Integer, BeanDefinition>orderedMeta = new OneToNMap<Integer, BeanDefinition>();
	private final DuplexFindMap<String, BeanDefinition>attributeMeta = new DuplexFindMap<String, BeanDefinition>();

	public BeanDefinition getMeta(String tagName) throws BeanDefinitionNotFoundException {
		BeanDefinition def = meta.get(tagName);
		if(def == null) throw new BeanDefinitionNotFoundException("This BeanDefinition is not found by TagName[" + tagName + " ]");
		return def;
	}
	
	public void setDefinition(String tagName, BeanDefinition definition) {
		meta.put(tagName, definition);
	}
	
	public List<BeanDefinition> getMetaByLevel(Integer level) throws BeanDefinitionNotFoundException {
		
		List<BeanDefinition> defs = orderedMeta.findByKey(level);
		if(defs == null || defs.size() == 0) throw new BeanDefinitionNotFoundException("This BeanDefinition is not found by Level[" + level + " ]");
		return defs;
	}
	
	public void setDefinition(Integer level, BeanDefinition definition) {
		orderedMeta.put(level, definition);
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
	
	public void setAttributeDefinition(String attribute, BeanDefinition definition) {
		this.attributeMeta.put(attribute, definition);
	}

	protected void loadLevelByMetaAnalysis(Integer level) {
		
		if(attributeMeta.size() == 0)return;
		
		for(BeanDefinition value : attributeMeta.valueSet()) {
			for(String key : attributeMeta.findByValue(value)) {
				boolean isExistByBelowLevel = true;
				List<String>objectNames = Parser.getRelatedObjectNames(key);
				for(String objectName : objectNames) {
					if(!orderedMeta.containsValue(objectName)) {
						isExistByBelowLevel = false;
						break;
					}
				}
				if(isExistByBelowLevel) {
					orderedMeta.put(level, attributeMeta.get(key));
				}
			}
			attributeMeta.remove(value);
		}
		this.level = level;
		loadLevelByMetaAnalysis(level+1);
	}

}
