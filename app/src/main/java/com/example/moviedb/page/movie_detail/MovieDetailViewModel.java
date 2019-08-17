package com.example.moviedb.page.movie_detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviedb.dao.Liked;
import com.example.moviedb.dao.LikedDao;
import com.example.moviedb.datamodel.MovieItem;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
public class MovieDetailViewModel extends ViewModel {
    Disposable disposable;
    Liked liked;
    LikedDao likedDao;

    MutableLiveData<MovieItem> movieItem;

    public MovieDetailViewModel() {
        movieItem = new MutableLiveData<>();
    }

    MovieDetailViewModel onCreateViewModel(MovieItem movieItem, LikedDao likedDao) {
        this.likedDao = likedDao;
        this.movieItem.setValue(movieItem);
        return this;
    }

    public MutableLiveData<MovieItem> getMovieItem() {
        return movieItem;
    }

    public boolean isLiked() {
        return liked != null;
    }

    public Liked getLiked() {
        return liked;
    }

    public void setLiked(Liked liked) {
        this.liked = liked;
    }

    void onClickLikeButton() {
        if (disposable != null && !disposable.isDisposed())
            return;

        disposable = Observable.fromCallable(() -> {
            if (movieItem.getValue() == null)
                return null;

            if (isLiked())
                likedDao.deleteLiked(liked);
            else {
                liked = new Liked(movieItem.getValue().getId());
                likedDao.insertLiked(liked);
            }
            return true;
        }).subscribeOn(Schedulers.io()).subscribe(res -> {
        }, err -> {
        });
    }
}
