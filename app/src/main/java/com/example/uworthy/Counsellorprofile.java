package com.example.uworthy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Counsellorprofile extends AppCompatActivity {
    DatabaseReference childdata;
    String counemail;
    FirebaseUser user;
    String counuid,usernamec;
    TextView usernametext,mobtxt,addtxt;
    String u,m,a,i;
    ImageView counimg;
    Button chatb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellorprofile);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        usernametext=findViewById(R.id.ustxt);
        mobtxt=findViewById(R.id.mobilenotxt);
        addtxt=findViewById(R.id.adstxt);
        counimg=findViewById(R.id.imageView10);
       chatb=findViewById(R.id.chatbtn);
       chatb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent ichat  = new Intent(getApplicationContext(),ChatActivity.class);
               startActivity(ichat);
           }
       });
        Intent intent = getIntent();
        usernamec =  intent.getStringExtra("usernamecoun");
        usernametext.setText(usernamec);

        user=FirebaseAuth.getInstance().getCurrentUser();
       counuid= user.getDisplayName();
        Toast.makeText(this, "name "+counuid, Toast.LENGTH_SHORT).show();


        FirebaseDatabase.getInstance().getReference("Counsellor")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        counemail= dataSnapshot.child(usernamec).child("usertype").getValue(String.class);
                        m=dataSnapshot.child(usernamec).child("mobileno").getValue(String.class);
                        mobtxt.setText(m);
                        a=dataSnapshot.child(usernamec).child("clnicaddress").getValue(String.class);
                        addtxt.setText(a);
                        i=dataSnapshot.child(usernamec).child("imageURL").getValue(String.class);
                        try {
                            URL uri = new URL(i);
                            Bitmap bmp = BitmapFactory.decodeStream(uri.openConnection().getInputStream());
                            counimg.setImageBitmap(bmp);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
