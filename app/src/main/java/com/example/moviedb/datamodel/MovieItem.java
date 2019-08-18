package com.example.moviedb.datamodel;

import org.parceler.Parcel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by kevin.adhitama on 2019-08-14.
 */
@Parcel
public class MovieItem {
    Integer vote_count;
    Integer id;
    Boolean video;
    Float vote_average;
    String title;
    Double popularity;
    String poster_path_prefix;
    String poster_path;
    String original_language;
    String original_title;
    List<Integer> genre_ids;
    String backdrop_path;
    Boolean adult;
    String overview;
    String release_date;

    public void setPosterPathPrefix(String poster_path_prefix) {
        this.poster_path_prefix = poster_path_prefix;
    }

    public Integer getVoteCount() {
        return vote_count;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getVideo() {
        return video;
    }

    public Float getVoteAverage() {
        return vote_average;
    }

    public String getVoteAverageDisplay() {
        DecimalFormat format = new DecimalFormat("0.#");
        return vote_average == null ? "" : format.format(vote_average);
    }

    public String getTitle() {
        return title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getFullPosterPath() {
        return poster_path == null ? "" : poster_path_prefix + poster_path;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getFullBackdropPath() {
        return backdrop_path == null ? "" : poster_path_prefix + backdrop_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return release_date;
    }
}

