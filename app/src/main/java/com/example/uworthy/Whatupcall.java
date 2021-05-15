package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Whatupcall extends AppCompatActivity {
Button callwat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatupcall);
        callwat=findViewById(R.id.button10);
        callwat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {









                String msg ="hihfkgjdh";
                Intent send =new Intent();
                send .setAction(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_TEXT,msg);
                send.setType("text/plain");
                send.setPackage("com.whatsapp");
                startActivity(send);



            }
        });
    }
}
