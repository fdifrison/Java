package advisor.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    static Properties properties = new Properties();

    static Integer serverPort;
    static String clientId;
    static String clientSecret;

    static {
        try (FileInputStream input = new FileInputStream("C:\\Users\\gfrison\\Desktop\\Programming\\HyperSkill\\Music Advisor\\task\\src\\resources\\application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("No property files found " + e);
        }
        serverPort = Integer.valueOf(properties.getProperty("port"));
        clientId = properties.getProperty("client_id");
        clientSecret = properties.getProperty("client_secret");
    }


    public static Integer getServerPort() {
        return serverPort;
    }
    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

}
