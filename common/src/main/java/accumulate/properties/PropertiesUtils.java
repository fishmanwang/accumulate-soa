package accumulate.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Properties文件工具类.
 * 
 */
public abstract class PropertiesUtils {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties defaultInitProperties;
    private static String systemPropertiesPath = null;

    public static void setInitProperty(Properties props) {
        defaultInitProperties = props;
    }

    public static String getInitProperty(String name) {
        return defaultInitProperties.getProperty(name);
    }
    
    public static String getInitProperty(String name, String defaultValue) {
        if (defaultInitProperties.getProperty(name) == null || defaultInitProperties.getProperty(name).equals("")) {
            return defaultValue;
        }
        return defaultInitProperties.getProperty(name);
    }
    
    public static void updateSystemConfigProperty(String name, String value) {
        defaultInitProperties.setProperty(name, value);
        if (systemPropertiesPath == null) {
            ConfigurableApplicationContext  ctx = (ConfigurableApplicationContext)SpringContextHolder.getApplicationContext();
            ConfigurableListableBeanFactory fb = ctx.getBeanFactory();
            BeanDefinition bf = fb.getBeanDefinition("propertiesHolder");
            //String[] location = (String[]) bf.getPropertyValues().getPropertyValue("locations").getValue();
            ManagedList values =  (ManagedList)bf.getPropertyValues().getPropertyValue("locations").getValue();
            TypedStringValue valueStr = (TypedStringValue)values.get(0);
            //classpath*:/properties/test/*.properties
            String propertiesPath = valueStr.getValue();
            if (propertiesPath != null) {
                propertiesPath = propertiesPath.replace("classpath*:/", "").replace("*.properties", "systemConfig.properties");
            }
            try {
                systemPropertiesPath = PropertiesUtils.class.getClassLoader().getResource(propertiesPath).toURI().getPath();
            } catch (URISyntaxException e) {
                logger.error("Exception in geting systemConfig.properties path", e);
            }
        }
        //将内容刷回文件中
        if (null == systemPropertiesPath) {
            logger.error("Can not find the path for systemConfig.properties.");
        } else {
            InputStream is = null;
            Properties property = null;
            try {
                is = new FileInputStream(systemPropertiesPath);
                property = new Properties();
                property.load(is);
            } catch (Exception e) {
                logger.error("Exception in saving systemConfig.properties", e);
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        logger.error("Exception in loading systemConfig.properties", e);
                    }
                }
            }
            OutputStream os = null;
            try {
                os = new FileOutputStream(systemPropertiesPath);
                property.setProperty(name, value);
                property.store(os, "###This is a writable properties file.###");
            } catch (Exception e) {
                logger.error("Exception in saving systemConfig.properties", e);
            } finally {
                if (null != os) {
                    try {
                        os.close();
                    } catch (Exception e) {
                        logger.error("Exception in saving systemConfig.properties", e);
                    }
                }
            }
        }
    }
    
}
