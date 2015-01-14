package org.beatifc.ddirori.bean;

public class BeanNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2816031274484956530L;

	public BeanNotFoundException() {
		super();
	}
	
	public BeanNotFoundException(String m) {
		super(m);
	}
	
	public BeanNotFoundException(Throwable t) {
		super(t);
	}
	
	public BeanNotFoundException(String m, Throwable t) {
		super(m, t);
	}
}
