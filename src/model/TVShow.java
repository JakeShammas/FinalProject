package model;

import java.io.Serializable;
import java.sql.Date;

public class TVShow implements Serializable {
    public String title;
    public String genre;
    public int totalSeasons;
    public int totalEpisodes;
    public int watchedEpisodes;
    public Date releaseDate;

    public TVShow(String title, String genre, int totalSeasons, int totalEpisodes, Date releaseDate) {
        this.title = title;
        this.genre = genre;
        this.totalSeasons = totalSeasons;
        this.totalEpisodes = totalEpisodes;
        this.watchedEpisodes = 0;
        this.releaseDate = releaseDate;
    }

    public void markEpisodeWatched() {
        if (watchedEpisodes < totalEpisodes) {
            watchedEpisodes++;
        }
    }

    public int getRemainingEpisodes() {
        return totalEpisodes - watchedEpisodes;
    }

    public int getProgress() {
        return (int) (((double) watchedEpisodes / totalEpisodes) * 100);
    }

    public String getDisplayInfo() {
        return title + " (" + genre + ") - " + watchedEpisodes + "/" + totalEpisodes + " watched. Remaining: " + getRemainingEpisodes();
    }
}