package org.beatific.ddirori.bean.impl;

public class NotSupportedApplicationContextExcpetion extends RuntimeException {
	
	private static final long serialVersionUID = -257903283522173293L;

	public NotSupportedApplicationContextExcpetion() {
        super();
    }
	
	public NotSupportedApplicationContextExcpetion(String s) {
        super(s);
    }
	
	public NotSupportedApplicationContextExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
	
	public NotSupportedApplicationContextExcpetion(Throwable cause) {
        super(cause);
    }
}
