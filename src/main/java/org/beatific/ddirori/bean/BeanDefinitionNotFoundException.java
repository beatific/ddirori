package org.beatific.ddirori.bean;

public class BeanDefinitionNotFoundException extends Exception{

	private static final long serialVersionUID = -8270272120341076492L;

	public BeanDefinitionNotFoundException() {
		super();
	}
	
	public BeanDefinitionNotFoundException(String m) {
		super(m);
	}
	
	public BeanDefinitionNotFoundException(Throwable t) {
		super(t);
	}
	
	public BeanDefinitionNotFoundException(String m, Throwable t) {
		super(m, t);
	}
}
