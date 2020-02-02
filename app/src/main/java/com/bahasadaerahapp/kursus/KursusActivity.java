package com.bahasadaerahapp.kursus;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bahasadaerahapp.R;

import java.io.IOException;

public class KursusActivity extends AppCompatActivity {

    private static final String FILE_URL = "http://www.w3schools.com/html/mov_bbb.mp4";
    private MediaController mediaControls;
    private VideoView simpleVideoView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursus);

        simpleVideoView =  findViewById(R.id.simpleVideoView); // initiate a video view

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(KursusActivity.this);
            mediaControls.setAnchorView(simpleVideoView);
        }
        // set the media controller for video view
        simpleVideoView.setMediaController(mediaControls);
        // set the uri for the video view
        Uri uri = Uri.parse(FILE_URL);
        simpleVideoView.setVideoURI(uri);
        // start a video
        simpleVideoView.start();

        // implement on completion listener on video view
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        simpleVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }
}
