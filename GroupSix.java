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
            TVShowQuery(stmt);
            SeasonQuery(stmt);
            EpisodeQuery(stmt);
            PersonQuery(stmt);
            ACTS_IN_EPQuery(stmt);

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

    //kj
    private static void TVShowQuery(PreparedStatement stmt) throws Exception {
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO TVShow (id, name, yearReleased, description) VALUES (NULL, \"The Office\", 2004, \"The Office with Mitchel Scotch and Jimmy and Dwigt Schrute\");";
        stmt.executeUpdate(command);


        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM TVShow;";
        ResultSet retrievalSet = stmt.executeQuery(command);
        
        while(retrievalSet.next()) {
            output += "\n TV Show ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t TV Show Name: " + (retrievalSet.getString("name"));
            output += "\n\t Year Released: " + String.valueOf(retrievalSet.getInt("yearReleased"));
            output += "\n\t Description: " + retrievalSet.getString("description");
        }
        System.out.println("Selected TV Show tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE TVShow SET description=\"The Office with Michael Scott and Jimmy and Dwight Schrute. New!\" WHERE name=\"The Office\";";
        stmt.executeUpdate(command);

        //Deletion
        command = "DELETE FROM TVShow WHERE name=\"The Office\";";
        stmt.executeUpdate(command);
    }
    
    
    //gavin
    private static void SeasonQuery(PreparedStatement stmt) throws Exception {
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Season (id, seasonName, seasonNumber, tvShowID) VALUES (NULL, \"The Beginning\", 1, 1);";
        stmt.executeUpdate(command);
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Season;";
        ResultSet retrievalSet = stmt.executeQuery(command);
        while(retrievalSet.next()) {
            output += "\n Object ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t Season Name: " + String.valueOf(retrievalSet.getInt("seasonName"));
            output += "\n\t Season Number: " + String.valueOf(retrievalSet.getInt("seasonNumber"));
            output += "\n\t TV Show ID: " + retrievalSet.getString("tvShowID");

        }
        System.out.println("Selected Season tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE Season SET seasonName=\"True Beginning\" WHERE TVShowID=1 AND seasonNumber=1;";
        stmt.executeUpdate(command);

        //Deletion
        command = "DELETE FROM Season WHERE tvShowID=1 AND seasonNumber=1;";
        stmt.executeUpdate(command);
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

    //RS
    private static void PersonQuery(PreparedStatement stmt) throws Exception {
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Person (id, firstName, lastName, sex) VALUES (NULL, \"John\", \"Madden\", \"Female\");";
        stmt.executeUpdate(command);
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Person;";
        ResultSet retrievalSet = stmt.executeQuery(command);
        while(retrievalSet.next()) {
            output += "\n Object ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t First Name: " + String.valueOf(retrievalSet.getInt("firstName"));
            output += "\n\t Last Name: " + String.valueOf(retrievalSet.getInt("lastName"));
            output += "\n\t Sex: " + retrievalSet.getString("sex");
        }
        System.out.println("Selected Person tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE person SET firstName=\"Bobby\" WHERE id=1 AND firstName= \"John\";";
        stmt.executeUpdate(command);

        //Deletion
        command = "DELETE FROM Person WHERE id=1 AND firstName=\"John\";";
        stmt.executeUpdate(command);
    }

    private static void ACTS_IN_EPQuery(PreparedStatement stmt) throws Exception {
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO ACTS_IN_EP (EpisodeID, PersonID) VALUES (1,1);";
        stmt.executeUpdate(command);
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Person;";
        ResultSet retrievalSet = stmt.executeQuery(command);
        while(retrievalSet.next()) {
            output += "\n Episode ID: " + String.valueOf(retrievalSet.getInt("EpisodeID")) + ": ";
            output += "\n\t Person ID " + String.valueOf(retrievalSet.getInt("PersonID"));
        }
        System.out.println("Selected ACTS_IN_EP tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE ACTS_IN_EP SET EpisodeID=2 WHERE EpisodeID=1 AND PersonID=1;";
        stmt.executeUpdate(command);

        //Deletion
        command = "DELETE FROM ACTS_IN_EP WHERE EpisodeID=2 AND PersonID=1;";
        stmt.executeUpdate(command);
    }

    
}
