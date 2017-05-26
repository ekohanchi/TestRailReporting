package com.project.testrail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import static com.project.testrail.ApplicationConfig.*;

//@PropertySource("classpath:application.properties")
//@PropertySource({ CLASSPATH_PROPERTY_RESOURCE, FILE_PROPERTY_RESOURCE })

@Configuration
@PropertySource({ CLASSPATH_PROPERTY_RESOURCE, FILE_PROPERTY_RESOURCE })
public class ApplicationConfig {
	
	public static final String FILE_PROPERTY_RESOURCE = "file:${TESTRAIL_INT_HOME}/conf/application.properties";
    public static final String CLASSPATH_PROPERTY_RESOURCE = "classpath:application.properties";
	
	@Value("${testrail.baseurl}")
	private String baseUrl;
	
	@Value("${testrail.username}")
	private String userName;
	
	@Value("${testrail.password}")
	private String password;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	} 
	

}
