package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {
    Button therapiesbtn,storiesbtn,nutritionbtn,depretionbtn,lifeboxbtn,logoutbtn,chatbtn;
    private FirebaseAuth firebaseAuth;
    String receivedName;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       //S Bundle extras = getIntent().getExtras();
        user=FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
       receivedName =  intent.getStringExtra("emailid");
       UserDetails.username=receivedName;
      Toast.makeText(this, "this is uname login"+receivedName, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, "this is uname login"+UserDetails.username, Toast.LENGTH_SHORT).show();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        therapiesbtn=findViewById(R.id.btntherapies);
        storiesbtn=findViewById(R.id.btnstories);
        nutritionbtn=findViewById(R.id.btnnutritions);
        depretionbtn=findViewById(R.id.btndesper);
        lifeboxbtn=findViewById(R.id.btnlifebox);

        therapiesbtn.setOnClickListener(this);
        storiesbtn.setOnClickListener(this);
        nutritionbtn.setOnClickListener(this);
        depretionbtn.setOnClickListener(this);
        lifeboxbtn.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Loginactivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();

        FirebaseDatabase.getInstance().getReference("Combodata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String plz = dataSnapshot.child(user.getUid()).child("usertype").getValue(String.class);
                System.out.println(dataSnapshot.toString());
                System.out.println(plz);
                Toast.makeText(User_home.this, "plz data"+plz, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(getApplicationContext(),AllCounselloractivity.class);
         //   Intent i = new Intent(getApplicationContext(), User_home.class);
          i.putExtra("name", receivedName);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(getApplicationContext(),ActivityDiaryEdit.class);
            i.putExtra("name", receivedName);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(getApplicationContext(),Listofcontacts.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_send) {
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Loginactivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id==R.id.btntherapies){
            Intent i1 = new Intent(getApplicationContext(),Choosecolor.class);
            startActivity(i1);
        }
        if(id==R.id.btnstories){
            Intent i1 = new Intent(getApplicationContext(),Storiesactivity.class);
            startActivity(i1);
        }
        if(id==R.id.btnnutritions){
            Intent i1 = new Intent(getApplicationContext(),DiteActivity.class);
            startActivity(i1);
        }
        if(id==R.id.btnlifebox){
            Intent i1 = new Intent(getApplicationContext(),Videofile.class);
            startActivity(i1);
        }
        if(id==R.id.btndesper){
            Intent i1 = new Intent(getApplicationContext(),QuestionActivity.class);
            startActivity(i1);
        }
    }
}
