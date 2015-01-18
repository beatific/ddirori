package org.beatific.ddirori.maps;

public class NotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 5792230134373079635L;

	public NotSupportedException() {
		super();
	}
	
	public NotSupportedException(String m) {
		super(m);
	}
	
	public NotSupportedException(Throwable t) {
		super(t);
	}
	
	public NotSupportedException(String m, Throwable t) {
		super(m, t);
	}
	
}
