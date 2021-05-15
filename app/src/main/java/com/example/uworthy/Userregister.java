package com.example.uworthy;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

public class Userregister extends AppCompatActivity {
    String Storage_Path = "All_Image_Uploads_User/";

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "All_Image_Uploads_Database_User";

    private EditText name1, mobile, emailidedit, addressedit, usernameedit, passwordedit, cpasswordedit;
    Button save, browserbtn;
    String gender;
    RadioGroup radioGroup;
    RadioButton malebtn, femalebtn;
    ImageView profileimg;
    private static final int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    int Image_Request_Code = 7;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Uri FilePathUri;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private final DatabaseReference mChildreference = databaseReference.child("User");
    private ImageButton calenderbtn;
    private TextView caltext;
    private FirebaseAuth firebaseAuth;


    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {

        }
        mChildreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregister);
        FirebaseApp.initializeApp(this);
      //  Firebase.setAndroidContext(this);
     // Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

//        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        progressDialog = new ProgressDialog(Userregister.this);


        caltext = findViewById(R.id.textcal);
        calenderbtn = findViewById(R.id.imageButtoncal);
        calenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Userregister.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                view.setMaxDate(System.currentTimeMillis() - 1000);
                                caltext.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });


        browserbtn = findViewById(R.id.browseruser);
        browserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });
        profileimg = findViewById(R.id.profileimage);
        malebtn = findViewById(R.id.radiomale);
        femalebtn = findViewById(R.id.radiofemale);
        emailidedit = findViewById(R.id.editemailid);

        addressedit = findViewById(R.id.editaddress);
        usernameedit = findViewById(R.id.editusername);
        passwordedit = findViewById(R.id.editpassword);
        cpasswordedit = findViewById(R.id.editconfirmpass);
        radioGroup=findViewById(R.id.genderuser);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroup.findViewById(i);
                if(null!=rb && i > -1){
                    gender=rb.getText().toString();
                  //  Toast.makeText(Userregister.this, ""+gender, Toast.LENGTH_SHORT).show();

                }
            }
        });

        name1 = findViewById(R.id.editname);
        mobile = findViewById(R.id.editphone);
        save = findViewById(R.id.usersavebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  UploadImageFileToFirebaseStorage();


               finalregisteruser();
               // datame();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                profileimg.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                // ChooseButton.setText("Image Selected");

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }


public  void  datame(){
    String emailid = emailidedit.getText().toString().trim();

    String dob = caltext.getText().toString().trim();
    String clnicaddress = addressedit.getText().toString().trim();
    String username = usernameedit.getText().toString().trim();
    String password = passwordedit.getText().toString().trim();
    String usertype = "2";
    String name = name1.getText().toString().trim();
    String mobileno = mobile.getText().toString().trim();
    final ProgressDialog pd = new ProgressDialog(Userregister.this);
    pd.setMessage("Loading...");
    pd.show();
    String url = "https://uworthy-6fd8e.firebaseio.com/Combodata.json";
    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
        @Override
        public void onResponse(String s) {
            Firebase reference = new Firebase("https://uworthy-6fd8e.firebaseio.com/Combodata");
            if(s.equals("null")) {
                progressDialog.setTitle("Register ...");
//
                // Showing progressDialog.
              //  progressDialog.show();
                reference.child(username).child("password").setValue(password);
                Toast.makeText(Userregister.this, "registration successful", Toast.LENGTH_LONG).show();
            }
            else {
                try {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.has(username)) {
                        reference.child(username).child("password").setValue(password);
                        reference.child(username).child("Mobileno").setValue(mobileno);
                        Toast.makeText(Userregister.this, "registration successful", Toast.LENGTH_LONG).show();
                       // Intent i = new Intent(Userregister.this, Loginactivity.class);
                       // startActivity(i);
                    } else {
                        Toast.makeText(Userregister.this, "username already exists", Toast.LENGTH_LONG).show();
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
    RequestQueue rQueue = Volley.newRequestQueue(Userregister.this);
    rQueue.add(request);
}

    public void finalregisteruser() {
        String emailid = emailidedit.getText().toString().trim();

        String dob = caltext.getText().toString().trim();
        String clnicaddress = addressedit.getText().toString().trim();
        String useername = usernameedit.getText().toString().trim();
        String password = passwordedit.getText().toString().trim();
        String usertype = "2";
        String name = name1.getText().toString().trim();
        String mobileno = mobile.getText().toString().trim();
        String id = databaseReference.push().getKey();
        firebaseAuth.createUserWithEmailAndPassword(emailid, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (FilePathUri != null) {

                                // Setting progressDialog Title.

                                // Creating second StorageReference.
                                final StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));


                                storageReference2nd.putFile(FilePathUri)
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                // Getting image name from EditText and store into string variable.
                                                String TempImageName = name1.getText().toString().trim();




                                                progressDialog.dismiss();


                                                Userdata data = new Userdata(id, name, emailid, mobileno, clnicaddress, gender,dob, useername, password, usertype, useername, taskSnapshot.toString());
                                                //  Savedatauser savedate = new Savedatauser(id, name, mobileno, emailid, gender, clnicaddress, useername, password, usertype, TempImageName, taskSnapshot.getStorage().getDownloadUrl().toString());
                                                FirebaseDatabase.getInstance().getReference("User")
                                                        .child(useername)
                                                        .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Userregister.this, "Register succeefully", Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                            }
                                        })
                                        // If something goes wrong .
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception exception) {

                                                // Hiding the progressDialog.
                                                progressDialog.dismiss();

                                                // Showing exception erro message.
                                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        })

                                        // On progress change upload time.
                                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                                // Setting progressDialog Title.
                                                progressDialog.setTitle("Register...");

                                            }
                                        });
                            }

                                                //  Savedatauser savedate = new Savedatauser(id, name, mobileno, emailid, gender, clnicaddress, useername, password, usertype, TempImageName, taskSnapshot.getStorage().getDownloadUrl().toString());



                              else {

                                Toast.makeText(Userregister.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

                            }

                        } else {
                            Toast.makeText(Userregister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            final StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempImageName = name1.getText().toString().trim();

                            // taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                            //  Toast.makeText(UploadActivity.this,"Upload successful",Toast.LENGTH_SHORT).show();
                            //progressDialog.dismiss();
                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();


                            String emailid = emailidedit.getText().toString().trim();

                            String dob = caltext.getText().toString().trim();
                            String clnicaddress = addressedit.getText().toString().trim();
                            String useername = usernameedit.getText().toString().trim();
                            String password = passwordedit.getText().toString().trim();
                            String usertype = "2";
                            String name = name1.getText().toString().trim();
                            String mobileno = mobile.getText().toString().trim();
                            String id = databaseReference.push().getKey();
                            @SuppressWarnings("VisibleForTests")
                            Userdata data = new Userdata(id, name, emailid, mobileno, clnicaddress, gender,dob, useername, password, usertype, useername, taskSnapshot.toString());
                            //  savedata savedate = new savedata(id,name,mobileno,emailid,gender,healthcenter,clnicaddress,useername,password,usertype,TempImageName,taskSnapshot.getStorage().getDownloadUrl().toString());
                                   // savedata data = new savedata(id, name, emailid, mobileno, clnicaddress, gender,dob, useername, password, usertype, TempImageName, taskSnapshot.toString());
                            //ImageUploadInfo imageUploadInfo = new ImageUploadInfo(TempImageName, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            // String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(useername)
                                    .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Userregister.this, "Register succeefully", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //  databaseReference.child(id).setValue(savedate);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(Userregister.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }


}
