package com.example.moviedb.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
@Dao
public interface LikedDao {

    @Query("Select * from Liked where id = :id")
    public LiveData<Liked> getLiked(int id);

    @Delete
    public void deleteLiked(Liked liked);

    @Insert(onConflict = REPLACE)
    public void insertLiked(Liked liked);
}
