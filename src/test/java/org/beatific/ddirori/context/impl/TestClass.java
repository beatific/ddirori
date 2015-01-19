package org.beatific.ddirori.context.impl;

public class TestClass {

	private String attribute;
	private Test2Class test2;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public Test2Class getTest2() {
		return test2;
	}

	public void setTest2(Test2Class test2) {
		this.test2 = test2;
	}

	@Override
	public String toString() {
		return "TestClass [attribute=" + attribute + ", test2=" + test2 + "]";
	}

	
}
