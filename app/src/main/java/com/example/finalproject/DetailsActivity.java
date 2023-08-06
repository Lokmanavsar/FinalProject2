package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class DetailsActivity extends AppCompatActivity {

    private NewsItem newsItem;
    private FavoritesManager favoritesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        WebView webView = findViewById(R.id.webView);


        webView.getSettings().setJavaScriptEnabled(true);

        NewsItem newsItem = getIntent().getParcelableExtra("newsItem");

        if (newsItem != null) {

            String link = newsItem.getLink();
            webView.loadUrl(link);


            webView.setWebViewClient(new WebViewClient());
        }
        favoritesManager = new FavoritesManager(this);


        String link = getIntent().getStringExtra("link");
        webView.loadUrl(link);


        webView.setWebViewClient(new WebViewClient());


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");



        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        TextView textViewDate = findViewById(R.id.textViewDate);
        TextView textViewLink = findViewById(R.id.textViewLink);

        textViewTitle.setText(title);
        textViewDescription.setText(description);
        textViewDate.setText(date);
        textViewLink.setText(link);



        addToFavoritesList(newsItem);


        Toast.makeText(this, "Article added to favorites", Toast.LENGTH_SHORT).show();
    }

    public void addToFavorites(View view) {
        favoritesManager.addFavorite(newsItem);
        showSnackbar("Article added to favorites");
    }

    public void removeFromFavorites(View view) {
        favoritesManager.removeFavorite(newsItem);
        showSnackbar("Article removed from favorites");
    }

    private void showSnackbar(String message) {

        Snackbar.make(findViewById(R.id.container), message, Snackbar.LENGTH_SHORT).show();
    }


    private void addToFavoritesList(NewsItem newsItem) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> favoritesSet = preferences.getStringSet("favorites", new HashSet<>());


        Gson gson = new Gson();
        String newsItemJson = gson.toJson(newsItem);


        favoritesSet.add(newsItemJson);


        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("favorites", favoritesSet);
        editor.apply();
}
}