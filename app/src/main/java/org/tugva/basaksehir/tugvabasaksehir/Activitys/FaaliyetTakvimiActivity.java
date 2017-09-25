package org.tugva.basaksehir.tugvabasaksehir.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import org.tugva.basaksehir.tugvabasaksehir.Adapters.FaaliyetExpandableListAdapter;
import org.tugva.basaksehir.tugvabasaksehir.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FaaliyetTakvimiActivity extends Activity {
    private FaaliyetExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faaliyet_takvimi);

        expListView = (ExpandableListView) findViewById(R.id.navdrawer);
        listViewDoldur();
        listAdapter = new FaaliyetExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }
    private void listViewDoldur(){
        listDataHeader = new ArrayList<>();
        listDataChild  = new HashMap<>();

        HashMap<ArrayList<String>,String> stringHashMap = new HashMap<>();

        listDataHeader.add("13\t\t Pazar\n\t\t\t\t Nisan,2016");
        listDataHeader.add("14\t\t Pazartesi\n\t\t\t\t Mayıs,2016");
        listDataHeader.add("15\t\t Salı\n\t\t\t\t Haziran,2016");

        List<List> lists = new ArrayList<>();

        List<String> top250 = new ArrayList<String>();
        top250.add("Robotik");
        top250.add("Osmanlıca");

        List<String> nowShowing = new ArrayList<>();
        nowShowing.add("Robotik");
        nowShowing.add("Osmanlıca");

        List<String> comingSoon = new ArrayList<>();
        comingSoon.add("Robotik");
        comingSoon.add("Osmanlıca");
        lists.add(top250);
        lists.add(nowShowing);
        lists.add(comingSoon);

        for (int i = 0; i < 3; i++) {
            listDataChild.put(listDataHeader.get(i), lists.get(i)); // Header, Child data
        }

    }
}
