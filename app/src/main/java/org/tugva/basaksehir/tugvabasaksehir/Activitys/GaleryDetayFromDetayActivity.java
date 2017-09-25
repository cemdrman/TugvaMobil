package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.tugva.basaksehir.tugvabasaksehir.R;

/**
 * Created by cdirman on 4.8.2016.
 */
public class GaleryDetayFromDetayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery_detay_from_detay_activity);

        Bundle bundle = getIntent().getExtras();
        String imageURL = bundle.getString("ImageURL");
        ImageView imageView = (ImageView)findViewById(R.id.imgGaleryDetayFromDetay);
        Picasso.with(getApplicationContext()).load(imageURL).into(imageView);
    }
}
