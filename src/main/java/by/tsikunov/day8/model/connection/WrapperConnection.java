package by.tsikunov.day8.model.connection;

import by.tsikunov.day8.exception.ConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WrapperConnection {
    private Connection connection;

    public WrapperConnection() throws ConnectionException {
        ConnectionCreator creator = new ConnectionCreator();
        connection = creator.getConnection();
        if(connection == null) {
            throw  new ConnectionException("Connection is null");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeStatement(Statement statement) {
        if(statement!= null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Can't close statement, " + e);
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Can't close connection" + e);
        }
    }
}
