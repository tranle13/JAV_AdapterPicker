
    // Name: Tran Le
    // JAV1 - 1808
    // File name: MainActivity.java

package com.sunny.android.letran_ce04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

    public class MainActivity extends AppCompatActivity {

        private int selectedView;
        private int selectedAdapter;
        private static final String TAG = "Hey dumbass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner viewSpinner = (Spinner)findViewById(R.id.spn_ListView);
        final Spinner adapterSpinner = (Spinner)findViewById(R.id.spn_ArrayAdapter);
        viewSpinner.setOnItemSelectedListener(viewSpinnerTapped);
        adapterSpinner.setOnItemSelectedListener(adapterSpinnerTapped);

    }

    // Create function to get selected item from view spinner
    private final AdapterView.OnItemSelectedListener viewSpinnerTapped = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedView = position;
            Log.i(TAG, "onItemClick: "+selectedView+" "+parent.getItemAtPosition(position));
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
            Log.i(TAG, "onItemClick: "+selectedAdapter+" "+parent.getItemAtPosition(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Log.i(TAG, "onItemClick: None");
        }
    };
}
