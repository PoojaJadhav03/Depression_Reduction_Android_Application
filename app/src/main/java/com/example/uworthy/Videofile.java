package com.example.uworthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Videofile extends AppCompatActivity {
    @BindView(R.id.recyclerViewFeed)
    RecyclerView recyclerViewFeed;

    VideoAdapter mRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videofile);
        ButterKnife.bind(this);
        // prepare data for list
        List<YoutubeVideo> youtubeVideos = prepareList();
        mRecyclerAdapter = new VideoAdapter(youtubeVideos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewFeed.setLayoutManager(mLayoutManager);
        recyclerViewFeed.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFeed.setAdapter(mRecyclerAdapter);
    }

    private List<YoutubeVideo> prepareList() {
        ArrayList mYoutubeVideo = new ArrayList();
        // add first item
        YoutubeVideo video1 = new YoutubeVideo();
        video1.setId(1l);
       // video1.setImageUrl("https://youtu.be/jBzB5xdeQ4o");
        //video1.setTitle("Thugs Of Hindostan - Official Trailer | Amitabh Bachchan | Aamir Khan | Katrina Kaif | Fatima");
        video1.setVideoId("jBzB5xdeQ4o");
        mYoutubeVideo.add(video1);

        // add second item
        YoutubeVideo video2 = new YoutubeVideo();
        video2.setId(2l);
       // video2.setImageUrl("https://youtu.be/3jCapiX3elQ");
      //  video2.setTitle("Colors for Children to Learning with Baby Fun Play with Color Balls Dolphin Slider Toy Set Kids Edu");
        video2.setVideoId("8ZK_S-46KwE");
        mYoutubeVideo.add(video2);

        // add third item
        YoutubeVideo video3 = new YoutubeVideo();
        video3.setId(3l);
      //  video3.setImageUrl("https://youtu.be/KidYk-E56rM");
       // video3.setTitle("Air Hostess Accepts Marriage Proposal Mid-Air, Airline Fires her.");
        video3.setVideoId("KidYk-E56rM");
        mYoutubeVideo.add(video3);

        // add four item
        YoutubeVideo video4 = new YoutubeVideo();
        video4.setId(4l);
       // video4.setImageUrl("https://youtu.be/1I9ADpXbD6c");
      //  video4.setTitle("EXPERIMENT Glowing 1000 degree METAL BALL vs Gunpowder (100 grams)");
        video4.setVideoId("1I9ADpXbD6c");
        mYoutubeVideo.add(video4);

        // add four item
        YoutubeVideo video5 = new YoutubeVideo();
        video5.setId(5l);
       // video5.setImageUrl("https://youtu.be/tBGvOmUhhq4");
       // video5.setTitle("What happened after Jauhar of Padmavati");
        video5.setVideoId("tBGvOmUhhq4");
        mYoutubeVideo.add(video5);


        YoutubeVideo video6 = new YoutubeVideo();
        video6.setId(6l);
        // video5.setImageUrl("https://youtu.be/tBGvOmUhhq4");
       // video6.setTitle("What happened after Jauhar of Padmavati");
        video6.setVideoId("ugxfT9b7XV4");
        mYoutubeVideo.add(video6);

        YoutubeVideo video7 = new YoutubeVideo();
        video7.setId(7l);
        video7.setVideoId("s8PJ1p2WDpo");
        mYoutubeVideo.add(video7);

        YoutubeVideo video8 = new YoutubeVideo();
        video8.setId(8l);
        video8.setVideoId("srGbyn8Ad5E");
        mYoutubeVideo.add(video8);

        YoutubeVideo video9 = new YoutubeVideo();
        video9.setId(9l);
        video9.setVideoId("0BnTAimbVes");
        mYoutubeVideo.add(video9);

        YoutubeVideo video10 = new YoutubeVideo();
        video10.setId((long) 111);
        video10.setVideoId("7yMXrERKX1M");
        mYoutubeVideo.add(video10);

//        mYoutubeVideo.add(video1);
//        mYoutubeVideo.add(video2);
//        mYoutubeVideo.add(video3);
//        mYoutubeVideo.add(video4);
        return mYoutubeVideo;
    }
}