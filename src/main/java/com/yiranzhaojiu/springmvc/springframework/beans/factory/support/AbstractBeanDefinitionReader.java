package com.yiranzhaojiu.springmvc.springframework.beans.factory.support;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    private final BeanDefinitionRegistry registry;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int loadBeanDefinitions(String location) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location.substring(CLASSPATH_URL_PREFIX.length()));
        try {
            try {
                InputSource inputSource = new InputSource(inputStream);

                inputSource.setEncoding("UTF-8");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document document = documentBuilder.parse(inputSource);
                registerBeanDefinitions(document);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                inputStream.close();
            }
        } catch (Exception ex) {

        }

        return 0;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    protected abstract int registerBeanDefinitions(Document doc);

}
