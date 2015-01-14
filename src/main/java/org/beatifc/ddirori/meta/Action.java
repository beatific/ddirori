package org.beatifc.ddirori.meta;

import java.util.List;
import java.util.Map;

public interface Action {

	public Object act(BeanDefinition parent, List<BeanDefinition> children, Map<String, String>attributes);
}
