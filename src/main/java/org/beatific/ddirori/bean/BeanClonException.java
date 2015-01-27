package org.beatific.ddirori.bean;

public class BeanClonException extends RuntimeException {
	
	private static final long serialVersionUID = 8368038264846685352L;

	public BeanClonException() {
		super();
	}
	
	public BeanClonException(String m) {
		super(m);
	}
	
	public BeanClonException(Throwable t) {
		super(t);
	}
	
	public BeanClonException(String m, Throwable t) {
		super(m, t);
	}
}
