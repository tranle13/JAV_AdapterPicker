
    // Name: Tran Le
    // JAV1 - 1808
    // File name: MainActivity.java

package com.sunny.android.letran_ce04;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

    public class MainActivity extends AppCompatActivity {

        private int selectedView = 0;
        private int selectedAdapter = 0;
        private static final String TAG = "Hey dumbass";
        ArrayList<Person> people = new ArrayList<>();
        private ListView pListView = null;
        private GridView pGridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner viewSpinner = (Spinner)findViewById(R.id.spn_ListView);
        final Spinner adapterSpinner = (Spinner)findViewById(R.id.spn_ArrayAdapter);
        viewSpinner.setOnItemSelectedListener(viewSpinnerTapped);
        adapterSpinner.setOnItemSelectedListener(adapterSpinnerTapped);

        populateDataCollection();

        findList_GridView();
    }

    // Create function to get selected item from view spinner
    private final AdapterView.OnItemSelectedListener viewSpinnerTapped = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedView = position;

            if (selectedView == 0) {
                Log.i(TAG, "onItemSelected: WERE");
                pListView.setVisibility(View.VISIBLE);
                pGridView.setVisibility(View.INVISIBLE);
            } else {
                Log.i(TAG, "onItemSelected: USED TO");
                pListView.setVisibility(View.INVISIBLE);
                pGridView.setVisibility(View.VISIBLE);
            }

            setupAllAdapters();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "onItemClick: None");
        }
    };

    // Create function to get selected item from adapter spinner
    private final AdapterView.OnItemSelectedListener adapterSpinnerTapped = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedAdapter = position;

            setupAllAdapters();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "onItemClick: None");
        }
    };

    // Create function to populate the data collection - people
    protected void populateDataCollection() {
        people.add(new Person("Adam", "Varner", "2/19/1978", R.drawable.adam));
        people.add(new Person("Anjali", "Byrd", "3/4/1978", R.drawable.anjali));
        people.add(new Person("Arjun", "Baynard", "6/7/1979", R.drawable.arjun));
        people.add(new Person("Jorge", "Haecker", "6/25/1980", R.drawable.jorge));
        people.add(new Person("Maya", "Mack", "7/14/1981", R.drawable.maya));
        people.add(new Person("Rahul", "Cissell", "8/5/1981", R.drawable.rahul));
        people.add(new Person("Sadona", "Poehler", "11/19/1983", R.drawable.sadona));
        people.add(new Person("Sandy", "Kasha", "7/29/1984", R.drawable.sandy));
        people.add(new Person("Sid", "Smith", "10/17/1986", R.drawable.sid));
        people.add(new Person("Steve", "Stibbleton", "8/25/1996", R.drawable.steve));
    }

    // Create function to find listView
    protected void findList_GridView() {
        try {
            pListView = (ListView)findViewById(R.id.listView);
            pListView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    buildAlert(position);
                }
            });
            pGridView = (GridView)findViewById(R.id.gridView);
            pGridView.setOnItemClickListener(new GridView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    buildAlert(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create function to show the data for array adapter
    protected void setupArrayAdapter() {
        ArrayList<String> fullNames = new ArrayList<>();

        for (Person human: people) {
            fullNames.add(human.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.
                R.layout.simple_list_item_1, android.R.id.text1, fullNames);

        if (selectedView == 0) {
            pListView.setAdapter(arrayAdapter);
        } else if (selectedView == 1) {
            pGridView.setAdapter(arrayAdapter);
        }
    }

    // Create function to show the data for simple adapter
    protected void setupSimpleAdapter() {
        final String keyFullName = "fullName";
        final String keyBirthday = "birthday";

        ArrayList<HashMap<String, String>> dataCollection = new ArrayList<>();

        for (Person human: people) {
            HashMap<String, String> map = new HashMap<>();

            String strFullName = human.toString();
            String strBirthday = human.getBirthday();

            map.put(keyFullName, strFullName);
            map.put(keyBirthday, strBirthday);

            dataCollection.add(map);
        }

        final String[] keys = new String[]{keyFullName, keyBirthday};

        int[] viewIds = new int[] { R.id.txt_SimpleAdapter_FullName, R.id.txt_SimpleAdapter_Birthday };

        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, dataCollection,
                R.layout.simpleadapter_custom_view, keys, viewIds);

        if (selectedView == 0) {
            pListView.setAdapter(simpleAdapter);
        } else if (selectedView == 1) {
            pGridView.setAdapter(simpleAdapter);
        }
    }

    // Create function to show data for base adapter
    protected void setupBaseAdapter() {
        PersonAdapter adapter = new PersonAdapter(this, people);
        if (selectedView == 0 && pListView != null) {
            pListView.setAdapter(adapter);
        } else if (selectedView == 1 && pGridView != null) {
            pGridView.setAdapter(adapter);
        }
    }

    // Create function to setup adapters
    protected void setupAllAdapters() {
        if (selectedAdapter == 0) {
            setupArrayAdapter();
        } else if (selectedAdapter == 1) {
            setupSimpleAdapter();
        } else if (selectedAdapter == 2) {
            setupBaseAdapter();
        }
    }

    // Create function to make alert dialog
    private void buildAlert(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        Person chosen = people.get(position);
        builder.setTitle(chosen.toString());
        builder.setMessage(chosen.getBirthday());
        builder.setIcon(chosen.getImageID());
        builder.setPositiveButton(R.string.alert_pos_btn, null);
        builder.show();
    }

}
