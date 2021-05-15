package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseImage extends AppCompatActivity {
    GridView gridView;
    ArrayList birdList=new ArrayList<>();
    byte[] colourbytes;
    byte[] emojibyte;
    Integer colourcode,emojicode;
    String[] fruitNames = {"Apple","Orange","strawberry","Melon","Kiwi","Banana","jhg","hg"};
    String[] positiveradio = {"lady","ball","boat","white sign on black","box on surface","flower pots","roots of tree","white cat"};
    String[] negeradio = {"skull","hole","skull","black sign on white","box on surface","two faces"," tree","black cat"};

    int[] fruitImages = {R.drawable.skull,R.drawable.hole,R.drawable.boat,R.drawable.plus,R.drawable.ice,R.drawable.flower,R.drawable.tree,R.drawable.cat};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosseimage);
        gridView = findViewById(R.id.gridview);





        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();


        //String receivedName =  intent.getStringExtra("name");
        colourcode = intent.getIntExtra("colorcode",0);
        colourbytes = extras.getByteArray("imagecolor");
        emojicode= intent.getIntExtra("emojicode",0);
        emojibyte= extras.getByteArray("imageemoji");
        //  Bitmap bmp = BitmapFactory.decodeByteArray(colorbytes,0,colorbytes.length);
         Toast.makeText(this, "FGHG"+colourcode, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "FGHG"+emojicode, Toast.LENGTH_SHORT).show();
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(ChooseImage.this, positiveradio,negeradio, fruitImages);

        gridView.setAdapter(adapterViewAndroid);
//        CustomAdapter customAdapter = new CustomAdapter(this);
//        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), GridItemActivity.class);
                intent.putExtra("pradio", positiveradio[i]);
                intent.putExtra("nradio", negeradio[i]);
                intent.putExtra("image", fruitImages[i]);
                intent.putExtra("emojicodes", emojicode);
                intent.putExtra("imageemoji", emojibyte);
                intent.putExtra("colorcodes", colourcode);
                intent.putExtra("imagecolors", colourbytes);
                startActivity(intent);

            }
        });
    }

//        private class CustomAdapter extends BaseAdapter {
//            private Context context;
//
//            int[] fruitImages = {R.drawable.skull,R.drawable.hole,R.drawable.boat,R.drawable.plus,R.drawable.ice,R.drawable.flower,R.drawable.tree,R.drawable.cat};
//            public CustomAdapter(Context c)
//            {
//                context = c;
//            }
//            @Override
//            public int getCount() {
//                return fruitImages.length;
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
//                View view1 = getLayoutInflater().inflate(R.layout.row_data,null);
//                ImageView imageView1;
//                if (view == null) {
//                    imageView1 = new ImageView(context);
//                    imageView1.setLayoutParams(new
//                            GridView.LayoutParams(200, 200));
//                    imageView1.setScaleType(
//                            ImageView.ScaleType.CENTER_CROP);
//                    imageView1.setPadding(10, 10, 10, 10);
//                } else {
//                    imageView1 = (ImageView) view;
//                }
//                //getting view in row_data
//              //  TextView name = view1.findViewById(R.id.fruits);
//                ImageView image = view1.findViewById(R.id.images1);
//                RadioButton prd = view1.findViewById(R.id.radioButton2);
//                RadioButton nrd = view1.findViewById(R.id.radioButton);
//              //  prd.setVisibility(View.INVISIBLE);
//               // nrd.setVisibility(View.INVISIBLE);
//
//
//              //  name.setText(fruitNames[i]);
//           //   image.setImageResource(fruitImages[i]);
//              //  prd.setText(positiveradio[i]);
//              //  nrd.setText(negeradio[i]);
//               // image.setImageResource(imgid[i]);
//                imageView1.setImageResource(fruitImages[i]);
//                return view;
//
//
//
//            }
//        }
    }