package util;

import model.TVShow;
import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {
    public static List<TVShow> getRecommendations(String genre) {
        List<TVShow> recommendations = new ArrayList<>();

        if (genre.equalsIgnoreCase("drama")) {
            recommendations.add(new TVShow("Breaking Bad", "Drama", 5, 62, null));
            recommendations.add(new TVShow("The Crown", "Drama", 6, 60, null));
            recommendations.add(new TVShow("Better Call Saul", "Drama", 6, 63, null));
        } else if (genre.equalsIgnoreCase("comedy")) {
            recommendations.add(new TVShow("Brooklyn Nine-Nine", "Comedy", 8, 153, null));
            recommendations.add(new TVShow("The Office", "Comedy", 9, 201, null));
            recommendations.add(new TVShow("Parks and Recreation", "Comedy", 7, 125, null));
        } else if (genre.equalsIgnoreCase("sci-fi")) {
            recommendations.add(new TVShow("Stranger Things", "Sci-Fi", 4, 34, null));
            recommendations.add(new TVShow("The Mandalorian", "Sci-Fi", 3, 24, null));
            recommendations.add(new TVShow("Westworld", "Sci-Fi", 4, 36, null));
        } else {
            recommendations.add(new TVShow("Sample Show 1", genre, 1, 10, null));
            recommendations.add(new TVShow("Sample Show 2", genre, 1, 12, null));
            recommendations.add(new TVShow("Sample Show 3", genre, 1, 8, null));
        }

        return recommendations;
    }
}
