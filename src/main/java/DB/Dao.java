package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    // connect DB
    private Connection conn = null;

    public Dao() {
        String hostName = "localhost";
        String dbName = "climate";
        String userName = "root";
        String password = "";
        String dbURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
                + "?useSSL=false&useUnicode=true&characterEncoding=utf8";

        if (conn == null) {
            try {
                conn = DriverManager.getConnection(dbURL, userName, password);
                System.out.println("Connecting...");
            } catch (SQLException ex) {
                System.err.println("Cannot connect database " + ex);
            }
        }
    }


}
