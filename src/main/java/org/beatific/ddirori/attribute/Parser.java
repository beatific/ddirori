package org.beatific.ddirori.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	private final static String REGEX_OBJECT = "\\$\\{(\\w*)\\}";
	private final static String REGEX_METHOD_ARGS = "(\\w+)\\.(\\w+)\\(([\\w\\,\\s\\'\\-\\.\\$\\[\\]]*)";
	
	public static Matcher getMatcher(String source, String pattern) {
		return Pattern.compile(pattern).matcher(source);
	}
	
	public static List<String> getRelatedObjectNames(String str) {
		List<String> objectNames = new ArrayList<String>();
		Matcher matcher = getMatcher(str, REGEX_OBJECT);
		while (matcher.find()) {
			
			String attribute = matcher.group(1);
			Matcher methodMatcher = getMatcher(attribute, REGEX_METHOD_ARGS);
		      
			if (methodMatcher.find()) {
		    	  attribute = methodMatcher.group(1);
		    }
			objectNames.add(attribute);
		}
		return objectNames;
		
	}
	
}
