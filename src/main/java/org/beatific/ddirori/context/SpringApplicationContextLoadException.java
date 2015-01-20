package org.beatific.ddirori.context;

public class SpringApplicationContextLoadException extends RuntimeException {

	private static final long serialVersionUID = 7961717388432679430L;

	public SpringApplicationContextLoadException() {
        super();
    }
	
	public SpringApplicationContextLoadException(String s) {
        super(s);
    }
	
	public SpringApplicationContextLoadException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public SpringApplicationContextLoadException(Throwable cause) {
        super(cause);
    }
}
