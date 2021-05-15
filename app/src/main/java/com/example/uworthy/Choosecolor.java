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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Choosecolor  extends AppCompatActivity {
    ImageButton b1,b2,b3,b4,b5,b6;
    ImageView img;

    GridView colorgv;
    Button nextcolor;
    int colorid;

    Integer[] imgid ={
            R.drawable.yellow,
            R.drawable.pink,
            R.drawable.white,
            R.drawable.black,
            R.drawable.blue,
            R.drawable.green,
            R.drawable.brown,
            R.drawable.purple,
            R.drawable.red
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecolor);
colorgv=findViewById(R.id.gvcolor);
img=findViewById(R.id.imageView6);
nextcolor=findViewById(R.id.colorbtn);
nextcolor.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // Toast.makeText(Choosecolor.this, " please select color...", Toast.LENGTH_SHORT).show();
       // Toast.makeText(Choosecolor.this, " select color is yellow..."+colorid, Toast.LENGTH_SHORT).show();
        if(img.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.qsmark).getConstantState())){
            Toast.makeText(Choosecolor.this, " select color...", Toast.LENGTH_SHORT).show();
            //Toast.makeText(Choosecolor.this, " select color is yellow..."+colorid, Toast.LENGTH_SHORT).show();
        }
        else {
            if(colorid==1){
               // Toast.makeText(Choosecolor.this, " select color is yellow...80"+colorid, Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.yellow);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=80;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==2){
               // Toast.makeText(Choosecolor.this, " select color is pink...90"+colorid, Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pink);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=90;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==3){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.white);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=50;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);

            }
            else if(colorid==4){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.black);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=0;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==5){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.blue);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=30;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==6){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.green);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=40;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==7){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.brown);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=70;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else if(colorid==8){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.purple);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=60;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.red);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytesarray = stream.toByteArray();
                Intent intent = new Intent(getApplicationContext(), Emoji.class);
                Integer imagecode=100;
                intent.putExtra("name", imagecode);
                intent.putExtra("image", bytesarray);
                startActivity(intent);
            }
        }
    }
});

       // final ImageAdapter imgadat = new ImageAdapter(Choosecolor.this);
        colorgv.setAdapter(new ImageAdapter(this));


        colorgv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(),
                        "pic" + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
                colorid=position+1;

              //  img.setImageResource(id[position]);

             //   img.setImageResource(imgid[position+1]);

             ImageView imageView= (ImageView) view;
             img.setImageResource(imgid[position]);

              //  ImageView imageView=(ImageView) findViewById(R.id.imageView);

            }
        });

    }
    public class ImageAdapter extends BaseAdapter
    {
        Integer[] imgid ={
                R.drawable.yellow,
                R.drawable.pink,
                R.drawable.white,
                R.drawable.black,
                R.drawable.blue,
                R.drawable.green,
                R.drawable.brown,
                R.drawable.purple,
                R.drawable.red
        };
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
            return position;
        }
        //---returns the ID of an item---
        public long getItemId(int position) {
            return position;
        }
        //---returns an ImageView view---
        @SuppressLint("ResourceType")
        public View getView(int position, View convertView,
                            ViewGroup parent)
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new
                        GridView.LayoutParams(200, 200));
                imageView.setScaleType(
                        ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(10, 10, 10, 10);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgid[position]);
           // imageView.set
           // img.setImageResource(imgid[position]);
           // imageView.setImageResource(imgid[position+1]);
           //img.setImageResource(imageView.getId(position));
           // img.setImageResource(imgid[position]);
            return imageView;
        }
    }
}
