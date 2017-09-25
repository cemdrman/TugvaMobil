package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.tugva.basaksehir.tugvabasaksehir.Database.DatabaseHelper;
import org.tugva.basaksehir.tugvabasaksehir.Models.Icerik;
import org.tugva.basaksehir.tugvabasaksehir.Others.DepthPageTransformer;
import org.tugva.basaksehir.tugvabasaksehir.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends Fragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ArrayList<String> baslikList;
    private ArrayList<String> icerikList;
    private ArrayList<Integer> listOfURL;
    private LinearLayout dotsLayout;
    private int dotsCount;
    private TextView[] dots;
    private View rootView;
    private Map<String, String> expected;
    private TextView txtBaslik, txtIcerik;
    private ImageView imgSlider;

    private String secilenHaberBaslik = "";
    private String secilenHaberIcerik = "";
    private String secilenHaberFoto = "";
    private ArrayList<Icerik> allIcerikList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baslikList = new ArrayList<>();
        icerikList = new ArrayList<>();
        listOfURL = new ArrayList<>();
       // PD = new ProgressDialog(getActivity());
        //PD.setMessage("Lütfen Bekleyiniz...");
        //PD.setCancelable(false);

        rootView = inflater.inflate(R.layout.activity_home, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        // Depth geçişi
        this.viewPager.setPageTransformer(true, new DepthPageTransformer());
        getAllDataFromDb();
        return rootView;
    }

    public void getAllDataFromDb(){
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
        allIcerikList = dbHelper.getAllIcerik();
        expected = new HashMap<>();
        for (int i = 0; i < allIcerikList.size(); i++) {
            String baslik = allIcerikList.get(i).getIcerikBaslik();
            String icerik = allIcerikList.get(i).getIcerik();
            icerik = icerik.substring(0,150) + "...";
           /* Toast.makeText(getActivity(), icerik, Toast.LENGTH_SHORT).show();*/
            System.out.println(icerik);
            expected.put(baslik , icerik);
        }
        for (Map.Entry<String, String> entry : expected.entrySet()) {
            baslikList.add(entry.getKey());
            icerikList.add(entry.getValue());
        }
        setViewPagerItemsWithAdapter();
        setUiPageViewController();
    }

    private void setUiPageViewController() {
        dotsLayout = (LinearLayout) rootView.findViewById(R.id.viewPagerCountDots);
        dotsCount = myViewPagerAdapter.getCount();
        dots = new TextView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dotsLayout.addView(dots[i]);
        }
        dots[0].setTextColor(getResources().getColor(R.color.app_green));
    }
    private void setViewPagerItemsWithAdapter() {
        myViewPagerAdapter = new MyViewPagerAdapter(baslikList, icerikList);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(viewPagerPageChangeListener);
    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
            dots[position].setTextColor(getResources().getColor(R.color.app_green));
            setContent(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
//            System.out.println("scrolled");
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private void setContent(int position){
        ArrayList<String> iceriklerListesi = new ArrayList<>();
        for (int i = 0; i < allIcerikList.size(); i++) {
            String icerik = allIcerikList.get(i).getIcerik();
            icerik = icerik.substring(0,150) + "...";
            System.out.println(icerik);
            iceriklerListesi.add(icerik);
        }
        Picasso.with(getActivity()).load(allIcerikList.get(position).getIcerikUrl()).into(imgSlider);
        txtBaslik.setText(allIcerikList.get(position).getIcerikBaslik());
        txtIcerik.setText(iceriklerListesi.get(position));
        secilenHaberBaslik = allIcerikList.get(position).getIcerikBaslik();
        secilenHaberIcerik = allIcerikList.get(position).getIcerik();
        secilenHaberFoto =  allIcerikList.get(position).getIcerikUrl();
    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private ArrayList<String> items;
        public MyViewPagerAdapter(ArrayList<String> listOfItems, ArrayList<String> listOfName) {
            this.items = listOfItems;
            this.items = listOfName;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.view_slider_custom, container, false);
            txtBaslik = (TextView) view.findViewById(R.id.txtIcerikBaslik);
            txtIcerik = (TextView) view.findViewById(R.id.txtIcerik);
            imgSlider = (ImageView) view.findViewById(R.id.imgPagerSlider);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(getActivity(),HomeHaberDetayActivity.class);
                    intent.putExtra("secilenHaberBaslik",secilenHaberBaslik);
                    intent.putExtra("secilenHaberIcerik",secilenHaberIcerik);
                    intent.putExtra("secilenHaberFoto",secilenHaberFoto);
                    startActivity(intent);
                }
            });
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            ((ViewPager) container).removeView(view);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
