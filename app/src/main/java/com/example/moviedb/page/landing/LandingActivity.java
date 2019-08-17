package com.example.moviedb.page.landing;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;
import com.example.moviedb.constants.PublicConstants;
import com.example.moviedb.core.component.DaggerApplicationComponent;
import com.example.moviedb.core.provider.NavigationProvider;
import com.example.moviedb.databinding.ActivityLandingBinding;
import com.example.moviedb.page.landing.adapter.MoviePosterAdapter;
import com.example.moviedb.provider.MovieProvider;

import javax.inject.Inject;

public class LandingActivity extends AppCompatActivity {

    ActivityLandingBinding mBinding;
    LandingViewModel mViewModel;
    MoviePosterAdapter mMoviePosterAdapter;

    @Inject
    MovieProvider mMovieProvider;

    @Inject
    NavigationProvider mNavigationProvider;

    GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerApplicationComponent.builder().build().inject(this);

        mViewModel = ViewModelProviders.of(this)
                .get(LandingViewModel.class)
                .onCreateViewModel(mMovieProvider);

        if (getSupportActionBar() != null)
            getSupportActionBar().setSubtitle(R.string.app_subtitle);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        initRecyclerView();
        initDataBinding();
        initFilterButton();
    }

    private void initRecyclerView() {
        mMoviePosterAdapter = new MoviePosterAdapter(this, movieItem -> {
            mNavigationProvider.navigateToMovieDetail(this, movieItem);
        });

        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mMoviePosterAdapter.getItemViewType(position)) {
                    case MoviePosterAdapter.VIEW_TYPE_ITEM:
                        return 1;
                    case MoviePosterAdapter.VIEW_TYPE_LOADING:
                        return 2;
                    default:
                        return -1;
                }
            }
        });

        mBinding.recyclerViewMovie.setLayoutManager(mLayoutManager);
        mBinding.recyclerViewMovie.setAdapter(mMoviePosterAdapter);
        mBinding.recyclerViewMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean lastPosition = mLayoutManager.findLastCompletelyVisibleItemPosition() == mMoviePosterAdapter.getItemCount() - 1;
                if (lastPosition && mViewModel.loadComplete) {
                    mViewModel.fetchData();
                }
            }
        });
    }

    private void initDataBinding() {
        mViewModel.movieList.observe(this, movieItems -> {
            if (mViewModel.page == 1) {
                mMoviePosterAdapter.setMovieList(movieItems);
                mBinding.recyclerViewMovie.scrollToPosition(0);
            } else
                mMoviePosterAdapter.addMovieList(movieItems);
        });

        mViewModel.loadingPage.observe(this, isLoading -> {
            if (isLoading)
                mBinding.containerLoading.setVisibility(View.VISIBLE);
            else
                mBinding.containerLoading.setVisibility(View.GONE);
        });
    }

    private void initFilterButton() {
        mBinding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            mViewModel.page = 1;
            if (checkedId == R.id.buttonFilterPopular) {
                mViewModel.fetchData(PublicConstants.FILTER_MOST_POPULAR);
            } else if (checkedId == R.id.buttonFilterRating) {
                mViewModel.fetchData(PublicConstants.FILTER_HIGHEST_RATING);
            }
        });
        mBinding.buttonFilterPopular.setChecked(true);
    }
}
