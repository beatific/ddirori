package org.beatific.ddirori.maps.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beatific.ddirori.maps.RelationMap;

public class MToNMap<K,V> implements RelationMap<K,V> {

	private final DuplexFindMap<K,V> valueMap = new DuplexFindMap<K,V>();
	private final OneToNMap<K,V> keyMap = new OneToNMap<K,V>(); 
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) {
		if(contains(key, value))return null;
		if(containsKey(key) || containsValue(value))throw new IllegalArgumentException("Cannot insert duplcated data!");
		keyMap.put(key, value);
		valueMap.put(key, value);
		return null;
	}

	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contains(Object key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<V> valueSet() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeByValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	public List<V> findByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<K> findByValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
