//Reference (based from):
//  https://www.tutorialspoint.com/java_mysql/java_mysql_select_database.html

import java.sql.Connection;
import java.sql.DriverManager;

public class Jared {

    public static void query() {
        try { 
            Connection connection = DriverManager.getConnection(SQLDatabaseConnection.connectionUrl);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}