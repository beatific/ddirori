package org.beatific.ddirori.repository;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.utils.AnnotationUtils;
import org.springframework.core.GenericTypeResolver;

public class RepositoryStore {

	private Map<Class<?>, Repository<?, ?>> store = new HashMap<Class<?>, Repository<?, ?>>();
	private String[] basePackage;

	public RepositoryStore(String[] basePackage) {
		this.basePackage = basePackage;
	}

	private synchronized Repository<?, ?> getRepository(Object object) {
		return store.get(object.getClass());
	}

	private synchronized void addRepository(Repository<?, ?> repository) {
		Class<?> clazz = GenericTypeResolver.resolveTypeArgument(
				repository.getClass(), Repository.class);
		if (clazz == null)
			throw new RepositoryTypeNotFoundException(
					"Can't find the generic type of repository["
							+ repository.getClass().getName() + "]");
		if(store.containsKey(clazz)) 
			throw new RepositoryRegisterException(
					"Can't register duplicate repository for One Type["
							+ repository.getClass().getName() + "]");
		
		store.put(clazz, repository);
	}

	public int save(Object object) {

		Repository<?, ?> repository = getRepository(object);
		return repository.save(repository.getState(), object);
	}

	public Object load(Object object) {

		Repository<?, ?> repository = getRepository(object);
		return repository.load(repository.getState(), object);
	}

	public int change(Object object) {

		Repository<?, ?> repository = getRepository(object);
		return repository.change(repository.getState(), object);
	}

	public int remove(Object object) {

		Repository<?, ?> repository = getRepository(object);
		return repository.remove(repository.getState(), object);
	}

	public void load() {
		for (Class<?> clazz : AnnotationUtils.findClassByAnnotation(
				this.basePackage, Store.class)) {
			try {
				Object repository = clazz.newInstance();
				if (repository instanceof Repository)
					addRepository((Repository<?, ?>) repository);
				else
					throw new RepositoryLoadException(
							"Only repository instance can attach Store annotation["
									+ clazz.getName() + "]");
			} catch (InstantiationException e) {
				throw new RepositoryLoadException(
						"Repository instantiation is not failed["
								+ clazz.getName() + "]", e);
			} catch (IllegalAccessException e) {
				throw new RepositoryLoadException(
						"Repository instantiation is not failed["
								+ clazz.getName() + "]", e);
			}
		}
	}

}
