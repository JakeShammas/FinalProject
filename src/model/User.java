package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public String username;
    public String email;
    public List<TVShow> trackedShows;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.trackedShows = new ArrayList<>();
    }

    public void addShow(TVShow show) {
        trackedShows.add(show);
    }

    public void removeShow(TVShow show) {
        trackedShows.remove(show);
    }

    public void updateProgress(String title, int watched) {
        for (TVShow show : trackedShows) {
            if (show.title.equalsIgnoreCase(title)) {
                show.watchedEpisodes = watched;
                break;
            }
        }
    }
}
