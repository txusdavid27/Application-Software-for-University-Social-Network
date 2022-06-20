package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    Properties properties;

    public PropertiesReader()
    {
        properties= new Properties();
        loadProperties();
    }

    private void loadProperties()
    {

        try {
            //System.out.println(PropertiesReader.class.getResource("config.properties"));
            InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFilePath()
    {
        return (String) properties.get("path.file");
    }
}
