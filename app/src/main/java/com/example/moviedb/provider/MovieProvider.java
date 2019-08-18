package com.example.moviedb.provider;

import com.example.moviedb.core.provider.ApiProvider;
import com.example.moviedb.core.provider.ApiRoute;
import com.example.moviedb.datamodel.landing.MovieResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by kevin.adhitama on 2019-08-15.
 */
public class MovieProvider {

    private ApiRoute mApiRoute;
    private ApiProvider mApiProvider;

    @Inject
    public MovieProvider(ApiProvider apiProvider, ApiRoute apiRoute) {
        mApiProvider = apiProvider;
        mApiRoute = apiRoute;
    }

    public Observable<MovieResponse> getMovieList(Map<String, String> params) {
        params.put("api_key", mApiRoute.getMovieApiKey());
        return mApiProvider.get(mApiRoute.getMovieListApiRoute(), params, MovieResponse.class);
    }

    public String getPosterUrl() {
        return mApiRoute.getPosterUrl();
    }
}
