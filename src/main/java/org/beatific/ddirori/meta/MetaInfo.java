package org.beatific.ddirori.meta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beatific.ddirori.attribute.Parser;
import org.beatific.ddirori.meta.map.RelationMap;
import org.beatific.ddirori.meta.map.impl.OneToNMap;

public class MetaInfo {

	private Integer level = 0;
	private final Map<String, BeanDefinition> meta = new HashMap<String, BeanDefinition>();
	private final RelationMap<Integer, BeanDefinition>orderedMeta = new OneToNMap<Integer, BeanDefinition>();
	private final Map<String, BeanDefinition>valueMeta = new HashMap<String, BeanDefinition>();

	public BeanDefinition getMeta(String tagName) throws BeanDefinitionNotFoundException {
		BeanDefinition def = meta.get(tagName);
		if(def == null) throw new BeanDefinitionNotFoundException("This BeanDefinition is not found by TagName[" + tagName + " ]");
		return def;
	}
	
	public void setDefinition(String tagName, BeanDefinition definition) {
		meta.put(tagName, definition);
	}
	
	public List<BeanDefinition> getMetaByLevel(Integer level) throws BeanDefinitionNotFoundException {
		
		List<BeanDefinition> defs = orderedMeta.getList(level);
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
	    if(valueMeta.size() > 0) {
	    	throw new ReferenceNotFoundException("This Reference Bean is not Found[" + Parser.getRelatedObjectNames(valueMeta.keySet().toArray(new String[0])[0]).get(0) + "]");
	    }
	}
	
	public Integer getLevel() {
		return level;
	}

	protected void loadLevelByMetaAnalysis(Integer level) {
		
		for(String key : valueMeta.keySet()) {
			boolean isExistByBelowLevel = true;
			List<String>objectNames = Parser.getRelatedObjectNames(key);
			for(String objectName : objectNames) {
				if(!orderedMeta.containsValue(objectName)) {
					isExistByBelowLevel = false;
					break;
				}
			}
			if(isExistByBelowLevel) {
				orderedMeta.put(level, valueMeta.get(key));
				valueMeta.remove(key);
			}
		}
		this.level = level;
		loadLevelByMetaAnalysis(level+1);
	}

}
