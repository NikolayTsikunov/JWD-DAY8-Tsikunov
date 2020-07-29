package by.tsikunov.day8.model.connection;

import by.tsikunov.day8.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionCreator {
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASS = "password";
    private static final String AUTO_RECONNECT = "autoReconnect";
    private static final String CHARACTER_ENCODING = "characterEncoding";
    private static final String SERVER_TIME_ZONE = "serverTimezone";
    private static final String USE_UNICODE = "useUnicode";

    private final Connection connection;

    public ConnectionCreator() throws ConnectionException {
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("property.database");
            String url = resourceBundle.getString(URL);
            String user = resourceBundle.getString(USER);
            String pass = resourceBundle.getString(PASS);
            String autoReconnect = resourceBundle.getString(AUTO_RECONNECT);
            String characterEncoding = resourceBundle.getString(CHARACTER_ENCODING);
            String serverTimeZone = resourceBundle.getString(SERVER_TIME_ZONE);
            String useUnicode = resourceBundle.getString(USE_UNICODE);
            Properties properties = new Properties();
            properties.put(USER, user);
            properties.put(PASS, pass);
            properties.put(CHARACTER_ENCODING, characterEncoding);
            properties.put(AUTO_RECONNECT, autoReconnect);
            properties.put(SERVER_TIME_ZONE, serverTimeZone);
            properties.put(USE_UNICODE, useUnicode);
            connection = DriverManager.getConnection(url, properties);
        } catch (MissingResourceException | SQLException e) {
            throw new ConnectionException("Can't create connection to database", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
