//Reference (based from):
//  https://www.tutorialspoint.com/java_mysql/java_mysql_select_database

import java.sql.*;
import java.util.*;

public class GroupSix {

    public static void main(String args[]) {
        Query();
    }

    public static void Query() {
        //Creating connection specifics here
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Try to get the jdbc driver?
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the java jdbc driver.");
            e.printStackTrace();
        }
        String connUrl = "jdbc:mysql://deltona.birdnest.org:3306";
        String user = "my.vaughnk3";
        String password = "!$w9oh71";
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);
        info.put("database", "my_vaughnk3_final_group6");
        info.put("encrypt", true);
        info.put("trustServerCertificate", false);
        info.put("loginTimeout", 30);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        try { 
            //Establish the connection
            connection = DriverManager.getConnection(connUrl, info);
            //To prevent injection, we must use what's called a "prepared statement."
            stmt = connection.prepareStatement(connUrl);
            stmt.setString(1, user);
            stmt.setString(2, password);

            //Replace the comments with your own methods
            //TvShow query (KJ)
            //Season query (Gavin)
            EpisodeQuery(stmt);
            //Actors in Movie (Robert)
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                //Always close the statement and the connection.
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Episode query (Jared)
    private static void EpisodeQuery(PreparedStatement stmt) throws Exception {
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