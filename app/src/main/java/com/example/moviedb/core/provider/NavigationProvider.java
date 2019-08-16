package com.example.moviedb.core.provider;

import android.content.Context;

import com.example.moviedb.datamodel.MovieItem;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
public interface NavigationProvider {
    void navigateToMovieDetail(Context context, MovieItem movieItem);
}
