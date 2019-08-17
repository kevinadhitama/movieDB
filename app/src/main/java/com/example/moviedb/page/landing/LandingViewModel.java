package com.example.moviedb.page.landing;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.datamodel.MovieItem;
import com.example.moviedb.provider.MovieProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevin.adhitama on 2019-08-14.
 */
public class LandingViewModel extends ViewModel {
    private CompositeDisposable mCompositeDisposable;
    private Disposable mDisposable;
    private MovieProvider mMovieProvider;
    private String currentFilter;
    MutableLiveData<List<MovieItem>> movieList;
    MutableLiveData<Boolean> loadingPage;
    Boolean loadComplete = true;
    Integer page = 1;

    LandingViewModel onCreateViewModel(MovieProvider movieProvider) {
        mCompositeDisposable = new CompositeDisposable();
        mMovieProvider = movieProvider;
        movieList = new MutableLiveData<>();
        loadingPage = new MutableLiveData<>();
        return this;
    }

    void fetchData() {
        fetchData(currentFilter);
    }

    void fetchData(String filter) {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        currentFilter = filter;
        mDisposable = mMovieProvider.getMovieList(LandingDataBridge.createMovieListRequest(filter, page.toString()))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(d -> {
                    if (page == 1)
                        loadingPage.setValue(true);
                    loadComplete = false;
                })
                .map(res -> {
                    LandingDataBridge.injectPosterUrl(res, mMovieProvider.getPosterUrl());
                    return res;
                })
                .subscribe(res -> {
                            movieList.setValue(res.getResults());
                            page = res.getPage() + 1;
                            loadingPage.setValue(false);
                            loadComplete = true;
                        },
                        err -> {

                        });

        mCompositeDisposable.add(mDisposable);
    }

}
