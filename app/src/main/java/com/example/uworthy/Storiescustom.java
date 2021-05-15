package com.example.uworthy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class Storiescustom  extends BaseAdapter {
    Context context;
   // int [] storiess;
    int [] storiess ={R.string.storiesone,R.string.stories2,R.string.storiesthree,R.string.stories4,R.string.stories5,R.string.stories6,R.string.stories7,R.string.stories8,R.string.stories9};
    public Storiescustom(Storiesactivity storiesactivity, int[] storiess) {
        this.context=storiesactivity;
        this.storiess=storiess;
    }

    @Override
    public int getCount() {
        return storiess.length;
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

        if (view == null) {

            gridViewAndroid = new View(context);

            // TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridViewAndroid = inflater.inflate(R.layout.storiestext, null);
            TextView storiestxt = gridViewAndroid.findViewById(R.id.textView30);






            storiestxt.setText(storiess[i]);
        } else {
            gridViewAndroid = view;
        }

        return gridViewAndroid;

    }


}
