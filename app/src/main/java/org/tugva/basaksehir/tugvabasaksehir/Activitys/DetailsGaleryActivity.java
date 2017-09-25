package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tugva.basaksehir.tugvabasaksehir.Adapters.GaleryDetayGridViewAdapter;
import org.tugva.basaksehir.tugvabasaksehir.Adapters.GaleryGridViewAdapter;
import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;

/**
 * Created by cdirman on 28.6.2016.
 */
public class DetailsGaleryActivity extends Activity {
    private String galeryDetayJsonUrl = "http://eminebabacan.com/api/galeridetays/";
    private GridView gridView;
    private GaleryDetayGridViewAdapter gridAdapter;
    private ArrayList<String> detayImageList;
    private int secilenItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery_detail_activity);
        gridView =(GridView)findViewById(R.id.detayGridView);
        Bundle bundle = getIntent(). getExtras();
        secilenItem = bundle.getInt("position"); //secilenItem galery sayfasından +1 deger alarak gelir ve json gelen id ile eslesmesi icin yapılmıstır
        String url = galeryDetayJsonUrl + secilenItem;

        System.out.println("URL: " + url);
        galeryImageJsonParse(url);
        url = url.substring(7,url.length());
        System.out.println("New url: " + url);


        gridAdapter = new GaleryDetayGridViewAdapter(this,R.layout.galery_detay_activity_grid_item,detayImageList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(DetailsGaleryActivity.this,GaleryDetayFromDetayActivity.class);
                intent.putExtra("ImageURL",detayImageList.get(position));
                startActivity(intent);
            }
        });
    }

    private void galeryImageJsonParse(String jsonUrl){
        detayImageList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject galeryJson = (JSONObject) response.get(i);
                        String detayUrl = galeryJson.getString("DetayURL");
                        detayUrl = detayUrl.substring(8,detayUrl.length());
                        detayUrl = "http://"+detayUrl;
                        Toast.makeText(DetailsGaleryActivity.this, "DetayURL: " + detayUrl, Toast.LENGTH_SHORT).show();
                        detayImageList.add(detayUrl);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplication(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());
        requestQueue.add(req);
    }
}
