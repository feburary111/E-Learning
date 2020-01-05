package com.example.app.BOTTOMUI;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

public class matmp3 extends AppCompatActivity {
    private VideoView videoView;
    private Button btn_start,btn_end;
    private MediaController mediaController;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matmp3ui);
        initView();
    }
    private void initView() {
        videoView= (VideoView) findViewById(R.id.videoView2);
        btn_start= (Button) findViewById(R.id.btn_start2);
        btn_end= (Button) findViewById(R.id.btn_end2);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                videoView.stopPlayback();
            }
        });
    }
    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView2);
        mediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" +R.raw.vtest;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}
