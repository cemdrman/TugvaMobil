package org.tugva.basaksehir.tugvabasaksehir.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;

/**
 * Created by cdirman on 28.6.2016.
 */
public class GaleryGridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<String> imageList = new ArrayList();
    private ArrayList<String> titleList = new ArrayList();
    public GaleryGridViewAdapter(Context context, int layoutResourceId, ArrayList<String> imageList, ArrayList<String> titleList) {
        super(context, layoutResourceId, imageList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.imageList = imageList;
        this.titleList = titleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TextView imageTitle;
        ImageView image;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        imageTitle = (TextView) row.findViewById(R.id.text);
        image= (ImageView) row.findViewById(R.id.image);
        Picasso.with(getContext()).load(imageList.get(position)).into(image);
        imageTitle.setText(titleList.get(position));
        return row;
    }
}
