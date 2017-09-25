package org.tugva.basaksehir.tugvabasaksehir.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by cdirman on 27.7.2016.
 */
public class ProjelerImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> imgList;

    public ProjelerImageAdapter(Context c, ArrayList<String> imgList){
        mContext = c;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object getItem(int i) {
        return imgList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        Picasso.with(mContext).load(imgList.get(position)).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(350, 550));
        imageView.setPadding(10,10,10,10);
        return imageView;
    }
}
