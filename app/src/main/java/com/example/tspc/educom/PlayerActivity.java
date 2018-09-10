package com.example.tspc.educom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    YouTubePlayer player;

    String video_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent= getIntent();
        video_id=intent.getStringExtra("video_id");

        playerView=findViewById(R.id.youTubePlayer);

        LoadPlayer(video_id);

    }

    private void LoadPlayer(final String video_id) {
        playerView.initialize(video_id, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (youTubePlayer!=null){
                    youTubePlayer.cueVideo(video_id);
                    youTubePlayer.setFullscreen(true);
                    youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                        @Override
                        public void onPlaying() {

                        }

                        @Override
                        public void onPaused() {

                        }

                        @Override
                        public void onStopped() {

                        }

                        @Override
                        public void onBuffering(boolean b) {

                        }

                        @Override
                        public void onSeekTo(int i) {

                        }
                    });
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


}
