package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.tugva.basaksehir.tugvabasaksehir.R;

/**
 * Created by cdirman on 28.7.2016.
 */
public class HomeHaberDetayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_haber_detay);
        TextView txtHomeHaberDetayBaslik = (TextView)findViewById(R.id.txtHomeHaberDetayBaslik);
        TextView txtHomeHaberDetayIcerik = (TextView)findViewById(R.id.txtHomeHaberDetayIcerik);
        ImageView imgHomeHaberDetayFoto = (ImageView)findViewById(R.id.imgHomeHaberDetayFoto);

        Bundle bundle = getIntent().getExtras();
        txtHomeHaberDetayBaslik.setText(bundle.getString("secilenHaberBaslik"));
        txtHomeHaberDetayIcerik.setText(bundle.getString("secilenHaberIcerik"));
        Picasso.with(getApplicationContext()).load(bundle.getString("secilenHaberFoto")).into(imgHomeHaberDetayFoto);


    }
}
