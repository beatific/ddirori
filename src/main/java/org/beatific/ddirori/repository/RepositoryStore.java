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
		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(
				repository.getClass(), Repository.class);
		Class<?> clazz = classes[0];
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

	public void save(Object object) {

		Repository<?, ?> repository = getRepository(object);
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
