package com.accumulate.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    
    private static Logger LOG = LoggerFactory.getLogger(CustomizedPropertyPlaceholderConfigurer.class);
    
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);        
        for (Object key : props.keySet()) {
            String keyStr = key.toString();            
            //从环境变量读取值，重写配置文件
            String value = this.resolvePlaceholder(keyStr, props, 2);
            if (!props.getProperty(keyStr).equals(value)) {  
                LOG.info("key " + keyStr + " was overrided from env: " + value);
                props.setProperty(keyStr, value);
            }
        }
        PropertiesUtils.setInitProperty(props);
    }

}
