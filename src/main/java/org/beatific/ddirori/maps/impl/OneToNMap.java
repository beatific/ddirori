package org.beatific.ddirori.maps.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beatific.ddirori.transaction.Transaction;
import org.springframework.util.Assert;

public class OneToNMap<K, V> {

	private Map<K, List<V>> forwardRelation = new HashMap<K, List<V>>();
	private Map<V, K> reverseRelation = new HashMap<V, K>();
	
	public int size() {
		return forwardRelation.size();
	}

	public boolean isEmpty() {
		return forwardRelation.isEmpty();
	}

	public boolean containsKey(Object key) {
		return forwardRelation.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		return reverseRelation.containsKey(value);
	}
	
	public boolean contains(Object key, Object value) {
		
		if(forwardRelation.containsKey(key) && forwardRelation.get(key).contains(value)) return true;
		return false;
	}

	public List<V> getValues(Object key) {
		return forwardRelation.get(key);
	}
	
	private List<V> getInstance(K key) {
		List<V> list = forwardRelation.get(key);
		if(list == null)list = new ArrayList<V>();
		return list;
	}
	
	public void put(K key, V value) {
		Assert.notNull(key);
		Assert.notNull(value);
		
		if(contains(key, value))return;
			
		List<V> list = getInstance(key);
		list.add(value);
		reverseRelation.put(value, key);
	}

	public List<V> removeByKey(Object key) {
		
		Transaction.start(forwardRelation, reverseRelation);
		try{
			List<V> list = forwardRelation.remove(key);
			for(V value : list) {
				reverseRelation.remove(value);
			}
		} catch(Exception ex) {
			Transaction.rollback();
		}
		Transaction.commit();
		
		return list;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keySet = m.keySet();
		for(K key : keySet) {
			put(key, m.get(key));
		}
	}

	public void clear() {
		forwardRelation.clear();
	}

	public Set<K> keySet() {
		return forwardRelation.keySet();
	}

	public Collection<V> values() {
		return values;
	}
}
