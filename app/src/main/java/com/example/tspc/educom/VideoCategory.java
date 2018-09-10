package com.example.tspc.educom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VideoCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_category);
    }

    public void android(View view) {
        Intent intent = new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.android_cat));
        startActivity(intent);

    }

    public void algorithm(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.algorithm_cat));
        startActivity(intent);
    }

    public void DataBase(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.database_cat));
        startActivity(intent);
    }

    public void ArtiIntel(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.ai_cat));
        startActivity(intent);
    }

    public void Html(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.html_cat));
        startActivity(intent);
    }

    public void Css(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.css_cat));
        startActivity(intent);
    }

    public void WrdPrs(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.wordprs_cat));
        startActivity(intent);
    }

    public void JvSrpt(View view) {
        Intent intent= new Intent(VideoCategory.this,AllItemLIst.class);
        intent.putExtra("playlist_id",getResources().getString(R.string.java_cat));
        startActivity(intent);
    }
}
