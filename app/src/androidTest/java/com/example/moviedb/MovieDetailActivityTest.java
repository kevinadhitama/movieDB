package com.example.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.moviedb.constants.PublicConstants;
import com.example.moviedb.datamodel.MovieItem;
import com.example.moviedb.page.movie_detail.MovieDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by kevin.adhitama on 2019-08-18.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDetailActivityTest {

    @Rule
    public ActivityTestRule<MovieDetailActivity> rule = new ActivityTestRule<MovieDetailActivity>(MovieDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            ApplicationProvider.getApplicationContext();

            movieItem = new MovieItem(1807
                    , 420818
                    , false
                    , 7.2f
                    , "The Lion King"
                    , 317.552d
                    , "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
                    , "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg"
                    , "en"
                    , "The Lion King",
                    new ArrayList<>(),
                    "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg"
                    , false
                    , "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his."
                    , "2019-07-12");

            Bundle bundle = new Bundle();
            bundle.putParcelable(PublicConstants.EXTRAS_MOVIE_ITEM, Parcels.wrap(movieItem));

            Intent intent = new Intent();
            intent.putExtras(bundle);
            return intent;
        }
    };

    private MovieItem movieItem;

    @Test
    public void ensureIntentDataIsDisplayed() throws Exception {
        MovieDetailActivity activity = rule.getActivity();

        TextView textViewTitle = activity.findViewById(R.id.textViewTitle);
        TextView textViewReleaseDate = activity.findViewById(R.id.textViewReleaseDate);
        TextView textViewRating = activity.findViewById(R.id.textViewRating);
        RatingBar ratingBar = activity.findViewById(R.id.ratingBar);
        TextView textViewOverview = activity.findViewById(R.id.textViewOverview);

        assertThat(textViewTitle.getText().toString(), is(movieItem.getOriginalTitle()));
        assertThat(textViewReleaseDate.getText().toString(), is("Release date : " + movieItem.getReleaseDate()));
        assertThat(textViewRating.getText().toString(), is(movieItem.getVoteAverageDisplay() + "/10"));
        assertThat(ratingBar.getRating(), is(movieItem.getVoteAverage()));
        assertThat(textViewOverview.getText().toString(), is(movieItem.getOverview()));
    }
}
