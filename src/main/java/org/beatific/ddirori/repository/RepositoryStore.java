package org.beatific.ddirori.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.bean.BeanContainerDelegator;
import org.beatific.ddirori.utils.AnnotationUtils;

public class RepositoryStore {

	private Map<Class<?>, Repository<?, ?>> store = new HashMap<Class<?>, Repository<?, ?>>();
	private String[] basePackage;
	private BeanContainerDelegator delegator;

	public RepositoryStore(String[] basePackage, BeanContainerDelegator delegator) {
		this.basePackage = basePackage;
		this.delegator = delegator;
	}

	private synchronized Repository<?, ?> getRepository(Object object) {
		return store.get(object.getClass());
	}

	private synchronized void addRepository(Repository<?, ?> repository) {
		
		Type type = ((ParameterizedType)repository.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
//		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(
//				repository.getClass(), Repository.class);
		Class<?> clazz = (Class<?>)type;
		if (clazz == null)
			throw new RepositoryTypeNotFoundException(
					"Can't find the generic type of repository["
							+ repository.getClass().getName() + "]");
		if(store.containsKey(clazz)) 
			throw new RepositoryRegisterException(
					"Can't register duplicate repository for One Type["
							+ repository.getClass().getName() + "]");
		
		store.put(clazz, repository);
		delegator.register(repository.getClass().getSimpleName(), repository);
	}

	public void save(Object object) {

		Repository<?, ?> repository = getRepository(object);
		if(repository == null) throw new NullPointerException("Repository is not Found for Class[" + object.getClass() + "]");
		repository.save(repository.getState(), object);
	}

	public Object load(Object object) {

		Repository<?, ?> repository = getRepository(object);
		return repository.load(repository.getState(), object);
	}

	public void change(Object object) {

		Repository<?, ?> repository = getRepository(object);
		repository.change(repository.getState(), object);
	}

	public void remove(Object object) {

		Repository<?, ?> repository = getRepository(object);
		repository.remove(repository.getState(), object);
	}

	public void loadStore() {
		for (Class<?> clazz : AnnotationUtils.findClassByAnnotation(
				this.basePackage, Store.class)) {
			
			try {
				Object repository = clazz.newInstance();
				if (repository instanceof Repository) {
					addRepository((Repository<?, ?>) repository);
				} else
					throw new RepositoryLoadException(
							"Only repository instance can attach Store annotation["
									+ clazz.getName() + "]");
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new RepositoryLoadException(
						"Repository instantiation is not failed["
								+ clazz.getName() + "]", e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RepositoryLoadException(
						"Repository instantiation is not failed["
								+ clazz.getName() + "]", e);
			} 
		}
	}

}
