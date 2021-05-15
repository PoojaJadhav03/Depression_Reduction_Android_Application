package com.example.uworthy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AllCounselloractivity extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;
    String emailchat;
    String gg;
    ArrayList<HashMap<String, String>> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactList = new ArrayList<>();
        setContentView(R.layout.activity_all_counselloractivity);
        usersList = findViewById(R.id.usersList);
        noUsersText = findViewById(R.id.noUsersText);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        gg = intent.getStringExtra("name");

        Toast.makeText(this, "dfgdgdfg"+gg, Toast.LENGTH_SHORT).show();

//        lo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.signOut();
//                //closing activity
//                finish();
//                //starting login activity
//                startActivity(new Intent(AllCounselloractivity.this, Loginactivity.class));
//
//            }
//        });
//        String url1 = "https://uworthy-6fd8e.firebaseio.com/Counsellor.json";
//        System.out.println("JSON DATA LOADING  ...."+url1);
//        Toast.makeText(this, "JSOn"+url1, Toast.LENGTH_SHORT).show();

       // pd = new ProgressDialog(AllCounselloractivity.this);
//        pd.setMessage("Loading...");
//        pd.show();
        String url = "https://uworthy-6fd8e.firebaseio.com/Counsellor.json";
        System.out.println("JSON DATA LOADING  ...."+url);
       // pd.setMessage("Loading...");
      //  pd.show();



        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(AllCounselloractivity.this);
        rQueue.add(request);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
               //startActivity(new Intent(AllCounselloractivity.this, ChatActivity.class));
                Intent i = new Intent(getApplicationContext(), chat.class);
              //  i.putExtra("dataname", gg);
                UserDetails.useername=gg;
                startActivity(i);
                Toast.makeText(AllCounselloractivity.this, "username is "+UserDetails.chatWith, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);
          //  JSONArray jsonArray = new JSONArray(s);
            System.out.println(obj);



            Iterator i = obj.keys();
          //  JSONArray jsonArray = new JSONArray();
           String key = "";


//
//
            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.useername)) {
                    al.add(key);

                }

                totalUsers++;
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <0){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
//            ListAdapter adapter = new SimpleAdapter(AllCounselloractivity.this, contactList,
//                    R.layout.list_item, new String[]{"id", "name"},
//                    new int[]{R.id.email, R.id.mobile}) ;
//
//            usersList.setAdapter(adapter);
        }

      //  pd.dismiss();
    }
}