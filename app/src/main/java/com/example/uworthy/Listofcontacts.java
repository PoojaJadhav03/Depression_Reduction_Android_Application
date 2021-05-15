package com.example.uworthy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Listofcontacts extends AppCompatActivity {

    // Store all phone contacts list.
    // Each String format is " DisplayName \r\n Phone Number \r\n Phone Type " ( Jerry \r\n 111111 \r\n Home) .
    private final List<String> phoneContactsList = new ArrayList<String>();

    // This is the phone contacts list view's data adapter.
    private ArrayAdapter<String> contactsListDataAdapter;

    ListView contactsListView = null;

    private final int PERMISSION_REQUEST_CODE_READ_CONTACTS = 1;

    private final int PERMISSION_REQUEST_CODE_WRITE_CONTACTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofcontacts);

      //  setTitle("dev2qa.com - Android Content Resolver Example.");

        // Get contacts list view.
        contactsListView = findViewById(R.id.display_phone_ocntacts_list_view);
        // Create the list view data adapter.
        contactsListDataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, phoneContactsList);
        // Set data adapter to the list view.
        contactsListView.setAdapter(contactsListDataAdapter);
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
              //Toast.makeText(Listofcontacts.this, "gno"+selectedItem, Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(Intent.ACTION_CALL);
                i1.setData(Uri.parse("tel:"+selectedItem));
                startActivity(i1);
            }
        });

        // Click this button start add phone contact activity.
        Button addPhoneContactsButton = findViewById(R.id.add_phone_contacts_button);
        addPhoneContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPhoneContactsPermission(Manifest.permission.WRITE_CONTACTS))
                {
                    requestPermission(Manifest.permission.WRITE_CONTACTS, PERMISSION_REQUEST_CODE_WRITE_CONTACTS);
                }else
                {
                    Addcontacts.start(getApplicationContext());
                }
            }
        });

        // Click this button to get and display phone contacts in the list view.
        Button readPhoneContactsButton = findViewById(R.id.read_phone_contacts_button);
        readPhoneContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                {
                    requestPermission(Manifest.permission.READ_CONTACTS, PERMISSION_REQUEST_CODE_READ_CONTACTS);
                }else
                {
                    readPhoneContacts();
                }
            }
        });
    }

    // Check whether user has phone contacts manipulation permission or not.
    private boolean hasPhoneContactsPermission(String permission)
    {
        boolean ret = false;

        // If android sdk version is bigger than 23 the need to check run time permission.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // return phone read contacts permission grant status.
            int hasPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            // If permission is granted then return true.
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                ret = true;
            }
        }else
        {
            ret = true;
        }
        return ret;
    }

    // Request a runtime permission to app user.
    private void requestPermission(String permission, int requestCode)
    {
        String[] requestPermissionArray = {permission};
        ActivityCompat.requestPermissions(this, requestPermissionArray, requestCode);
    }

    // After user select Allow or Deny button in request runtime permission dialog
    // , this method will be invoked.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int length = grantResults.length;
        if(length > 0)
        {
            int grantResult = grantResults[0];

            if(grantResult == PackageManager.PERMISSION_GRANTED) {

                if(requestCode==PERMISSION_REQUEST_CODE_READ_CONTACTS)
                {
                    // If user grant read contacts permission.
                    readPhoneContacts();
                }else if(requestCode==PERMISSION_REQUEST_CODE_WRITE_CONTACTS)
                {
                    // If user grant write contacts permission then start add phone contact activity.
                    Addcontacts.start(getApplicationContext());
                }
            }else
            {
                Toast.makeText(getApplicationContext(), "You denied permission.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Read and display android phone contacts in list view.
    private void readPhoneContacts()
    {

        // First empty current phone contacts list data.
        int size = phoneContactsList.size();
        for(int i=0;i<size;i++)
        {
            phoneContactsList.remove(i);
            i--;
            size = phoneContactsList.size();
        }

        // Get query phone contacts cursor object.
        Uri readContactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(readContactsUri, null, null, null, null);

        if(cursor!=null)
        {
            cursor.moveToFirst();

            // Loop in the phone contacts cursor to add each contacts in phoneContactsList.
            do{
                // Get contact display name.
                int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String userDisplayName = cursor.getString(displayNameIndex);

                // Get contact phone number.
                int phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber = cursor.getString(phoneNumberIndex);

                // Get contact phone type.
                String phoneTypeStr = "Mobile";
                int phoneTypeColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                int phoneTypeInt = cursor.getInt(phoneTypeColumnIndex);
                if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                {
                    phoneTypeStr = "Home";
                }else if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                {
                    phoneTypeStr = "Mobile";
                }else if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                {
                    phoneTypeStr = "Work";
                }

                StringBuffer contactStringBuf = new StringBuffer();
                contactStringBuf.append(phoneNumber);
//                contactStringBuf.append("\r\n");
//                contactStringBuf.append(phoneNumber);
//                contactStringBuf.append("\r\n");
//                contactStringBuf.append(phoneTypeStr);

                phoneContactsList.add(contactStringBuf.toString());
            }while(cursor.moveToNext());

            // Refresh the listview to display read out phone contacts.
            contactsListDataAdapter.notifyDataSetChanged();
        }
    }
}
