package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteArticleDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FavoriteArticles.db";
    private static final int DATABASE_VERSION = 1;

    public FavoriteArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FavoriteArticleContract.ArticleEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FavoriteArticleContract.ArticleEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
}
}