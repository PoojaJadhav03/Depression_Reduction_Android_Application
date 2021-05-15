package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Registerhome extends AppCompatActivity {
    Button counsellorbtn,userbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerhome);




        counsellorbtn= findViewById(R.id.regcounsellerbtn);
        counsellorbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent con = new Intent(getApplicationContext(),Counsellorregister.class);
                startActivity(con);

            }
        });
        userbtn= findViewById(R.id.reguserbtn);
        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent user = new Intent(getApplicationContext(),Userregister.class);
                startActivity(user);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.choosecolor){
            Intent intent = new Intent(getApplicationContext(),UserHome.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.usermenu, menu);
        return true;
    }
}
