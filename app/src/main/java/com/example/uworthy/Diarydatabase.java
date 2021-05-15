package com.example.uworthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Diarydatabase {
    mydatabasehelper myhelper;

    public Diarydatabase(ActivityDiaryEdit activityDiaryEdit) {
        myhelper= new mydatabasehelper(activityDiaryEdit);
    }

    public Diarydatabase(Diarylist diarylist) {
        myhelper= new mydatabasehelper(diarylist);
    }

    public long insertdate(String name, String title, String body) {

            SQLiteDatabase data = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(mydatabasehelper.NAME,name);
            contentValues.put(mydatabasehelper.Title,title);
            contentValues.put(mydatabasehelper.Body,body);


            long nid = data.insert(mydatabasehelper.TABLE_NAME,null,contentValues);
//
//
            return nid;

    }
    public List<String> getAll_Simple() {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String query= "SELECT * FROM"+ mydatabasehelper. TABLE_NAME +"WHERE";
//        String selectQuery = "SELECT  " +
//                myhelper.NAME + "," +
//                myhelper.EMAIL +
//                " FROM " + myhelper.TABLE_NAME;

        List<String> studentList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(query, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                studentList.add(i,cursor.getString(cursor.getColumnIndex(mydatabasehelper.NAME)));
                studentList.add(i,cursor.getString(cursor.getColumnIndex(mydatabasehelper.Body)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    public ArrayList<HashMap<String, String>> getStudentList(String no) {



            String[] columns = {
                    mydatabasehelper.NAME, mydatabasehelper.Title, mydatabasehelper.Body
            };
            SQLiteDatabase db = myhelper.getReadableDatabase();

            // selection criteria
            String selection = mydatabasehelper.NAME + " = ?";

            // selection argument
            String[] selectionArgs = {no};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();
            Cursor cursor = db.query(mydatabasehelper.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> student = new HashMap<String, String>();



                    student.put("Title", cursor.getString(cursor.getColumnIndex(Student.KEY_Title)));
                    student.put("Body", cursor.getString(cursor.getColumnIndex(Student.KEY_Body)));

                    studentList.add(student);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return studentList;


    }

    //  public long insertdate(String namestr, String emailstr, String datestr) {







    // }
    static class mydatabasehelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "DiaryData";    // Database Name
        private static final String TABLE_NAME = "diary";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String Title = "Title";    //Column III

        private static final String Body = "Body";    //Column VII



        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+Title+" VARCHAR(255),"+Body+" VARCHAR);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private final Context context;

        public mydatabasehelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {

                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
}



