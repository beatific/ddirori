package org.beatific.ddirori.bean;

import java.util.HashMap;
import java.util.Map;

import org.beatific.ddirori.attribute.AttributeExtractor;
import org.beatific.ddirori.bean.impl.DDiroriBeanContainer;
import org.beatific.ddirori.bean.impl.SpringBeanContainer;
import org.beatific.ddirori.meta.BeanDefinition;
import org.beatific.ddirori.meta.BeanDefinitionNotFoundException;
import org.beatific.ddirori.meta.MetaInfo;
import org.springframework.context.ApplicationContext;

public abstract class BeanLoader {

	private final AttributeExtractor extractor;
	protected final String[] basePackage;
	private final BeanContainer container;
	
    public BeanLoader(String basePackage) {
		
    	this(basePackage, null); 
	}

	public BeanLoader(String basePackage, ApplicationContext context) {
		
		this.container = context != null ? new SpringBeanContainer(context) : new DDiroriBeanContainer();
		this.basePackage = basePackage == null ? null : basePackage.split(",");
		this.extractor = new AttributeExtractor(this.basePackage) {

			@Override
			protected Object getObject(BeanContainer container,
					String objectName) {
				return container.getObject(objectName);
			}
			
		};
	}

	private BeanContainer getBeanContainer() {
		return this.container;
	}
	
	protected void initContainer() throws BeanDefinitionNotFoundException {
		MetaInfo meta = buildMetaInfo();
		init(meta);
	}
	
	protected abstract MetaInfo buildMetaInfo();
	
	private void init(MetaInfo meta) throws BeanDefinitionNotFoundException {
		for(int i = 0 ; i < meta.getLevel(); i++) 
			for(BeanDefinition definition : meta.getMetaByLevel(i+1))
				load(definition);
	}
	
	
	private Map<String, Object> loadAttributes(Map<String, String> attributes) {

		Map<String, Object> map = new HashMap<String, Object>();
		for(String key : attributes.keySet()) {
			String value = attributes.get(key);
			map.put(key, extractor.extract(getBeanContainer(), value));
		}
		return map;
	}
	
    protected void registerObject(BeanDefinition definition, Object object) {
		
        switch(definition.getTag()) {
		
		case BEAN :
			getBeanContainer().registerBean(definition.getBeanName(), object);
			break;
			
		case TEMP :
			getBeanContainer().registerTemp(definition.getBeanName(), object);
			break;
			
		case ATTRIBUTE :
			break;
			
		default : 
			throw new BeanCreationException("Bean Creation Exception[" + definition.getBeanName() + "]");
		}
	}

	private void load(BeanDefinition definition) {
		
		loadWithoutRelation(definition);
	}
	
    private void loadWithoutRelation(BeanDefinition definition) {
    	
		Object object = definition.getConstructor().create(definition.getParentElementDefinition(), definition.getChildElementDeifinitions(), loadAttributes(definition.getAttributes()));
		registerObject(definition, object);
	}
	
	public Object getBean(String beanName) {
		return getBeanContainer().getBean(beanName);
	}
	
	protected synchronized void destory() {
		getBeanContainer().destory();
	}
}
