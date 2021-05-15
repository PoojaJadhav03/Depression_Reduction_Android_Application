package com.example.uworthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

class CustomGridViewActivity extends BaseAdapter {
Context context;
    int[] fruitImages ;
    String[] positiveradio ;
    String[] negeradio;


    public CustomGridViewActivity(ChooseImage chooseImage, String[] positiveradio, String[] negeradio, int[] fruitImages) {

            context = chooseImage;






            this.positiveradio = positiveradio;
            this.negeradio = negeradio;
            this.fruitImages = fruitImages;

    }

    @Override
    public int getCount() {
        return fruitImages.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            gridViewAndroid = new View(context);
            gridViewAndroid = inflater.inflate(R.layout.row_data, null);
           // TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            ImageView imageViewAndroid = gridViewAndroid.findViewById(R.id.images1);
            RadioButton prd = gridViewAndroid.findViewById(R.id.radioButton2);
                RadioButton nrd = gridViewAndroid.findViewById(R.id.radioButton);
               prd.setVisibility(View.INVISIBLE);
                nrd.setVisibility(View.INVISIBLE);
                prd.setText(positiveradio[i]);
                nrd.setText(negeradio[i]);
            imageViewAndroid.setImageResource(fruitImages[i]);
        } else {
            gridViewAndroid = view;
        }
        return gridViewAndroid;
    }
}
