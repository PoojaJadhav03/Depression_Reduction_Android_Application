package com.example.uworthy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Trackmood extends AppCompatActivity {
    ImageView colorfinal, emojifinal, imagefinal;
    Integer fccode,fecode,ficode;
    byte[] fcbyte;
    byte[] febyte;
    byte[]fibyte;
Button next;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackmood);


        colorfinal=findViewById(R.id.finalcolors);
        emojifinal=findViewById(R.id.finalemojis);
        imagefinal=findViewById(R.id.finalimages);
        next=findViewById(R.id.button9);

        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();


        fccode = intent.getIntExtra("finalcolorcodes",0);
        fcbyte = extras.getByteArray("finalimagecolors");
        fecode= intent.getIntExtra("finalemojicodes",0);
       febyte= extras.getByteArray("finalimageemoji");




        ficode= intent.getIntExtra("pnradiocodes",0);
        fibyte= extras.getByteArray("finalimages");
        //  Bitmap bmp = BitmapFactory.decodeByteArray(colorbytes,0,colorbytes.length);
        Toast.makeText(this, "FGHG"+fccode, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "FGHG"+ficode, Toast.LENGTH_SHORT).show();
        Bitmap bmpcolor = BitmapFactory.decodeByteArray(fcbyte,0,fcbyte.length);
        colorfinal.setImageBitmap(bmpcolor);
        Bitmap bmpimage = BitmapFactory.decodeByteArray(fibyte,0,fibyte.length);
        imagefinal.setImageBitmap(bmpimage);
        Bitmap bmpemoji = BitmapFactory.decodeByteArray(febyte,0,febyte.length);
        emojifinal.setImageBitmap(bmpemoji);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Statisticsresult.class);
                intent.putExtra("pnradiocodes", ficode);

                intent.putExtra("finalemojicodes", fecode);

                intent.putExtra("finalcolorcodes", fccode);

                startActivity(intent);
            }
        });
    }
}