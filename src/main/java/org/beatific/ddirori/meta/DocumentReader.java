package org.beatific.ddirori.meta;

import org.beatific.ddirori.bean.BeanDefinition;
import org.beatific.ddirori.bean.BeanDefinitionNotFoundException;
import org.beatific.ddirori.bean.NoBeanDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentReader {

	public void read(Document doc, MetaInfo meta, boolean isUseDDiroriExpression) {
		
		Element element = doc.getDocumentElement();
		String tagName = element.getNodeName();
		BeanDefinition def = getBeanDefinition(element, meta);
		completeElementDefinition(element, def, meta);
		meta.loadLevel(isUseDDiroriExpression);
	}
	
	private BeanDefinition getBeanDefinition(Node element, MetaInfo meta) {
		
		BeanDefinition def = null;
		try {
			def = (BeanDefinition)meta.getMeta(element.getNodeName()).clone();
		} catch (BeanDefinitionNotFoundException e) {
			def = new NoBeanDefinition(element.getNodeName());
		}
		
		return def;
	}
	
	protected BeanDefinition completeElementDefinition(Node element, BeanDefinition parentDefinition, MetaInfo meta) {
		
		BeanDefinition def = getBeanDefinition(element, meta);
		
		def.setParentElementDefinition(parentDefinition);
		loadAttribute(element, def, meta);
		
		NodeList childElementsList = element.getChildNodes();
		for (int i = 0; i < childElementsList.getLength(); i++) {
			Node childNode = childElementsList.item(i);

			switch(childNode.getNodeType()) {
			case Node.ELEMENT_NODE : 
				BeanDefinition childDefinition = completeElementDefinition(childNode, def, meta);
				def.addChildElementDeifinition(childDefinition);
				break;
			}
		}
		return def;
	}
	
	protected void loadAttribute(Node element, BeanDefinition definition, MetaInfo meta) {
		NamedNodeMap nodeAttributes = element.getAttributes();
		for(int i =0; i < nodeAttributes.getLength(); i++) {
			Node attribute = nodeAttributes.item(i);
			definition.addStringAttribute(attribute.getNodeName(), attribute.getNodeValue());
			meta.setAttributeDefinition(definition, attribute.getNodeValue());
		}
	}
}
