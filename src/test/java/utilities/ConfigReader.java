package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {


    static Properties properties;

    static {

        try {
            String filePath = "configuration.properties";
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis);

        } catch (FileNotFoundException e) {
            System.out.println("file could not be found");
        } catch (IOException e) {
            System.out.println("Could not be able to load properties file");
        }
    }

    public static String getProperty(String key){

        return properties.getProperty(key);

    }
    public static String get(String keyName) {

        return properties.getProperty(keyName);
    }

}