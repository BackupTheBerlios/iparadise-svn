package com.pdpsoft.web.configuration;

import java.io.IOException;
import java.util.PropertyResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pdpsoft.commons.InitializationException;
import com.pdpsoft.commons.ResourceLocator;

/**
 * PDP Company, G16
 * User: h_shayan
 * Date: Feb 23, 2009
 * Time: 2:11:08 PM
 */
public class WebConfiguration {

	private static final Log logger = LogFactory.getLog(WebConfiguration.class);
	
	private String host;
	private String applicationServer;
	private ServiceLocatorTypeEnum serviceLocatorType;
	
	private static class WebConfigurationHolder {
		private static WebConfiguration configuration;
		
		static {
			try {
				configuration = new WebConfiguration();
				logger.info("web-configuration.properties is processing ...");
				
				PropertyResourceBundle bundle = new PropertyResourceBundle(
						ResourceLocator
								.getResourceAsStream("web-configuration.properties"));
				configuration.setHost(bundle.getString("EJB-AS-host"));
				configuration.setApplicationServer(bundle.getString("AS_type"));				
				determineServiceLocatorType(bundle.getString("ServiceLocatorType"));
				
				logger.info("web-configuration.properties processed");
            } catch (IOException e) {
                throw new InitializationException("IOException while reading web-configuration.properties file ", e);
            }
		}

		private static void determineServiceLocatorType(
				final String serviceLocatorType) {
			if(serviceLocatorType.equalsIgnoreCase("EJBRemote"))
				configuration.setServiceLocatorType(ServiceLocatorTypeEnum.EJBRemote);
			if(serviceLocatorType.equalsIgnoreCase("EJBLocal"))
				configuration.setServiceLocatorType(ServiceLocatorTypeEnum.EJBLocal);
			if(serviceLocatorType.equalsIgnoreCase("POJO"))
				configuration.setServiceLocatorType(ServiceLocatorTypeEnum.POJO);
		}
	}
	
	public static WebConfiguration getInstance() {
		return WebConfigurationHolder.configuration;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApplicationServer() {
		return applicationServer;
	}

	public void setApplicationServer(String applicationServer) {
		this.applicationServer = applicationServer;
	}

	private void setServiceLocatorType(ServiceLocatorTypeEnum serviceLocatorType) {
		this.serviceLocatorType = serviceLocatorType;
	}

	public ServiceLocatorTypeEnum getServiceLocatorType() {
		return serviceLocatorType;
	}

}
