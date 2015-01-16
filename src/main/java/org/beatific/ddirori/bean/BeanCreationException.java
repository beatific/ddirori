package org.beatific.ddirori.bean;

public class BeanCreationException extends RuntimeException {

	private static final long serialVersionUID = -3300164364099270161L;

	public BeanCreationException() {
        super();
    }
	
	public BeanCreationException(String s) {
        super(s);
    }
	
	public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public BeanCreationException(Throwable cause) {
        super(cause);
    }
	
}
