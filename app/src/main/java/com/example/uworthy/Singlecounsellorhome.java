package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Singlecounsellorhome extends AppCompatActivity {
    DatabaseReference childdata;
    String childname;
    Button vbtn,conmebtn,mentn,finalbtn;
TextView emailidtxt,usernametxt,mobiletxt,addresstxt;
String loginemail,chatemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singelcounsellor);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        loginemail = intent.getStringExtra("name");
        UserDetails.lname=loginemail;


emailidtxt=findViewById(R.id.emailid);
usernametxt=findViewById(R.id.counname);
mobiletxt=findViewById(R.id.textView9);
addresstxt=findViewById(R.id.textView63);
usernametxt.setText(UserDetails.chatWith);

finalbtn=findViewById(R.id.button13);
finalbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent ii = new Intent(getApplicationContext(),chat.class);
        startActivity(ii);
    }
});


FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("Counsellor")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String mobi,address;
                        chatemail= dataSnapshot.child(UserDetails.chatWith).child("emailid").getValue(String.class);
                        mobi=dataSnapshot.child(UserDetails.chatWith).child("mobileno").getValue(String.class);
                       address= dataSnapshot.child(UserDetails.chatWith).child("cinicaddress").getValue(String.class);
                        emailidtxt.setText(chatemail);
                        mobiletxt.setText(mobi);
                        addresstxt.setText(address);
                        UserDetails.cname=chatemail;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
