package org.beatific.ddirori.maps;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RelationMap<K,V>  extends Map<K,V> {
	public boolean contains(Object key, Object value);
	public Set<V> valueSet();
	public void removeByValue(Object value);
	public List<V> findByKey(Object key);
	public List<K> findByValue(Object value);
}
