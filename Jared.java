//Reference (based from):
//  https://www.tutorialspoint.com/java_mysql/java_mysql_select_database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jared {

    public static void query() {
        String command = "";
        String output = "";
        try { 
            //TODO: Also, probably not safe due to SQL injection problem, so I'm going to try to see what I can do to fix that.
            Connection connection = DriverManager.getConnection(SQLDatabaseConnection.connectionUrl);
            Statement stmt = connection.createStatement();
            //Creation
            command = "INSERT INTO Episode(id, seasonID, episodeNumber, episodeName, description, runtime) VALUES (NULL, 1, 1, \"The Office Field Guide\", \"When a documentary crew arrives at the office, manager Michael Scott attempts to paint a rosy picture but fails after learning the company will be downsizing.\" 23);";
            stmt.executeQuery(command);
            //Retrieval (must display the results using the ResultingSet object)
            command = "SELECT * FROM Episode";
            ResultSet retrievalSet = stmt.executeQuery(command);
            while(retrievalSet.next()) {
                output += "\n Object ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
                output += "\n\t Season: " + String.valueOf(retrievalSet.getInt("seasonID"));
                output += "\n\t Episode: " + String.valueOf(retrievalSet.getInt("episodeNumber"));
                output += "\n\t Name: " + retrievalSet.getString("episodeName");
                output += "\n\t Description: " + retrievalSet.getString("description");
                output += "\n\t Runtime: " + String.valueOf(retrievalSet.getInt("runtime"));
            }
            System.out.println("Selected Episode tuples: " + output);
            output = "";
            //Update
            command = "UPDATE Episode SET episodeName=\"The Office Pilot Ep. 1: The Office Field Guide\" WHERE seasonID=1 AND episodeNumber=1;";
            stmt.executeQuery(command);
            //Deletion
            command = "DELETE FROM Episode WHERE seasonID=1 AND episodeNumber=1;";
            stmt.executeQuery(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}