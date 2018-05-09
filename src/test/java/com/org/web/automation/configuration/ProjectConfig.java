package com.org.web.automation.configuration;

import java.util.Properties;

import org.springframework.stereotype.Component;


@Component
public class ProjectConfig
		extends Configuration
		implements IPropertiesDefinitionsContainer, IWebDriverConfiguration, IUserConfiguration, IEnvironmentConfiguration {

	public ProjectConfig(String env) {
		super(env);
	}
	
	private Properties properties;

	@Property("env")
	private String env;

	@Property("env.name")
	private String envName;

	@Property("env.url")
	private String envUrl;

	@Property("user.admin.name")
	private String userAdminName;

	@Property("user.admin.password")
	private String userAdminPassword;

	@Property("webdriver.firefox.driver")
	private String firefoxDriverPath;

	@Property("webdriver.chrome.driver")
	private String chromeDriverPath;

	@Property("webdriver.ie.driver")
	private String internetExplorerDriverPath;

	@Property("webdriver.safari.driver")
	private String safariDriverPath;
	
	@Property("browser.default")
	private String browser;

	@Property("wait.default.timeout")
	private Integer defaultTimeout;
	
	@Property("report.excel")
	private Boolean enableExcelReporter;
	
	@Property("report.extent")
	private Boolean enableExtentReporter;
	
	@Property("report.klov")
	private Boolean enableKlovReporter;
	
	@Property("screenshot.save.path")
	private String screenshotSavePath;

	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public Object getValue(String key) {
		return properties.get(key);
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
		super.setEnv(env);
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvUrl() {
		return envUrl;
	}

	public void setEnvUrl(String envUrl) {
		this.envUrl = envUrl;
	}

	public String getUserAdminName() {
		return userAdminName;
	}

	public void setUserAdminName(String userAdminName) {
		this.userAdminName = userAdminName;
	}

	public String getUserAdminPassword() {
		return userAdminPassword;
	}

	public void setUserAdminPassword(String userAdminPassword) {
		this.userAdminPassword = userAdminPassword;
	}

	public String getFirefoxDriverPath() {
		return firefoxDriverPath;
	}

	public void setFirefoxDriverPath(String firefoxDriverPath) {
		this.firefoxDriverPath = firefoxDriverPath;
	}

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

	public String getInternetExplorerDriverPath() {
		return internetExplorerDriverPath;
	}

	public void setInternetExplorerDriverPath(String ieDriverPath) {
		this.internetExplorerDriverPath = ieDriverPath;
	}

	public String getSafariDriverPath() {
		return safariDriverPath;
	}

	public void setSafariDriverPath(String safariDriverPath) {
		this.safariDriverPath = safariDriverPath;
	}
	
	public String getBrowser() {
		return browser;
	}

	public void setBrowsers(String browser) {
		this.browser = browser;
	}

	public Integer getDefaultTimeout() {
		return defaultTimeout;
	}

	public void setDefaultTimeout(Integer defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}
	
	public Boolean getEnableExcelReporter() {
		return enableExcelReporter;
	}

	public void setEnableExcelReporter(Boolean enableExcelReporter) {
		this.enableExcelReporter = enableExcelReporter;
	}

	public Boolean getEnableExtentReporter() {
		return enableExtentReporter;
	}

	public void setEnableExtentReporter(Boolean enableExtentReporter) {
		this.enableExtentReporter = enableExtentReporter;
	}

	public Boolean getEnableKlovReporter() {
		return enableKlovReporter;
	}

	public void setEnableKlovReporter(Boolean enableKlovReporter) {
		this.enableKlovReporter = enableKlovReporter;
	}
	
	public String getScreenshotSavePath() {
		return screenshotSavePath;
	}

	public void setScreenshotSavePath(String screenshotSavePath) {
		this.screenshotSavePath = screenshotSavePath;
	}

	@Override
	public String toString() {
		String s = "{ " +
				"env=" + getEnv() +
				", envName=" + envName +
				", envUrl=" + envUrl +
				", userAdminName=" + userAdminName +
				", userAdminPassword=" + userAdminPassword +
				", webdriver.chrome.driver=" + chromeDriverPath +
				", webdriver.ie.driver=" + internetExplorerDriverPath +
				", webdriver.firefox.driver=" + firefoxDriverPath +
				", webdriver.safari.driver=" + safariDriverPath +
				", browser.default=" + browser +
				" }";
		return s;
	}

}
