package org.beatific.ddirori.meta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentReader {

	public void read(Document doc, MetaInfo meta) {
		
		Element element = doc.getDocumentElement();
		String tagName = element.getNodeName();
		BeanDefinition def;
		try {
			def = meta.getMeta(tagName);
		} catch (BeanDefinitionNotFoundException e) {
			throw new DocumentParseException("Can't find BeanDefinition[" + tagName + "]");
		}
		completeElementDefinition(element, def, meta);
		meta.loadLevel();
	}
	
	protected BeanDefinition completeElementDefinition(Node element, BeanDefinition parentDefinition, MetaInfo meta) {
		
		BeanDefinition def;
		try {
			def = meta.getMeta(element.getNodeName());
		} catch (BeanDefinitionNotFoundException e) {
			throw new DocumentParseException();
		}
		
		if (def != null) {
			
			def.setParentElementDefinition(parentDefinition);
			NodeList childElementsList = element.getChildNodes();
			for (int i = 0; i < childElementsList.getLength(); i++) {
				final Node childNode = childElementsList.item(i);

				switch(childNode.getNodeType()) {
				case Node.ELEMENT_NODE : 
					BeanDefinition childDefinition = completeElementDefinition(childNode, def, meta);
					def.addChildElementDeifinition(childDefinition);
					break;
				case Node.ATTRIBUTE_NODE :
					loadAttribute(childNode, def, meta);
					break;
				}
			}
		}
		
		return def;
	}
	
	protected void loadAttribute(Node element, BeanDefinition definition, MetaInfo meta) {
		definition.getAttributes().put(element.getNodeName(), element.getNodeValue());
	}
}
