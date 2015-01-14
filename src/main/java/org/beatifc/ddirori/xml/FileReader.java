package org.beatifc.ddirori.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {

	public static InputStream getInputStream(String filePath) throws IOException {
		InputStream is = null;
		
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		} catch (Exception ex){}
		
		if(is == null)is = ClassLoader.getSystemResourceAsStream(filePath);
		
		if (is == null) {
			throw new FileNotFoundException(filePath + " does not exist!");
		}
		return is;
	}
}
