package com.example.uworthy;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Loginactivity extends AppCompatActivity {
    TextView registerUser;
    EditText username, password1,usernameeit;
    Button loginButton;
    TextView dis;
    String email, password,uname,iddata,uid;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
   // Firebase databaseReference;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String CurrentID;
    RadioGroup radioGroup;
    String usertypestr;
    @Override
    protected void onStart() {
        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        dis=findViewById(R.id.textView62);
        user=FirebaseAuth.getInstance().getCurrentUser();
        radioGroup=findViewById(R.id.usetyperadio);
        usernameeit=findViewById(R.id.usercoun);

    //   CurrentID= firebaseAuth.getCurrentUser().getUid();
       firebaseAuth=FirebaseAuth.getInstance();
//databaseReference=FirebaseDatabase.getInstance().getReference().child("Combodata");

        if (firebaseAuth.getCurrentUser() != null) {
         //   finish();
          // Intent i = new Intent(getApplicationContext(), User_home.class);
         //  i.putExtra("name", UserDetails.username);
          // startActivity(i);
          //  startActivity(new Intent(getApplicationContext(), User_home.class));
        }


        registerUser = findViewById(R.id.textView5);
        username = findViewById(R.id.emailedit);
        password1 = findViewById(R.id.editText2);
        loginButton = findViewById(R.id.button5);


        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this, Registerhome.class));
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroup.findViewById(i);
                if(null!=rb && i > -1){
                    usertypestr=rb.getText().toString();
                    //  Toast.makeText(Userregister.this, ""+gender, Toast.LENGTH_SHORT).show();

                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // radioGroup=findViewById(R.id.genderuser);
                String useredit= usernameeit.getText().toString();
                if(useredit!=null){
                    if(usertypestr!=null){
                        loginuser();
                       // loginurl();
                    }
                }


            }
        });

//
    }

    public void loginuser() {
        email = username.getText().toString().trim();
        password = password1.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            String ig = firebaseUser.getUid();

                            FirebaseDatabase firebaseDatabase1;
                            uid=user.getUid();
                            String ume = firebaseUser.getEmail();
                            //  Userdata getdatass = new Userdata();
                            if(usertypestr.equals("Counsellor")){
                               Intent i = new Intent(getApplicationContext(),Counsellorprofile.class);
                               i.putExtra("usernamecoun",usernameeit.getText().toString());
                               startActivity(i);
                            }
                            else if(usertypestr.equals("User")){

                                Intent i = new Intent(getApplicationContext(),User_home.class);
                                i.putExtra("emailid",ume);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(Loginactivity.this, " Plz Select user type...", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(Loginactivity.this, "Login Succe..", Toast.LENGTH_SHORT).show();

                            String unameee= user.getDisplayName();
                            Toast.makeText(Loginactivity.this, "usertype  id"+unameee, Toast.LENGTH_SHORT).show();
                            firebaseAuth= FirebaseAuth.getInstance();


                        } else {
                            Toast.makeText(Loginactivity.this, "user not found..", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

//
    }

    public  void loginurl(){

        String url = "https://uworthy-6fd8e.firebaseio.com/Users.json";
        final ProgressDialog pd = new ProgressDialog(Loginactivity.this);
        pd.setMessage("Loading...");
        pd.show();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                if(s.equals("null")){
                    Toast.makeText(Loginactivity.this, "user not found", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        JSONObject obj = new JSONObject(s);

                        if(!obj.has("gn")){
                            Toast.makeText(Loginactivity.this, "user not found", Toast.LENGTH_LONG).show();
                        }
                        else if(obj.getJSONObject("gn").getString("password").equals("gn1234")){

                           startActivity(new Intent(Loginactivity.this, User_home.class));
                        }
                        else {
                            Toast.makeText(Loginactivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                pd.dismiss();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Loginactivity.this);
        rQueue.add(request);
    }
}



