package firstapplication.codesroom.com.firstprojectfromcodesroom.activity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.VideoView;

import firstapplication.codesroom.com.firstprojectfromcodesroom.R;
import firstapplication.codesroom.com.firstprojectfromcodesroom.beans.MyItem;

public class DetailsActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textOwn;
    private TextView textDesc;
    private VideoView videoView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        MyItem container = (MyItem) getIntent().getSerializableExtra(MyItem.class.getSimpleName());

        if (container == null) {
            finish();
            return;
        }

        textName = (TextView) findViewById(R.id.textViewName);
        textOwn = (TextView) findViewById(R.id.textViewOwn);
        textDesc = (TextView) findViewById(R.id.textViewDescription);
        textName.setText(container.getName());
        textOwn.setText(container.getOwn());
        textDesc.setText(container.getDesc());
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                finish();
            }
        });
        playVideo(container.getUrl());
    }

    private void playVideo(String videoUrl) {
        try {
//            videoUrl = "http://codesroom-portal.surge.sh/CodesRoom.mp4";
            progressDialog = ProgressDialog.show(this, "", "Buffering video...", false);
            progressDialog.setCancelable(true);
            getWindow().setFormat(PixelFormat.TRANSLUCENT);

//            mediaController = new MediaController(this);

            Uri video = Uri.parse(videoUrl);
            videoView.setMediaController(new android.widget.MediaController(this));
            videoView.setVideoURI(video);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.getMessage());
        }
    }
}
