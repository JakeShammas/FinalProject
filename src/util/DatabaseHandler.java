package util;

import model.TVShow;
import model.User;

import java.io.*;
import java.util.List;

public class DatabaseHandler {

    private static final String FILE_NAME = "tvshows.dat";

    // Save the list of tracked shows and user info to a file
    public static void saveData(User user) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(user);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Load the saved data (User and their tracked TV shows)
    public static User loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (User) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new User("Guest", "guest@example.com"); // Return default user if no data is found
    }
}
