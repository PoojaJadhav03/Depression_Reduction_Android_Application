package com.example.uworthy;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Supportpeople extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supportpeople);

        TabHost tabHost = getTabHost();

        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Keypad");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Keypad", getResources().getDrawable(R.drawable.keypad));
        Intent photosIntent = new Intent(this, Dialerpad.class);
        photospec.setContent(photosIntent);

        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Logs");
        songspec.setIndicator("Logs", getResources().getDrawable(R.drawable.callicon));
        Intent songsIntent = new Intent(this, Addcontacts.class);
        songspec.setContent(songsIntent);

        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("fav");
        videospec.setIndicator("fav", getResources().getDrawable(R.drawable.favcall));
        Intent videosIntent = new Intent(this, Calllogs.class);
        videospec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}