//Reference (based from):
//  https://www.tutorialspoint.com/java_mysql/java_mysql_select_database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Jared {

    public static void query() {
        String command = "";
        try { 
            Connection connection = DriverManager.getConnection(SQLDatabaseConnection.connectionUrl);
            Statement stmt = connection.createStatement();
            //command = "INSERT INTO Episode(id, name, runtime, number,)";
            stmt.executeQuery(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}