//Gavin T. Anderson
//References: 
  //https://learn.microsoft.com/en-us/sql/connect/jdbc/step-3-proof-of-concept-connecting-to-sql-using-java?view=sql-server-ver16

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class SQLDatabaseConnection {
    //connecting to database
    public static void main(String[] args) {
        String connectionUrl =
            "jdbc:mysql://deltona.birdnest.org:3306"
                        + "database=vaughnk3_final_group6;"
                        + "user=my.vaughnk3;"
                        + "password=!$w9oh71;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // TODO: 
        }       Statement statement = connection.createStatement();) {

            // Allow user to type in information to add to the database
           

            // Print results from select statement for testing purposes
            

        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}