package org.beatifc.ddirori.meta.map;

import java.util.List;
import java.util.Map;

public interface RelationMap<K,V>  extends Map<K,V> {
	public boolean contains(Object key, Object value);
	public List<V> getList(Object key);
}
