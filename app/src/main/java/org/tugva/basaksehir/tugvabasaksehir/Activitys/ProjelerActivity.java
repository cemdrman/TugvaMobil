package org.tugva.basaksehir.tugvabasaksehir.Activitys;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tugva.basaksehir.tugvabasaksehir.Adapters.ProjelerImageAdapter;
import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;

/**
 * Created by cdirman on 27.6.2016.
 */

public class ProjelerActivity extends Activity {

    private String projelerJsonUrl = "http://eminebabacan.com/api/galeris";
    private ArrayList<String> imgList = new ArrayList<>();
    private ImageView imageView;
    private PopupWindow pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeler);
        final GridView gridView = (GridView) findViewById(R.id.gridViewProjeler);
        imageView = (ImageView)findViewById(R.id.imgPopupWindow);
        LayoutInflater inflater = (LayoutInflater) ProjelerActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate the view from a predefined XML layout
        final View layout = inflater.inflate(R.layout.popup_window_layout, (ViewGroup) findViewById(R.id.rl_custom_layout));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        pw = new PopupWindow(layout, width - 100, height -150, true);
        projelerImageJsonParse();
        gridView.setAdapter(new ProjelerImageAdapter(ProjelerActivity.this,imgList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pw.setBackgroundDrawable(new BitmapDrawable());
                pw.setOutsideTouchable(true);
                pw.setFocusable(true);
                // display the popup in the center
                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
                ImageView imageView = (ImageView)layout.findViewById(R.id.imgPopupWindow);
                Picasso.with(getApplicationContext()).load(imgList.get(i)).into(imageView);


            }
        });

    }
    private void projelerImageJsonParse(){
        JsonArrayRequest req = new JsonArrayRequest(projelerJsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject kategoriJson = (JSONObject) response.get(i);
                        String galeryUrl = kategoriJson.getString("GaleriURL");
                        System.out.println(galeryUrl);
                        imgList.add(galeryUrl);
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
