package org.beatific.ddirori.maps.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

public class NToOneMap<K,V> {

	private OneToNMap<V,K> relations = new OneToNMap<V,K>();
	
	public int size() {
		return relations.size();
	}

	public boolean isEmpty() {
		return relations.isEmpty();
	}

	public boolean containsKey(Object key) {
		return relations.containsValue(key);
	}
	
	public boolean containsValue(Object value) {
		return relations.containsKey(value);
	}
	
	public boolean contains(Object key, Object value) {
		
		return relations.contains(value,key);
	}

	public List<K> getKeys(Object value) {
		return relations.getValues(value);
	}
	
	private List<V> getInstance(K key) {
		List<V> list = relations.get(key);
		if(list == null)list = new ArrayList<V>();
		return list;
	}
	
	public V put(K key, V value) {
		Assert.notNull(value);
		if(contains(key, value))return null;
		else {
			
			List<V> list = getInstance(key);
			list.add(value);
			values.add(value);
			return  null;
		}
	}

	public List<V> removeByKey(Object key) {
		return relations.remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keySet = m.keySet();
		for(K key : keySet) {
			put(key, m.get(key));
		}
	}

	public void clear() {
		relations.clear();
	}

	public Set<K> keySet() {
		return relations.keySet();
	}

	public Collection<V> values() {
		return values;
	}
}
