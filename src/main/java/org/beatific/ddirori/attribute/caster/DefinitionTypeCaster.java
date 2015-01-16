package org.beatific.ddirori.attribute.caster;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.attribute.TypeResolverCreationException;
import org.beatific.ddirori.attribute.annotation.ArgumentType;
import org.beatific.ddirori.attribute.resolver.DefinitionTypeResolver;
import org.beatific.ddirori.utils.AnnotationUtils;

public class DefinitionTypeCaster {

	private final Map<String, DefinitionTypeResolver<?>> resolvers = new HashMap<String, DefinitionTypeResolver<?>>();
	private final String basePackage;
	
	public DefinitionTypeCaster(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public Object cast(String type) {
		DefinitionTypeResolver<?> resolver = resolvers.get(type);
		return resolver.resolve();
	}
	
	public void init() {
		for(Class<?> clazz : AnnotationUtils.findClassByAnnotation(basePackage, ArgumentType.class)) {
			ArgumentType annotation = clazz.getAnnotation(ArgumentType.class);
			try {
				resolvers.put(annotation.type(), (DefinitionTypeResolver<?>)clazz.newInstance());
			} catch (InstantiationException e) {
				throw new TypeResolverCreationException("Creating DefinitionTypeResolver is failed[" + clazz.getName() + "]");
			} catch (IllegalAccessException e) {
				throw new TypeResolverCreationException("Creating DefinitionTypeResolver is failed[" + clazz.getName() + "]");
			}
		}
		
	}
}
