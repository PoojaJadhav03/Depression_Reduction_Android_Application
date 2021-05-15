package com.example.uworthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class Dialerpad extends AppCompatActivity {
    EditText edtPhoneNo;
    TextView lblinfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialerpad);
//        edtPhoneNo = (EditText) findViewById(R.id.edtPhoneNumber);
//        lblinfo = (TextView) findViewById(R.id.lblinfo);
    }

//    public void buttonClickEvent(View v) {
//        String phoneNo = edtPhoneNo.getText().toString();
//        try {
//
//            switch (v.getId()) {
//                case R.id.btnAterisk:
//                    lblinfo.setText("");
//                    phoneNo += "*";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnHash:
//                    lblinfo.setText("");
//                    phoneNo += "#";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnZero:
//                    lblinfo.setText("");
//                    phoneNo += "0";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnOne:
//                    lblinfo.setText("");
//                    phoneNo += "1";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnTwo:
//                    lblinfo.setText("");
//                    phoneNo += "2";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnThree:
//                    lblinfo.setText("");
//                    phoneNo += "3";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnFour:
//                    lblinfo.setText("");
//                    phoneNo += "4";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnFive:
//                    lblinfo.setText("");
//                    phoneNo += "5";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnSix:
//                    lblinfo.setText("");
//                    phoneNo += "6";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnSeven:
//                    lblinfo.setText("");
//                    phoneNo += "7";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnEight:
//                    lblinfo.setText("");
//                    phoneNo += "8";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnNine:
//                    lblinfo.setText("");
//                    phoneNo += "9";
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btndel:
//                    lblinfo.setText("");
//                    if (phoneNo != null && phoneNo.length() > 0) {
//                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
//                    }
//
//                    edtPhoneNo.setText(phoneNo);
//                    break;
//                case R.id.btnClearAll:
//                    lblinfo.setText("");
//                    edtPhoneNo.setText("");
//                    break;
//                case R.id.btnCall:
//                    if (phoneNo.trim().equals("")) {
//                        lblinfo.setText("Please enter a number to call on!");
//                    } else {
//                        Boolean isHash = false;
//                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
//                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
//                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
//                            Intent callIntent = new Intent(Intent.ACTION_CALL);
//                            callIntent.setData(Uri.parse(callInfo));
//                            startActivity(callIntent);
//                        } else {
//                            String callInfo = "tel:" + phoneNo;
//                            Intent callIntent = new Intent(Intent.ACTION_CALL);
//                            callIntent.setData(Uri.parse(callInfo));
//                            startActivity(callIntent);
//                        }
//                    }
//                    break;
//            }
//
//        } catch (Exception ex) {
//
//        }
//    }

}