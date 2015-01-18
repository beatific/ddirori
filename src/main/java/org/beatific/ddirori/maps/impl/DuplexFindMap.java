package org.beatific.ddirori.maps.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beatific.ddirori.maps.NotSupportedException;
import org.beatific.ddirori.maps.RelationMap;
import org.springframework.util.Assert;

public class DuplexFindMap<K, V> extends HashMap<K, V> implements RelationMap<K, V>{

	private static final long serialVersionUID = -751995922333434716L;
	private Map<V, List<K>> indexes = new HashMap<V, List<K>>();
	
	@Override
	public V put(K key, V value) {

		Assert.notNull(value);
		if(contains(key, value))return get(key);
		if(containsKey(key) || indexes.containsKey(value))throw new IllegalArgumentException("Cannot insert duplcated data!");
		
		super.put(key, value);
		List<K> index = indexes.get(value);
		if(index == null){
			index = new ArrayList<K>();
			indexes.put(value, index);
		}
		index.add(key);
		return null;
		
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
	}
	
	@Override
	@Deprecated
	public Set<Map.Entry<K, V>> entrySet() {
		throw new NotSupportedException("Not Supported Function![entrySet()]");
	}
	
    public boolean contains(Object key, Object value) {
		
		if(containsKey(key) && get(key).equals(value)) return true;
		return false;
	}

	@Deprecated
	public List<V> findByKey(Object key) {
		throw new NotSupportedException("Not Supported Function![contains(Object key, Object value)]");
	}

	public List<K> findByValue(Object value) {
		return indexes.get(value);
	}

	public Set<V> valueSet() {
		return indexes.keySet();
	}

	public void removeByValue(Object value) {
		for(K key : indexes.remove(value)) {
			remove(key);
		}
	}

	
}
