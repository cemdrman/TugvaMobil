package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.tugva.basaksehir.tugvabasaksehir.R;

/**
 * Created by cdirman on 30.6.2016.
 */
public class IletisimActivity extends Activity {
    static final LatLng tugvaKor = new LatLng(41.01461 , 28.9068853);
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(tugvaKor));
            //harita yakınlasacak
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Marker tugvaMarker = googleMap.addMarker(new MarkerOptions().
                    position(tugvaKor).title("Türkiye Gençlik Vakfı\n" +
                    "Merkezefendi Mahallesi Mevlana Caddesi\n" +
                    "No: 140/A Toya Plaza Kat:1, Zeytinburnu / İSTANBUL \n" +
                    "EMAIL: info@tugva.org"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
