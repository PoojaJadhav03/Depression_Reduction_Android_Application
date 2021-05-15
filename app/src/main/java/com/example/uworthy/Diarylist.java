package com.example.uworthy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Diarylist extends AppCompatActivity {
    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private DiaryDbAdapter mDbHelper;
    private Cursor mDiaryCursor;
    ListView lv;
    Diarydatabase help;
    String fetchname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);
      help= new Diarydatabase(this);
      //  renderListView();


        Bundle extras = getIntent().getExtras();
        Intent intentr = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        fetchname = intentr.getStringExtra("names");

        lv=findViewById(R.id.listdiary);

       // String no = "renuka";
        ArrayList<HashMap<String, String>> studentList =  help.getStudentList(fetchname);
        if(studentList.size()!=0) {
            // ListView lv = getListView();

            ListAdapter adapter = new SimpleAdapter( Diarylist.this,studentList, R.layout.diary_row, new String[] { "Title","Body"}, new int[] {R.id.text1, R.id.created});
            //  lv. setListAdapter(adapter);
            lv.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(),"No Patient!",Toast.LENGTH_SHORT).show();
        }
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i1 = new Intent(Diarylist.this, ActivityDiaryEdit.class);

                TextView tite = view.findViewById(R.id.text1);
                TextView body = view.findViewById(R.id.created);

                i1.putExtra("Title" ,tite.getText().toString());
                i1.putExtra("Body",body.getText().toString());
                startActivity(i1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent i = new Intent(Diarylist.this, ActivityDiaryEdit.class);
         TextView tite = view.findViewById(R.id.text1);
        TextView body = view.findViewById(R.id.created);
        i.putExtra("Title" ,tite.getText().toString());
        i.putExtra("Body",body.getText().toString());
        startActivity(i);
    }

});
    }

//
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        switch (item.getItemId()) {
//            case INSERT_ID:
//                createDiary();
//                return true;
//            case DELETE_ID:
//                mDbHelper.deleteDiary(getListView().getSelectedItemId());
//                renderListView();
//                return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
//
//    private void createDiary() {
//        Intent i = new Intent(this, ActivityDiaryEdit.class);
//        startActivityForResult(i, ACTIVITY_CREATE);
//    }
//
//    @Override
//
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        Cursor c = mDiaryCursor;
//        c.moveToPosition(position);
//        Intent i = new Intent(this, ActivityDiaryEdit.class);
//        i.putExtra(DiaryDbAdapter.KEY_ROWID, id);
//        i.putExtra(DiaryDbAdapter.KEY_TITLE, c.getString(c
//                .getColumnIndexOrThrow(DiaryDbAdapter.KEY_TITLE)));
//        i.putExtra(DiaryDbAdapter.KEY_BODY, c.getString(c
//                .getColumnIndexOrThrow(DiaryDbAdapter.KEY_BODY)));
//        startActivityForResult(i, ACTIVITY_EDIT);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        renderListView();
//    }
}