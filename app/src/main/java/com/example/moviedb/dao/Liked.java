package com.example.moviedb.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by kevin.adhitama on 2019-08-16.
 */
@Entity(tableName = "Liked")
public class Liked {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    public Liked(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}