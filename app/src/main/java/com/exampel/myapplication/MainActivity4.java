package com.exampel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class MainActivity4 extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer player;
    String vidooURL="https://cdn.videvo.net/videvo_files/video/free/2019-02/small_watermarked/181015_12a_Venice%20Beach_011_preview.webm";
    private int CurrentWindow =0;
    private boolean PlayWhenReady =true;
    private long pp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        playerView= findViewById(R.id.iu4);

    }
    public void initVideo(){
        player= ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri=Uri.parse("https://cdn.videvo.net/videvo_files/video/free/2019-09/small_watermarked/190828_27_SuperTrees_HD_16_preview.webm");
        DataSource.Factory DataSource= new DefaultDataSourceFactory(this,"exoplayer-codelab");
        MediaSource mediaSource=new ProgressiveMediaSource.Factory(DataSource).createMediaSource(uri);
        player.setPlayWhenReady(PlayWhenReady);
        player.seekTo(CurrentWindow,pp);
        player.prepare(mediaSource,false,false);
    }
    public void releseVideo(){
        if (player!=null){
            PlayWhenReady=player.getPlayWhenReady();
            pp=player.getContentPosition();
            CurrentWindow=player.getCurrentWindowIndex();
            player.release();
            player=null;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        initVideo();


    }
    @Override
    protected void onStop() {
        super.onStop();
        releseVideo();}
    @Override
    protected void onPause() {
        super.onPause();
        releseVideo();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initVideo();

    }
}