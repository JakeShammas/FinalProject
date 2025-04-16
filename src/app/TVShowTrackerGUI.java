package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.TVShow;
import model.User;
import util.RecommendationEngine;
import util.DatabaseHandler;

import java.util.List;

public class TVShowTrackerGUI extends Application {
    private User currentUser;

    private ListView<String> showListView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Load the saved user data
        currentUser = DatabaseHandler.loadData();

        primaryStage.setTitle("TV Show Tracker");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        TextField titleField = new TextField();
        titleField.setPromptText("Show Title");
        TextField genreField = new TextField();
        genreField.setPromptText("Genre");
        TextField seasonsField = new TextField();
        seasonsField.setPromptText("Seasons");
        TextField episodesField = new TextField();
        episodesField.setPromptText("Total Episodes");
        Button addShowBtn = new Button("Add Show");

        addShowBtn.setOnAction(e -> {
            String title = titleField.getText();
            String genre = genreField.getText();
            int seasons = Integer.parseInt(seasonsField.getText());
            int episodes = Integer.parseInt(episodesField.getText());
            TVShow show = new TVShow(title, genre, seasons, episodes, null);
            currentUser.addShow(show);
            refreshShowList();
            DatabaseHandler.saveData(currentUser); // Save data after adding a show
        });

        Button watchOneMoreBtn = new Button("Mark 1 Episode Watched");
        watchOneMoreBtn.setOnAction(e -> {
            String selected = showListView.getSelectionModel().getSelectedItem();
            for (TVShow show : currentUser.trackedShows) {
                if (selected.contains(show.title)) {
                    show.markEpisodeWatched();
                    break;
                }
            }
            refreshShowList();
            DatabaseHandler.saveData(currentUser); // Save data after marking an episode as watched
        });

        TextField genreInputField = new TextField();
        genreInputField.setPromptText("Enter Genre for Recommendations");
        Button recommendBtn = new Button("Get Recommendations");
        ListView<String> recommendationList = new ListView<>();

        recommendBtn.setOnAction(e -> {
            String genre = genreInputField.getText();
            List<TVShow> recs = RecommendationEngine.getRecommendations(genre);
            recommendationList.getItems().clear();
            for (TVShow rec : recs) {
                recommendationList.getItems().add(rec.getDisplayInfo());
            }
        });

        root.getChildren().addAll(titleField, genreField, seasonsField, episodesField, addShowBtn,
                showListView, watchOneMoreBtn, genreInputField, recommendBtn, recommendationList);

        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }

    private void refreshShowList() {
        showListView.getItems().clear();
        for (TVShow s : currentUser.trackedShows) {
            showListView.getItems().add(s.getDisplayInfo());
        }
    }
}
