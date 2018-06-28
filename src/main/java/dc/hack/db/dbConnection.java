package dc.hack.db;

import java.sql.*;

public class dbConnection {


    public static void main(String args[]){

        System.out.println("Connecting to postgres db");
    }


    public static Connection getConnection() {
        Connection dbConn = null;
        String dbName = "DB_NAME";
        String userName ="RDS_USERNAME";
        String password = "RDS_PASSWORD";
        String hostname = "RDS_HOSTNAME";
        String port = "RDS_PORT";
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        String DB_URL = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;


        try {
            Class.forName("org.postgresql.Driver");
            dbConn = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        return dbConn;
    }
}
