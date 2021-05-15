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

import java.io.IOException;
import java.util.Calendar;

public class Counsellorregister extends AppCompatActivity {
    String Storage_Path = "All_Image_Uploads_counsellor/";
    TextView caltxt;
    ImageButton calbtn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private RadioGroup rg;

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "All_Image_Uploads_Database_counsellor";

  private  EditText name1,mobile,emailidedit,healthcenteredit,caddressedit,usernameedit,passwordedit,cpasswordedit;
    Button save,browserbtn;
    String gender,downloadstr;
    RadioButton malebtn,femalebtn;
    ImageView profileimg;
    private static final int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    int Image_Request_Code = 7;
    StorageReference storageReference;
    ProgressDialog progressDialog ;
    Uri FilePathUri;
   private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
   private DatabaseReference databaseReference =firebaseDatabase.getReference();
   private final DatabaseReference  mChildreference = databaseReference.child("Counsellor");









    @Override
    protected void onStart() {
        super.onStart();
        mChildreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counsellorregister);
        FirebaseApp.initializeApp(this);
       calbtn=findViewById(R.id.calcounsellor);
       caltxt=findViewById(R.id.textView16);
       rg=findViewById(R.id.counradiogruop);

        firebaseAuth = FirebaseAuth.getInstance();



       rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
       calbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // Get Current Date
               final Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);


               DatePickerDialog datePickerDialog = new DatePickerDialog(Counsellorregister.this,
                       new DatePickerDialog.OnDateSetListener() {

                           @SuppressLint("SetTextI18n")
                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {
                               view.setMaxDate(System.currentTimeMillis() - 1000);
                               caltxt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                           }
                       }, mYear, mMonth, mDay);
               datePickerDialog.show();

           }
       });



        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        progressDialog = new ProgressDialog(Counsellorregister.this);

       browserbtn=findViewById(R.id.button4);
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
       profileimg=findViewById(R.id.imageView3);
        malebtn= findViewById(R.id.radioButtonmale);
        femalebtn= findViewById(R.id.radioButtonfemale);
        emailidedit= findViewById(R.id.editText9);
        healthcenteredit= findViewById(R.id.editText14);
        caddressedit= findViewById(R.id.editText10);
        usernameedit= findViewById(R.id.editText11);
        passwordedit= findViewById(R.id.editText12);
        cpasswordedit= findViewById(R.id.editText13);
        if(malebtn.isChecked()){
            gender=malebtn.getText().toString();
        }
        else if(femalebtn.isChecked()){
            gender=femalebtn.getText().toString();
        }
        name1= findViewById(R.id.editText6);
        mobile= findViewById(R.id.editText7);
        save= findViewById(R.id.button3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalregistercounsellor();
                //UploadImageFileToFirebaseStorage();
            }
//



        });
    }
//    public void loadImagefromGallery(View view) {
//        // Create intent to Open Image applications like Gallery, Google Photos
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // Start the Intent
//        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
//                    && null != data) {
//                // Get the Image from data
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//                // Get the cursor
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                // Move to first row
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgDecodableString = cursor.getString(columnIndex);
//                cursor.close();
//              //  ImageView imgView = (ImageView) findViewById(R.id.imgView);
//                // Set the Image in ImageView after decoding the String
//                profileimg.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));
//
//            } else {
//                Toast.makeText(this, "You haven't picked Image",
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
//                    .show();
//        }
//
//    }
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

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void user(){
        String emailid=emailidedit.getText().toString().trim();

        String healthcenter=healthcenteredit.getText().toString().trim();
        String clnicaddress=caddressedit.getText().toString().trim();
        String useername=usernameedit.getText().toString().trim();
        String password=passwordedit.getText().toString().trim();
        String usertype="1";
        String name = name1.getText().toString().trim();
        String mobileno= mobile.getText().toString().trim();
       String id= databaseReference.push().getKey();


      //  savedata savedate = new savedata(id,name,mobileno,emailid,gender,healthcenter,clnicaddress,useername,password,usertype);
     //   databaseReference.child(id).setValue(savedate);
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


                            String emailid=emailidedit.getText().toString().trim();
                            String dob=caltxt.getText().toString();
                            String healthcenter=healthcenteredit.getText().toString().trim();
                            String clnicaddress=caddressedit.getText().toString().trim();
                            String useername=usernameedit.getText().toString().trim();
                            String password=passwordedit.getText().toString().trim();
                            String usertype="1";
                            String name = name1.getText().toString().trim();
                            String mobileno= mobile.getText().toString().trim();
                            String id= databaseReference.push().getKey();

                            @SuppressWarnings("VisibleForTests")
                          //  savedata savedate = new savedata(id,name,mobileno,emailid,gender,healthcenter,clnicaddress,useername,password,usertype,TempImageName,taskSnapshot.getStorage().getDownloadUrl().toString());
                            savedata data = new savedata(id, name, emailid, mobileno, clnicaddress, gender,dob, useername, password, usertype, TempImageName, taskSnapshot.toString());
                            //ImageUploadInfo imageUploadInfo = new ImageUploadInfo(TempImageName, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                           // String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            FirebaseDatabase.getInstance().getReference("Counsellor")
                                    .child(useername)
                                    .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Counsellorregister.this, "Register succeefully", Toast.LENGTH_SHORT).show();
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

            Toast.makeText(Counsellorregister.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    public void finalregistercounsellor() {
        String emailid=emailidedit.getText().toString().trim();
        String dob=caltxt.getText().toString();
        String healthcenter=healthcenteredit.getText().toString().trim();
        String clnicaddress=caddressedit.getText().toString().trim();
        String useername=usernameedit.getText().toString().trim();
        String password=passwordedit.getText().toString().trim();
        String usertype="1";
        String name = name1.getText().toString().trim();
        String mobileno= mobile.getText().toString().trim();
        String id= databaseReference.push().getKey();
        firebaseAuth.createUserWithEmailAndPassword(emailid, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (FilePathUri != null) {

                                // Setting progressDialog Title.
                                progressDialog.setTitle("Register ...");

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




                                                progressDialog.dismiss();
                                               savedata data = new savedata(id, name, emailid, mobileno, clnicaddress, gender,dob, useername, password, usertype, TempImageName, taskSnapshot.toString());
                                                //  Savedatauser savedate = new Savedatauser(id, name, mobileno, emailid, gender, clnicaddress, useername, password, usertype, TempImageName, taskSnapshot.getStorage().getDownloadUrl().toString());
                                                FirebaseDatabase.getInstance().getReference("Counsellor")
                                                        .child(useername)
                                                        .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(Counsellorregister.this, "Register succeefully", Toast.LENGTH_SHORT).show();
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
                               else {

                                Toast.makeText(Counsellorregister.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

                            }

                        } else {
                            Toast.makeText(Counsellorregister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }
}
