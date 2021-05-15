package com.example.uworthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    Button resultbtn;
    RadioButton one,two ,three,four,five,six,seven,eight,nine,ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        resultbtn=findViewById(R.id .button2);







        one=findViewById(R.id.oneyes);
        two=findViewById(R.id.twoyes);
        three=findViewById(R.id.threeyes);
        four=findViewById(R.id.fouryes);
        five=findViewById(R.id.fiveyes);
        six=findViewById(R.id.sixyes);
        seven=findViewById(R.id.sevenyes);
        eight=findViewById(R.id.eightyes);
        nine=findViewById(R.id.nineyes);
        ten=findViewById(R.id.tenyes);
        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yes=0;
                if(one.isChecked()){
                    yes++;
                }
                if(two.isChecked()){
                    yes++;
                }
                if(three.isChecked()){
                    yes++;
                }
                if(four.isChecked()){
                    yes++;
                }
                if(five.isChecked()){
                    yes++;
                }
                if(six.isChecked()){
                    yes++;
                }
                if(seven.isChecked()){
                    yes++;
                }
                if(eight.isChecked()){
                    yes++;
                }
                if(nine.isChecked()){
                    yes++;
                }
                if(ten.isChecked()){
                    yes++;
                }
                if(yes!=0) {
                    if (yes <= 3) {
                        Toast.makeText(QuestionActivity.this, "You are far away from being depressed. You have warm, optimistic and full of life. Keep up the good vibes!!!", Toast.LENGTH_SHORT).show();
                    }
                    else if(yes>=4 &&yes<=7){
                        Toast.makeText(QuestionActivity.this, "You feel blue sometimes, as well as you also feel good. Its part of life.dont worry too much!", Toast.LENGTH_SHORT).show();
                        
                    }
                    
                }
                else {
                    Toast.makeText(QuestionActivity.this, "NEED SOME HELP OF OUR EXPERTIES”)", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(QuestionActivity.this, "yse is"+yes, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
