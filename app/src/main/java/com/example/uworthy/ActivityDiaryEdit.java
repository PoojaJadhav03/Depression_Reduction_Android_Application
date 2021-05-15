package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityDiaryEdit extends AppCompatActivity {

    private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    Diarydatabase diarydatabase;
    private DiaryDbAdapter mDbHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.diary_now);

        Bundle extras = getIntent().getExtras();
        Intent intentr = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        username = intentr.getStringExtra("name");
   diarydatabase= new Diarydatabase(this);
        mTitleText = findViewById(R.id.title);
        mBodyText = findViewById(R.id.body);

        Intent intent = getIntent();

        String title1 = intent.getStringExtra("Title");
        String body1 = intent.getStringExtra("Body");


        mTitleText.setText(title1);

        mBodyText.setText(body1);

        Button confirmButton = findViewById(R.id.confirm);





        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name=username;

                String title = mTitleText.getText().toString();
                String body = mBodyText.getText().toString();
//                if (mRowId != null) {
//                    mDbHelper.updateDiary(mRowId, title, body);
//                } else
//
//                    mDbHelper.createDiary(title, body);
//                Intent mIntent = new Intent();
//                setResult(RESULT_OK, mIntent);
//


                long id = diarydatabase.insertdate(name,title,body);
                if(id<=0){
                    Toast.makeText(ActivityDiaryEdit.this, " Not insert...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ActivityDiaryEdit.this, "insert data"+id, Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.diarymenu){
            Intent intent = new Intent(getApplicationContext(),Diarylist.class);
            intent.putExtra("names", username);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diarylistmenu, menu);
        return true;
    }
}