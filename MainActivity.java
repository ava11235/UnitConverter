package com.examples.spinner1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.text.NumberFormat;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, OnEditorActionListener{

        private Spinner conversionSpinner;
        private TextView fromUnitLabel;
        private EditText fromUnitEditText;
        private TextView toUnitLabel;
        private TextView toUnitTextView;

        private String fromUnitString = "Miles";
        private String toUnitString = "Kilometers";
        private float ratio = 1.6093f;

        private float fromValue;
        private float toValue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // get references to the widgets
            conversionSpinner = (Spinner) findViewById(R.id.conversionSpinner);
            fromUnitLabel = (TextView) findViewById(R.id.fromUnitLabel);
            fromUnitEditText = (EditText) findViewById(R.id.fromUnitEditText);
            toUnitLabel = (TextView) findViewById(R.id.toUnitLabel);
            toUnitTextView = (TextView) findViewById(R.id.toUnitTextView);

            // create array adapter for specified array and layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.conversions, android.R.layout.simple_spinner_item);

            // set the layout for the drop-down list
            adapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);

            // set the adapter for the spinner
            conversionSpinner.setAdapter(adapter);

            conversionSpinner.setOnItemSelectedListener((OnItemSelectedListener) this);
            fromUnitEditText.setOnEditorActionListener((OnEditorActionListener) this);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position,
        long id) {

            if (position == 0) {

                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected : " + item, Toast.LENGTH_LONG).show();
                fromUnitString = "Miles";
                toUnitString = "Kilometers";
                ratio = 1.6093f;
            }
            else if (position == 1) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                fromUnitString = "Kilometers";
                toUnitString = "Miles";
                ratio = 0.6214f;
            }
            else if (position == 2) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                fromUnitString = "Inches";
                toUnitString = "Centimeters";
                ratio = 2.54f;
            }
            else if (position == 3) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                fromUnitString = "Centimeters";
                toUnitString = "Inches";
                ratio = 0.3937f;
            }

            fromUnitLabel.setText(fromUnitString);
            toUnitLabel.setText(toUnitString);

            calculateAndDisplay();

            // hide the soft keyboard
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(
                    fromUnitEditText.getWindowToken(), 0);
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No code is needed here
    }

        public void calculateAndDisplay() {

            // get the "from" value
            fromUnitString = fromUnitEditText.getText().toString();
            if (fromUnitString.equals("")) {
                fromValue = 0;
            }
            else {
                fromValue = Float.parseFloat(fromUnitString);
            }

            // calculate the "to" value
            toValue = fromValue * ratio;

            // display the results with formatting
            NumberFormat number = NumberFormat.getNumberInstance();
            number.setMaximumFractionDigits(2);
            number.setMinimumFractionDigits(2);
            toUnitTextView.setText(number.format(toValue));
        }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}
