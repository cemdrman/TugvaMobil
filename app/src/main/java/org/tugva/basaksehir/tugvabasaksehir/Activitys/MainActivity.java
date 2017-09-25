package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tugva.basaksehir.tugvabasaksehir.Adapters.ExpandableListAdapter;
import org.tugva.basaksehir.tugvabasaksehir.Others.LoadFragment;
import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;

    private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    private ProgressDialog PD;

    private String kategoriUrlJsonArry = "http://www.eminebabacan.com/api/kategorilers";
    private String altKategoriUrlJsonArry = "http://www.eminebabacan.com/api/altkategorilers";

    private static MainActivity mainActivity;
    private static RequestQueue mRequestQueue;
    public synchronized static MainActivity getInstance() {
        return mainActivity;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PD = new ProgressDialog(this);
        PD.setMessage("Lütfen Bekleyiniz...");
        PD.setCancelable(false);

        mRequestQueue = Volley.newRequestQueue(this);
        mainActivity = this;
        kategoriJsonParse();

        LoadFragment loadFragmentObj = new LoadFragment(getFragmentManager());
        loadFragmentObj.initializeFragment(new HomeActivity());

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        expListView = (ExpandableListView) findViewById(R.id.navdrawer);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final String selectedChild = (String) listAdapter.getChild(groupPosition, childPosition);
                Intent sayfaGecisi ;
                switch (selectedChild){
                    case "GELECEK PROJELER" : sayfaGecisi = new Intent(MainActivity.this,ProjelerActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "FAALİYET TAKVİMİ": sayfaGecisi = new Intent(MainActivity.this,FaaliyetTakvimiActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "Haber Gönder": sayfaGecisi = new Intent(MainActivity.this,HaberGonderActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                }
                return true;
            }
        });
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                final String selectedGroup = (String) listAdapter.getGroup(groupPosition);

                Intent sayfaGecisi;
                switch (selectedGroup){
                    /*case "Haberler": sayfaGecisi = new Intent(MainActivity.this, HaberlerActivity.class);
                        startActivity(sayfaGecisi);
                        break;*/
                    case "Hakkımızda": sayfaGecisi = new Intent(MainActivity.this,HakkimizdaActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                   /* case "Yönetim" : sayfaGecisi = new Intent(MainActivity.this,YönetimActivity.class);
                        startActivity(sayfaGecisi);
                        break;*/
                    case "Koordinatörlükler" : sayfaGecisi = new Intent(MainActivity.this, KoordinatorlukActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "Projeler" : sayfaGecisi = new Intent(MainActivity.this,ProjelerActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "Faaliyetler" : sayfaGecisi = new Intent(MainActivity.this,FaaliyetTakvimiActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "Foto Albüm": sayfaGecisi = new Intent(MainActivity.this,GaleryActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    case "Üyelik": sayfaGecisi = new Intent(MainActivity.this,UyelikActivity.class);
                        startActivity(sayfaGecisi);
                        break;
                    /*case "Anket" : sayfaGecisi = new Intent(MainActivity.this,AnketActivity.class);
                        startActivity(sayfaGecisi);
                        break;*/
                    case "İletişim": sayfaGecisi = new Intent(MainActivity.this,IletisimActivity.class);
                        startActivity(sayfaGecisi);
                        break;


                }
                return false;
            }
        });

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.logo);
        expListView.addHeaderView(imageView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, drawerArrow, R.string.drawer_open,R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    private ArrayList<String> kategoriList ;
    private ArrayList<String> altKategoriList ;
    private void kategoriJsonParse() {
        listDataHeader = new ArrayList<>();
        listDataChild  = new HashMap<>();
        JsonArrayRequest req = new JsonArrayRequest(kategoriUrlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject kategoriJson = (JSONObject) response.get(i);
                                listDataHeader.add(kategoriJson.getString("KategoriAdi"));
                                altKategoriList = new ArrayList<>();
                                listDataChild.put(listDataHeader.get(i), altKategoriJsonParse(kategoriJson.getInt("KategoriID")));
                            }
                            PD.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(req);
    }

    private ArrayList<String> altKategoriJsonParse(int kategoriId){
        final ArrayList<String> arrayList = new ArrayList<>();
        JsonArrayRequest req = new JsonArrayRequest(altKategoriUrlJsonArry+"/"+kategoriId,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject kategoriJson = (JSONObject) response.get(i);
                                arrayList.add(kategoriJson.getString("AltKategoriAdi"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
        return arrayList;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}

