package org.beatific.ddirori.utils;

import java.util.ArrayList;
import java.util.List;

public class CopyObject {

	private List<String> strings = new ArrayList<String>();
	private int abc = 10;
	private CopyObject object = new CopyObject();
	
	public void setAbc(int abc) {
		this.abc = abc;
	}
	
	public CopyObject getObject() {
		return this.object;
	}

	public List<String> getStrings() {
		return strings;
	}
	
}
