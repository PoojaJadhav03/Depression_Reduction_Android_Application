package com.example.uworthy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class GridItemActivity extends AppCompatActivity {

    TextView gridData;
    ImageView imageView;
    byte[] colorbytes;
    byte[] emojibyte;
    Integer colorcode,emojicode;
    RadioButton pbtn,nbtn;
    String pradiostr,nradiostr;
    Button nextgridrow;
    Integer pnimagecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_items);
 nextgridrow= findViewById(R.id.buttongridfinal);
 nextgridrow.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         if(pbtn.isChecked()==true){
             pnimagecode=75;

             Drawable drawable =  imageView.getDrawable();
             Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
             byte[]finalpnimage = byteArrayOutputStream.toByteArray();
             Intent intent = new Intent(getApplicationContext(), Trackmood.class);
             intent.putExtra("pnradiocodes", pnimagecode);
             intent.putExtra("finalimages", finalpnimage);
             intent.putExtra("finalemojicodes", emojicode);
             intent.putExtra("finalimageemoji", emojibyte);
             intent.putExtra("finalcolorcodes", colorcode);
             intent.putExtra("finalimagecolors", colorbytes);
             startActivity(intent);

         }
         else if(nbtn.isChecked()==true){
             pnimagecode=25;
             Drawable drawable =  imageView.getDrawable();
             Bitmap bitmap =((BitmapDrawable)drawable).getBitmap();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
             byte[]finalpnimage = byteArrayOutputStream.toByteArray();
             Intent intent = new Intent(getApplicationContext(), Trackmood.class);
             intent.putExtra("pnradiocodes", pnimagecode);
             intent.putExtra("finalimages", finalpnimage);
             intent.putExtra("finalemojicodes", emojicode);
             intent.putExtra("finalimageemoji", emojibyte);
             intent.putExtra("finalcolorcodes", colorcode);
             intent.putExtra("finalimagecolors", colorbytes);
             startActivity(intent);

         }
         else {
             Toast.makeText(GridItemActivity.this, "Select image options...", Toast.LENGTH_LONG).show();
         }
     }
 });
        gridData = findViewById(R.id.griddata);
        imageView = findViewById(R.id.imageView);
        pbtn=findViewById(R.id.radioButtonpositiv);
        nbtn=findViewById(R.id.radioButtonneg);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();

        int receivedImage = intent.getIntExtra("image",0);
        pradiostr =  intent.getStringExtra("pradio");
        nradiostr =  intent.getStringExtra("nradio");
        colorcode = intent.getIntExtra("colorcodes",0);
        colorbytes = extras.getByteArray("imagecolors");
        emojicode= intent.getIntExtra("emojicodes",0);
        emojibyte= extras.getByteArray("imageemoji");
        //  Bitmap bmp = BitmapFactory.decodeByteArray(colorbytes,0,colorbytes.length);
         Toast.makeText(this, "FGHG"+colorcode, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "FGHG"+emojicode, Toast.LENGTH_SHORT).show();
         pbtn.setText(pradiostr);
         nbtn.setText(nradiostr);

        imageView.setImageResource(receivedImage);
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}