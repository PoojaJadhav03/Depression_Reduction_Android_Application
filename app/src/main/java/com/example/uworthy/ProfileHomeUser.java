package com.example.uworthy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileHomeUser extends AppCompatActivity {
    Button hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home_user);
        hi=findViewById(R.id.buttonme);
        Firebase.setAndroidContext(this);
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useername="me";
                String password="data1234";
                final ProgressDialog pd = new ProgressDialog(ProfileHomeUser.this);
                pd.setMessage("Loading...");
                pd.show();
                String url = "https://uworthy-6fd8e.firebaseio.com/Users.json";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://uworthy-6fd8e.firebaseio.com/Users");
                        if(s.equals("null")) {
                            reference.child(useername).child("password").setValue(password);
                            Toast.makeText(ProfileHomeUser.this, "registration successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            try {
                                JSONObject obj = new JSONObject(s);

                                if (!obj.has(useername)) {
                                    reference.child(useername).child("password").setValue(password);
                                    Toast.makeText(ProfileHomeUser.this, "registration successful", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(ProfileHomeUser.this, "username already exists", Toast.LENGTH_LONG).show();
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
                        System.out.println("" + volleyError );
                        pd.dismiss();
                    }
                });
                RequestQueue rQueue = Volley.newRequestQueue(ProfileHomeUser.this);
                rQueue.add(request);
            }
        });
    }
}
