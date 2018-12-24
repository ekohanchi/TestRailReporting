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
	
	public static final String FILE_PROPERTY_RESOURCE = "file:${TESTRAIL_REPORT_HOME}/conf/application.properties";
    public static final String CLASSPATH_PROPERTY_RESOURCE = "classpath:application.properties";
	
	@Value("${testrail.baseurl}")
	private String baseUrl;
	
	@Value("${testrail.username}")
	private String userName;
	
	@Value("${testrail.password}")
	private String password;
	
	@Value("${page.logopath}")
	private String pageLogoPath;
	
	@Value("${testrail.status.customstatus1}")
	private String customStatus1Text;
	
	@Value("${testrail.status.customstatus2}")
	private String customStatus2Text;
	
	@Value("${testrail.status.customstatus3}")
	private String customStatus3Text;
	
	@Value("${testrail.status.customstatus4}")
	private String customStatus4Text;
	
	@Value("${testrail.status.customstatus5}")
	private String customStatus5Text;
	
	@Value("${testrail.status.customstatus6}")
	private String customStatus6Text;
	
	@Value("${testrail.status.customstatus7}")
	private String customStatus7Text;

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getPageLogoPath() {
		return pageLogoPath;
	}

	public String getCustomStatus1Text() {
		return customStatus1Text;
	}

	public String getCustomStatus2Text() {
		return customStatus2Text;
	}

	public String getCustomStatus3Text() {
		return customStatus3Text;
	}

	public String getCustomStatus4Text() {
		return customStatus4Text;
	}

	public String getCustomStatus5Text() {
		return customStatus5Text;
	}

	public String getCustomStatus6Text() {
		return customStatus6Text;
	}

	public String getCustomStatus7Text() {
		return customStatus7Text;
	} 
	
	

}
