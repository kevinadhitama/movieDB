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

    public MovieResponse() {
    }

    public MovieResponse(Integer page, Integer total_results, Integer total_pages, List<MovieItem> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

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
