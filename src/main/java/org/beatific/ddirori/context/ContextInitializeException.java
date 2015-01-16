package org.beatific.ddirori.context;

public class ContextInitializeException extends RuntimeException {

	private static final long serialVersionUID = -395346773604295700L;

	public ContextInitializeException() {
		super();
	}
	
	public ContextInitializeException(String m) {
		super(m);
	}
	
	public ContextInitializeException(Throwable t) {
		super(t);
	}
	
	public ContextInitializeException(String m, Throwable t) {
		super(m, t);
	}
}
