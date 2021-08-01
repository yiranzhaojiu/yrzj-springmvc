package com.yiranzhaojiu.springmvc.springframework.beans.factory.xml;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.support.GenericBeanDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private final String ROOT = "beans";
    public static final String BEAN_ELEMENT = "bean";
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public int registerBeanDefinitions(Document doc) {
        Element element = doc.getDocumentElement();
        if (element.getNodeName().equals(ROOT)) {
            NodeList nl = element.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                if (node instanceof Element) { //node是元素节点
                    if(node.getNodeName().equals(BEAN_ELEMENT)){ //只支持XML-Bean对象解析
                        String beanClazz = ((DeferredElementImpl) node).getAttribute("class");
                        String beanName = ((DeferredElementImpl) node).getAttribute("name");
                        //if (StringUtils.isNotBlank(clazz)) { //先野蛮处理后期优化
                        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                        beanDefinition.setBeanName(beanName);
                        beanDefinition.setBeanClass(beanClazz);
                        getRegistry().registerBeanDefinition(beanName, beanDefinition);
                        //}
                    }
                    else {

                    }
                }
            }
        }
        return 0;
    }
}
