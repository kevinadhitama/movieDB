package com.example.moviedb.page.movie_detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.constants.PublicConstants;
import com.example.moviedb.dao.LikedDB;
import com.example.moviedb.dao.LikedDao;
import com.example.moviedb.databinding.ActivityMovieDetailBinding;
import com.example.moviedb.datamodel.MovieItem;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding mBinding;
    MovieDetailViewModel mViewModel;
    LikedDao likedDao;

    MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieItem = Parcels.unwrap(getIntent().getParcelableExtra(PublicConstants.EXTRAS_MOVIE_ITEM));
        likedDao = LikedDB.getDatabase(getApplicationContext()).likedDao();

        mViewModel = ViewModelProviders.of(this)
                .get(MovieDetailViewModel.class)
                .onCreateViewModel(movieItem, likedDao);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }

        likedDao.getLiked(movieItem.getId()).observe(this, liked -> {
            mViewModel.setLiked(liked);

            showFabLike(mViewModel.isLiked());
            mBinding.fab.show();
        });

        initFab();
        initView();

    }

    private void showFabLike(boolean liked) {
        if (mBinding.fab.isOrWillBeHidden()) {
            mBinding.fab.show();
        }

        if (liked) {
            mBinding.fab.setImageResource(R.drawable.ic_thumb_up_liked);
        } else {
            mBinding.fab.setImageResource(R.drawable.ic_thumb_up);
        }
    }

    private void initFab() {
        mBinding.fab.hide();
        mBinding.fab.setOnClickListener(view -> {
            mViewModel.onClickLikeButton();

            int msg = mViewModel.isLiked() ? R.string.message_unlike : R.string.message_like;
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        });
    }

    private void initView() {
        if (mViewModel.getMovieItem().getValue() == null)
            return;

        String posterPath = mViewModel.getMovieItem().getValue().getFullPosterPath();
        if (posterPath != null && !posterPath.isEmpty()) {
            Glide.with(this)
                    .load(posterPath)
                    .transition(withCrossFade())
                    .into(mBinding.imageViewBanner);
        } else {
            Glide.with(this)
                    .load(R.drawable.background_no_image_placeholder)
                    .transition(withCrossFade())
                    .into(mBinding.imageViewBanner);
        }

        String backdropPath = mViewModel.getMovieItem().getValue().getBackdropPath();
        if (backdropPath != null && !backdropPath.isEmpty()) {
            Glide.with(this)
                    .load(mViewModel.getMovieItem().getValue().getFullBackdropPath())
                    .transition(withCrossFade())
                    .into(mBinding.imageViewBackground);
        } else if (posterPath != null && !posterPath.isEmpty()) {
            Glide.with(this)
                    .load(mViewModel.getMovieItem().getValue().getFullBackdropPath())
                    .transition(withCrossFade())
                    .into(mBinding.imageViewBackground);
        } else {
            mBinding.imageViewBackground.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
