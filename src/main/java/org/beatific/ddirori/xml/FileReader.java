package org.beatific.ddirori.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.beatific.ddirori.context.ClassUtils;

public class FileReader {

	public static InputStream getInputStream(String filePath) throws IOException {
		InputStream is = null;
		
		is = ClassUtils.getDefaultClassLoader().getResourceAsStream(filePath);
		if (is == null) {
			throw new FileNotFoundException(filePath + " does not exist!");
		}
		return is;
	}
}
