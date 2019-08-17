package com.example.moviedb.core.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.moviedb.constants.PublicConstants;
import com.example.moviedb.datamodel.MovieItem;
import com.example.moviedb.page.movie_detail.MovieDetailActivity;

import org.parceler.Parcels;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
public class NavigationProviderImpl implements NavigationProvider {

    @Override
    public void navigateToMovieDetail(Context context, MovieItem item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PublicConstants.EXTRAS_MOVIE_ITEM, Parcels.wrap(item));

        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
