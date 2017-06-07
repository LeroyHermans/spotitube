package nl.han.dea.oose.persistence.connection;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Default
public class MysqlConnectionFactory implements IConnection {

    private static final Properties prop = new Properties();

    static{
        try {
            prop.load(MysqlConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties"));
            Class.forName(prop.getProperty("jdbc.driver"));
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(prop.getProperty("jdbc.url"));
        }catch (SQLException e){
            throw new IllegalArgumentException("Cannot connect to database!", e);
        }
        return conn;
    }
}
