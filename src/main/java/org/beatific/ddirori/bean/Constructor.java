package org.beatific.ddirori.bean;

import java.util.List;
import java.util.Map;

import org.beatific.ddirori.meta.BeanDefinition;

public interface Constructor<T> {

	public T create(BeanDefinition parent, List<BeanDefinition> children, Map<String, Object>attributes);
}
