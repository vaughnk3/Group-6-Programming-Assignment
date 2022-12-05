import java.sql.*;

public class SQLDatabaseConnection {
    public static String connectionUrl =
    "jdbc:mysql://deltona.birdnest.org:3306"
                + "database=vaughnk3_final_group6;"
                + "user=my.vaughnk3;"
                + "password=!$w9oh71;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";

    public static void main (String[] args) {
        
            
     ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 10 Title, FirstName, LastName from SalesLT.Customer";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}