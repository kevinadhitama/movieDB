package com.example.moviedb.core.provider;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
public class ApiRoute {
    private final String movieApiKey = "121e42808bb64946ef1778f3c71e3771";

    private final String apiUrl = "https://api.themoviedb.org/3";
    private final String posterUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private final String movieListApiRoute = apiUrl + "/discover/movie";


    public String getMovieApiKey() {
        return movieApiKey;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getMovieListApiRoute() {
        return movieListApiRoute;
    }
}
