package com.example.uworthy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Emoji extends AppCompatActivity {
    ImageView imgemoji;
    int colorid;
    GridView emojigv;
    Button emojinext;
    byte[] colorbytess;
    Integer colorcodes;

    Integer[] imgid ={
            R.drawable.happy,
            R.drawable.joy,
            R.drawable.worry,
            R.drawable.angry,
            R.drawable.fear,
            R.drawable.sad,
            R.drawable.proud,
            R.drawable.embarrassed,
            R.drawable.normal
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emojie);

        emojigv=findViewById(R.id.gvemoji);
        imgemoji=findViewById(R.id.imageemoji);
        emojinext=findViewById(R.id.buttonemoji);
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        //String receivedName =  intent.getStringExtra("name");
        colorcodes = intent.getIntExtra("name",0);
         colorbytess = extras.getByteArray("image");
      //  Bitmap bmp = BitmapFactory.decodeByteArray(colorbytes,0,colorbytes.length);
        Toast.makeText(this, "FGHG"+colorcodes, Toast.LENGTH_SHORT).show();
        emojinext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imgemoji.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.qsmark).getConstantState())){
                    Toast.makeText(Emoji.this, " select color...", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Choosecolor.this, " select color is yellow..."+colorid, Toast.LENGTH_SHORT).show();
                }
                else {
                    if(colorid==1){
                        // Toast.makeText(Choosecolor.this, " select color is yellow...80"+colorid, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.happy);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=70;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==2){
                        // Toast.makeText(Choosecolor.this, " select color is pink...90"+colorid, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.joy);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=100;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==3){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.worry);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=20;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);

                    }
                    else if(colorid==4){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.angry);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=40;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==5){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fear);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=10;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==6){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sad);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=0;
                        intent.putExtra("name", imagecode);
                        intent.putExtra("image", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==7){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.proud);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=90;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else if(colorid==8){
                        Integer imagecode=30;
                        Toast.makeText(Emoji.this, "gffh"+imagecode, Toast.LENGTH_SHORT).show();

                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.embrassment);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                         intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                    else {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.normal);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                        byte[] bytesarray = stream.toByteArray();
                        Intent intent = new Intent(getApplicationContext(), ChooseImage.class);
                        Integer imagecode=50;
                        intent.putExtra("emojicode", imagecode);
                        intent.putExtra("imageemoji", bytesarray);
                        intent.putExtra("colorcode", colorcodes);
                        intent.putExtra("imagecolor", colorbytess);
                        startActivity(intent);
                    }
                }
            }
        });



        // final ImageAdapter imgadat = new ImageAdapter(Choosecolor.this);
        emojigv.setAdapter(new Emoji.ImageAdapter(this));



        emojigv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub


                    Toast.makeText(getBaseContext(),
                            "pic" + (position + 1) + " happy",
                            Toast.LENGTH_SHORT).show();

                  colorid=position+1;
                //  img.setImageResource(id[position]);

                //   img.setImageResource(imgid[position+1]);

                ImageView imageView= (ImageView) view;
                imgemoji.setImageResource(imgid[position]);

                //  ImageView imageView=(ImageView) findViewById(R.id.imageView);

            }
        });

    }
    public class ImageAdapter extends BaseAdapter
    {

        private final Context context;
        public ImageAdapter(Context c)
        {
            context = c;
        }
        //---returns the number of images---
        public int getCount() {
            return imgid.length;
        }
        //---returns the item---
        public Object getItem(int position) {
            return null;
        }
        //---returns the ID of an item---
        public long getItemId(int position) {
            return 0;
        }
        //---returns an ImageView view---
        @SuppressLint("ResourceType")
        public View getView(int position, View convertView,
                            ViewGroup parent)

        {
            ImageView mImageView;

            if (convertView == null) {
                mImageView = new ImageView(context);
                mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setPadding(16, 16, 16, 16);
            } else {
                mImageView = (ImageView) convertView;
            }
            mImageView.setImageResource(imgid[position]);
            return mImageView;

//            ImageView imageView;
//            if (convertView == null) {
//                imageView = new ImageView(context);
//                imageView.setLayoutParams(new
//                        GridView.LayoutParams(200, 200));
//                imageView.setScaleType(
//                        ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(10, 10, 10, 10);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//            imageView.setImageResource(imgid[position]);
//            // imageView.set
//            // img.setImageResource(imgid[position]);
//            // imageView.setImageResource(imgid[position+1]);
//            //img.setImageResource(imageView.getId(position));
//            // img.setImageResource(imgid[position]);
//            return imageView;
        }
    }
}
