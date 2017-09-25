package org.tugva.basaksehir.tugvabasaksehir.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.tugva.basaksehir.tugvabasaksehir.R;
import java.util.ArrayList;
/**
 * Created by cdirman on 4.8.2016.
 */
public class GaleryDetayGridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<String> detayImageList;

    public GaleryDetayGridViewAdapter(Context context, int resource,ArrayList<String> detayImageList) {
        super(context, resource,detayImageList);
        this.detayImageList = detayImageList;
        this.layoutResourceId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageView image;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        image= (ImageView) row.findViewById(R.id.galeryImageDetayView);
        Picasso.with(getContext()).load(detayImageList.get(position)).into(image);
        return row;
    }
}
