package org.beatific.ddirori.repository;

/**
 * 
 * warn : repository must have the constructor without parameter
 * 
 * @author beatificho
 *
 */
public interface Repository<O, S extends Enum<?>> {

	public S getState();
	
	public void save(Object state, Object object);
	
	public O load(Object state, Object object);
	
	public void change(Object state, Object object);
	
	public void remove(Object state, Object object);
}
