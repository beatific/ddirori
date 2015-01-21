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
	
	public int save(Object state, Object object);
	
	public O load(Object state, Object object);
	
	public int change(Object state, Object object);
	
	public int remove(Object state, Object object);
}
