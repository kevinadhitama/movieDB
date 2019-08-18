package com.example.moviedb.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */

@Database(entities = {Liked.class}, version = 1)
public abstract class LikedDB extends RoomDatabase {

    public abstract LikedDao likedDao();

    private static volatile LikedDB INSTANCE;

    public static LikedDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LikedDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LikedDB.class, "liked_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
