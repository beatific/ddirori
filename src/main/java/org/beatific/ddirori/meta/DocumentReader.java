package org.beatific.ddirori.meta;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentReader {

	public void read(Document doc, MetaInfo meta) {
		
		Element element = doc.getDocumentElement();
		String tagName = element.getNodeName();
		BeanDefinition def = null;
		try {
			def = meta.getMeta(tagName);
		} catch (BeanDefinitionNotFoundException e) {}
		
		completeElementDefinition(element, def, meta);
		meta.loadLevel();
	}
	
	protected BeanDefinition completeElementDefinition(Node element, BeanDefinition parentDefinition, MetaInfo meta) {
		
		BeanDefinition def = null;
		try {
			def = meta.getMeta(element.getNodeName());
		} catch (BeanDefinitionNotFoundException e) {
//			throw new DocumentParseException();
		}
		
		if (def != null)  {
			def.setParentElementDefinition(parentDefinition);
			loadAttribute(element, def, meta);
		}
			NodeList childElementsList = element.getChildNodes();
			for (int i = 0; i < childElementsList.getLength(); i++) {
				Node childNode = childElementsList.item(i);

				switch(childNode.getNodeType()) {
				case Node.ELEMENT_NODE : 
					BeanDefinition childDefinition = completeElementDefinition(childNode, def, meta);
					if (def != null) def.addChildElementDeifinition(childDefinition);
//					loadAttribute(childNode, childDefinition, meta);
					break;
				}
			}
//		}
		
		return def;
	}
	
	protected void loadAttribute(Node element, BeanDefinition definition, MetaInfo meta) {
		NamedNodeMap nodeAttributes = element.getAttributes();
		for(int i =0; i < nodeAttributes.getLength(); i++) {
			Node attribute = nodeAttributes.item(i);
			definition.getAttributes().put(attribute.getNodeName(), attribute.getNodeValue());
			meta.setAttributeDefinition(definition, attribute.getNodeValue());
		}
	}
}
