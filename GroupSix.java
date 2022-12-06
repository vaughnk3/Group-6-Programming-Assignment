//Group 6
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
        Properties info = new Properties();
        info.put("user", "my.vaughnk3");
        info.put("password", "!$w9oh71");
        info.put("database", "my_vaughnk3_final_group6");
        info.put("encrypt", true);
        info.put("trustServerCertificate", false);
        info.put("loginTimeout", 30);
        
        Connection connection = null;
        try { 
            //Establish the connection
            connection = DriverManager.getConnection(connUrl, info);

            //Replace the comments with your own methods
            TVShowQuery(connection);
            SeasonQuery(connection);
            EpisodeQuery(connection);
            PersonQuery(connection);
            ACTS_IN_EPQuery(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                //Always close the connection.
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //TV Show Query (KJ)
    private static void TVShowQuery(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO TVShow (id, name, yearReleased, description) VALUES (NULL, \"The Office\", 2004, \"The Office with Mitchel Scotch and Jimmy and Dwigt Schrute\");";
        stmt = conn.prepareStatement(command);
        stmt.executeUpdate(command);
        stmt.close();

        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM TVShow;";
        stmt = conn.prepareStatement(command);
        ResultSet retrievalSet = stmt.executeQuery(command);
        stmt.close();
        while(retrievalSet.next()) {
            output += "\n TV Show ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t TV Show Name: " + (retrievalSet.getString("name"));
            output += "\n\t Year Released: " + String.valueOf(retrievalSet.getInt("yearReleased"));
            output += "\n\t Description: " + retrievalSet.getString("description");
        }
        System.out.println("Selected TV Show tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE TVShow SET description=? WHERE name=?;";
        stmt = conn.prepareStatement(command);
        stmt.setString(1, "The Office with Michael Scott and Jimmy and Dwight Schrute. New!");
        stmt.setString(2, "The Office");
        stmt.executeUpdate(command);
        stmt.close();

        //Deletion
        command = "DELETE FROM TVShow WHERE name=?;";
        stmt = conn.prepareStatement(command);
        stmt.setString(1, "The Office");
        stmt.executeUpdate(command);
        stmt.close();
    }
    
    
    //Season Query (Gavin)
    private static void SeasonQuery(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Season (id, seasonName, seasonNumber, tvShowID) VALUES (NULL, \"The Beginning\", 1, 1);";
        stmt = conn.prepareStatement(command);
        stmt.executeUpdate(command);
        stmt.close();
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Season;";
        stmt = conn.prepareStatement(command);
        ResultSet retrievalSet = stmt.executeQuery(command);
        stmt.close();
        while(retrievalSet.next()) {
            output += "\n Object ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t Season Name: " + String.valueOf(retrievalSet.getInt("seasonName"));
            output += "\n\t Season Number: " + String.valueOf(retrievalSet.getInt("seasonNumber"));
            output += "\n\t TV Show ID: " + retrievalSet.getString("tvShowID");

        }
        System.out.println("Selected Season tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE Season SET seasonName=? WHERE TVShowID=? AND seasonNumber=?;";
        stmt = conn.prepareStatement(command);
        stmt.setString(1, "True Beginning");
        stmt.setInt(2, 1);
        stmt.setInt(3, 1);
        stmt.executeUpdate(command);
        stmt.close();

        //Deletion
        command = "DELETE FROM Season WHERE tvShowID=? AND seasonNumber=?;";
        stmt = conn.prepareStatement(command);
        stmt.setInt(1, 1);
        stmt.setInt(2, 1);
        stmt.executeUpdate(command);
        stmt.close();
    }

    //Episode Query (Jared)
    private static void EpisodeQuery(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Episode(id, seasonID, episodeNumber, episodeName, description, runtime) VALUES (NULL, 1, 1, \"The Office Field Guide\", \"When a documentary crew arrives at the office, manager Michael Scott attempts to paint a rosy picture but fails after learning the company will be downsizing.\" 23);";
        stmt = conn.prepareStatement(command);
        stmt.executeUpdate(command);
        stmt.close();
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Episode;";
        stmt = conn.prepareStatement(command);
        ResultSet retrievalSet = stmt.executeQuery(command);
        stmt.close();
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
        command = "UPDATE Episode SET episodeName=? WHERE seasonID=? AND episodeNumber=?;";
        stmt = conn.prepareStatement(command);
        stmt.setString(1, "The Office Pilot Ep. 1: The Office Field Guide");
        stmt.setInt(2, 1);
        stmt.setInt(3, 1);
        stmt.executeUpdate(command);
        stmt.close();

        //Deletion
        command = "DELETE FROM Episode WHERE seasonID=? AND episodeNumber=?;";
        stmt = conn.prepareStatement(command);
        stmt.setInt(1, 1);
        stmt.setInt(2, 1);
        stmt.executeUpdate(command);
        stmt.close();
    }

    //Person Query & ACTS_IN_EP Query (Robert)
    private static void PersonQuery(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO Person (id, firstName, lastName, sex) VALUES (NULL, \"John\", \"Madden\", \"Female\");";
        stmt = conn.prepareStatement(command);
        stmt.executeUpdate(command);
        stmt.close();
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Person;";
        stmt = conn.prepareStatement(command);
        ResultSet retrievalSet = stmt.executeQuery(command);
        stmt.close();
        while(retrievalSet.next()) {
            output += "\n Object ID " + String.valueOf(retrievalSet.getInt("id")) + ": ";
            output += "\n\t First Name: " + String.valueOf(retrievalSet.getInt("firstName"));
            output += "\n\t Last Name: " + String.valueOf(retrievalSet.getInt("lastName"));
            output += "\n\t Sex: " + retrievalSet.getString("sex");
        }
        System.out.println("Selected Person tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE person SET firstName=? WHERE id=? AND firstName=?;";
        stmt = conn.prepareStatement(command);
        stmt.setString(1, "Bobby");
        stmt.setInt(2, 1);
        stmt.setString(3, "John");
        stmt.executeUpdate(command);
        stmt.close();

        //Deletion
        command = "DELETE FROM Person WHERE id=? AND firstName=?;";
        stmt = conn.prepareStatement(command);
        stmt.setInt(1, 1);
        stmt.setString(2, "John");
        stmt.close();
    }

    private static void ACTS_IN_EPQuery(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        String command = "";
        String output = "";

        //Creation
        command = "INSERT INTO ACTS_IN_EP (EpisodeID, PersonID) VALUES (1,1);";
        stmt = conn.prepareStatement(command);
        stmt.executeUpdate(command);
        stmt.close();
        //Retrieval (must display the results using the ResultingSet object)
        command = "SELECT * FROM Person;";
        stmt = conn.prepareStatement(command);
        ResultSet retrievalSet = stmt.executeQuery(command);
        stmt.close();
        while(retrievalSet.next()) {
            output += "\n Episode ID: " + String.valueOf(retrievalSet.getInt("EpisodeID")) + ": ";
            output += "\n\t Person ID " + String.valueOf(retrievalSet.getInt("PersonID"));
        }
        System.out.println("Selected ACTS_IN_EP tuples: " + output);
        retrievalSet.close();

        //Update
        command = "UPDATE ACTS_IN_EP SET EpisodeID=? WHERE EpisodeID=? AND PersonID=?;";
        stmt = conn.prepareStatement(command);
        stmt.setInt(1, 2);
        stmt.setInt(2, 1);
        stmt.setInt(3, 1);
        stmt.executeUpdate(command);
        stmt.close();

        //Deletion
        command = "DELETE FROM ACTS_IN_EP WHERE EpisodeID=? AND PersonID=?;";
        stmt = conn.prepareStatement(command);
        stmt.setInt(1, 2);
        stmt.setInt(2, 1);
        stmt.executeUpdate(command);
        stmt.close();
    }

    
}
