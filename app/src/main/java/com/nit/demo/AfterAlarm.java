package com.nit.demo;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.khizar1556.mkvideoplayer.MKPlayerActivity;

public class AfterAlarm extends AppCompatActivity {

//    private VideoView mVideoView;
    private Button playonline;

    private int REQUEST_CODE = 11;
    private MediaController controller = null;

    DisplayMetrics dm;
    private int height;
    private int width;


    private String videourl = "https://firebasestorage.googleapis.com/v0/b/fir-e229a.appspot.com/o/videoplayback.mp4?alt=media&token=c0a4e295-3469-4b7e-94ad-82a210a62ad9";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_alarm);

//        mVideoView = findViewById(R.id.videoView1);

        Uri uri1 = Uri.parse(videourl);
        MKPlayerActivity.configPlayer(this).play(videourl);
//        playVideo(uri1);

//        playonline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
////                startActivity(intent);
//                Uri uri1 = Uri.parse(videourl);
//                playVideo(uri1);
//            }
//        });

    }

//    private void playVideo(Uri uri)
//    {
//
//        controller = new MediaController(this);
//        controller.setAnchorView(mVideoView);
//        mVideoView.setMediaController(controller);
//        mVideoView.setVideoURI(uri);
//
//
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp)
//            {
//                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                        mVideoView.setMediaController(controller);
//                        controller.setAnchorView(mVideoView);
//
//                    }
//                });
//            }
//        });
//
//        mVideoView.start();
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE)
//        {
//            if(resultCode == RESULT_OK)
//            {
//                if(data!= null)
//                {
//                    Uri uri = data.getData();
//                    playVideo(uri);
//                }
//            }
//        }
//    }

}

