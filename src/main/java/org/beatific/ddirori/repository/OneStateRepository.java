package org.beatific.ddirori.repository;

public abstract class OneStateRepository<O> implements Repository<O, OneState>{

	public OneState getState() {
		return OneState.ONE;
	}

	public abstract void save(Object object);
	
	public void save(Object state, Object object) {
		save(object);
	}

	public abstract O load(Object object);
	
	public O load(Object state, Object object) {
		return load(object);
	}

	public abstract void change(Object object);
	
	public void change(Object state, Object object) {
		change(object);
	}

	public abstract void remove(Object object);
	
	public void remove(Object state, Object object) {
		remove(object);
	}

}
