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
import org.tugva.basaksehir.tugvabasaksehir.Adapters.GaleryGridViewAdapter;
import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;

/**
 * Created by cdirman on 28.6.2016.
 */
public class GaleryActivity extends Activity {
    private String galeryJsonUrl = "http://eminebabacan.com/api/galeris";
    private GridView gridView;
    private GaleryGridViewAdapter gridAdapter;
    private ArrayList galeryImageList = new ArrayList();
    private ArrayList galeryTitleList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        gridView = (GridView) findViewById(R.id.gridView);
        galeryImageJsonParse();
        gridAdapter = new GaleryGridViewAdapter(this, R.layout.galery_activity_grid_item, galeryImageList,galeryTitleList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentDetayGesis = new Intent(GaleryActivity.this,DetailsGaleryActivity.class);
                intentDetayGesis.putExtra("position",position + 1);
                startActivity(intentDetayGesis);
            }
        });
    }

    private void galeryImageJsonParse(){
        JsonArrayRequest req = new JsonArrayRequest(galeryJsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject galeryJson = (JSONObject) response.get(i);
                        String galeryUrl = galeryJson.getString("GaleriURL");
                        String galeryAciklama = galeryJson.getString("GaleriAciklama");
                        System.out.println(galeryAciklama);
                        System.out.println(galeryUrl);
                        galeryImageList.add(galeryUrl);
                        galeryTitleList.add(galeryAciklama);
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
