package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoriteArticlesAdapter adapter;
    private FavoritesManager favoritesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesManager = new FavoritesManager(this);
        List<NewsItem> favoriteArticles = favoritesManager.getFavorites();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new FavoriteArticlesAdapter(this, favoriteArticles);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FavoriteArticlesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem item) {

                Intent intent = new Intent(FavoritesActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("date", item.getDate());
                intent.putExtra("link", item.getLink());
                startActivity(intent);
            }
   });
}
}