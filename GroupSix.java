//Reference (based from):
//  https://www.tutorialspoint.com/java_mysql/java_mysql_select_database

import java.sql.*;
import java.util.*;

public class GroupSix {

    public static void main(String args[]) {
        query();
    }

    public static void query() {
        //Creating connection specifics here
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Try to get the jdbc driver?
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the java jdbc driver.");
            e.printStackTrace();
        }
        String connUrl = "jdbc:mysql://deltona.birdnest.org:3306";
        Properties info = new Properties();
        info.put("user", "my.vaughnk3");
        info.put("password", "!$w9oh71");
        info.put("database", "my_vaughnk3_final_group6");
        info.put("encrypt", true);
        info.put("trustServerCertificate", false);
        info.put("loginTimeout", 30);
        
        try { 
            //TODO: Also, probably not safe due to SQL injection problem, so I'm going to try to see what I can do to fix that.
            //Establish the connection
            Connection connection = DriverManager.getConnection(connUrl, info);
            Statement stmt = connection.createStatement();

            //TvShow (KJ)
            
            //Season (Gavin)

            //Episode (Jared)

            //Actors in Movie? (Robert)
            EpisodeQuery(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void EpisodeQuery(Statement stmt) throws Exception {
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Episode(id, seasonID, episodeNumber, episodeName, description, runtime) VALUES (NULL, 1, 1, \"The Office Field Guide\", \"When a documentary crew arrives at the office, manager Michael Scott attempts to paint a rosy picture but fails after learning the company will be downsizing.\" 23);";
        stmt.executeUpdate(command);
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Episode;";
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
        retrievalSet.close();
        //Update
        command = "UPDATE Episode SET episodeName=\"The Office Pilot Ep. 1: The Office Field Guide\" WHERE seasonID=1 AND episodeNumber=1;";
        stmt.executeUpdate(command);
        //Deletion
        command = "DELETE FROM Episode WHERE seasonID=1 AND episodeNumber=1;";
        stmt.executeUpdate(command);
    }
}