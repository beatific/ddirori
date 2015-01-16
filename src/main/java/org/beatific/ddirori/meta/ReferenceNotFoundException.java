package org.beatific.ddirori.meta;

public class ReferenceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1167994082582623444L;

	public ReferenceNotFoundException() {
		super();
	}
	
	public ReferenceNotFoundException(String m) {
		super(m);
	}
	
	public ReferenceNotFoundException(Throwable t) {
		super(t);
	}
	
	public ReferenceNotFoundException(String m , Throwable t) {
		super(m, t);
	}
}
