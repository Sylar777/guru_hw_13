package helpers;

public class Configurations {
    public static final String BROWSER = System.getProperty("browser", "chrome");
    public static final String BROWSER_VERSION = System.getProperty("browserVersion", "latest");
    public static final String WDHOST = System.getProperty("Wdhost", "http://localhost:4444/wd/hub");
    public static final String BROWSER_SIZE = System.getProperty("browserSize", "1920x1080");
}
