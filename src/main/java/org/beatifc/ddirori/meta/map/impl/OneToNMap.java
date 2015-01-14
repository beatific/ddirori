package org.beatifc.ddirori.meta.map.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beatifc.ddirori.meta.map.RelationMap;

public class OneToNMap<K, V> implements RelationMap<K, V> {

	private Map<K, List<V>> relations = new HashMap<K, List<V>>();
	private List<V> values = new ArrayList<V>();
	
	public int size() {
		return relations.size();
	}

	public boolean isEmpty() {
		return relations.isEmpty();
	}

	public boolean containsKey(Object key) {
		return relations.containsKey(key);
	}
	
	public boolean contains(Object key, Object value) {
		
		if(relations.containsKey(key) && relations.get(key).equals(value)) return true;
		return false;
	}

	public V get(Object key) {
		return relations.get(key).get(0);
	}
	
	public List<V> getList(Object key) {
		return relations.get(key);
	}

	public V put(K key, V value) {
		if(value == null || contains(key, value))return null;
		else {
			List<V> list = relations.get(key);
			list.add(value);
			values.add(value);
			return  null;
		}
	}

	public V remove(Object key) {
		return relations.remove(key).get(0);
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

	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	public Collection<V> values() {
		return values;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new NotSupportedException();
	}

}
