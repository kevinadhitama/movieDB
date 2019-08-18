package com.example.moviedb.datamodel.landing;

import com.example.moviedb.datamodel.MovieItem;

import java.util.List;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
public class MovieResponse {
    private Integer page;
    private Integer total_results;
    private Integer total_pages;
    private List<MovieItem> results;

    public Integer getPage() {
        return page;
    }

    public Integer getTotalResults() {
        return total_results;
    }

    public Integer getTotalPages() {
        return total_pages;
    }

    public List<MovieItem> getResults() {
        return results;
    }
}
