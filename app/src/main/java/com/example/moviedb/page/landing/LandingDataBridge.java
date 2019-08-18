package com.example.moviedb.page.landing;

import com.example.moviedb.datamodel.MovieItem;
import com.example.moviedb.datamodel.landing.MovieResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
public class LandingDataBridge {

    public static Map<String, String> createMovieListRequest(String filter, String page) {
        Map<String, String> params = new HashMap<>();
        params.put("sort_by", filter);
        params.put("page", page);
        return params;
    }

    public static void injectPosterUrl(MovieResponse movieResponse, String posterUrlPrefix) {
        for (MovieItem item : movieResponse.getResults()) {
            if (item.getPosterPath() != null && !item.getPosterPath().isEmpty()) {
                item.setPosterPathPrefix(posterUrlPrefix);
            }
        }
    }

}
