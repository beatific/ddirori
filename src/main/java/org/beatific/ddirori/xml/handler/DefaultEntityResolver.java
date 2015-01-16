package org.beatific.ddirori.xml.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

import org.beatific.ddirori.xml.FileReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DefaultEntityResolver implements EntityResolver {
	
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		
		if(systemId != null && systemId.endsWith(".xsd")) {
			
			String resourcePath = null;
			try {
				String decodedSystemId = URLDecoder.decode(systemId, "UTF-8");
				String givenUrl = new URL(decodedSystemId).toString();
				String systemRootUrl = new File("").toURI().toURL().toString();
				
				if (givenUrl.startsWith(systemRootUrl)) {
					resourcePath = givenUrl.substring(systemRootUrl.length());
				}
				
			} catch (Exception ex) {
				resourcePath = systemId;
			}
			
			if (resourcePath != null) {
			
				InputStream stream = FileReader.getInputStream(resourcePath);
				InputSource source = new InputSource(stream);
				source.setPublicId(publicId);
				source.setSystemId(systemId);
				return source;
			}
		}
		
		return null;
		
	}
	
}
