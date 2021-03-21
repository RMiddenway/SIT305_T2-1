package com.rodnog.rogermiddenway.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public enum units {
        METRES,
        CELSIUS,
        KILOGRAMS
    };

    public units currentUnit;
    public Spinner unitSelectSpinner;
    public EditText input;

    public TextView result1TextView;
    public TextView result2TextView;
    public TextView result3TextView;

    public TextView unit1TextView;
    public TextView unit2TextView;
    public TextView unit3TextView;

    public ImageButton metresButton;
    public ImageButton celsiusButton;
    public ImageButton kilogramsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUnit = units.METRES;
        unitSelectSpinner = findViewById(R.id.unitSelectSpinner);
        input = findViewById(R.id.input);

        metresButton = findViewById(R.id.metresButton);
        celsiusButton = findViewById(R.id.celsiusButton);
        kilogramsButton = findViewById(R.id.kilogramsButton);

        result1TextView = findViewById(R.id.result1TextView);
        result2TextView = findViewById(R.id.result2TextView);
        result3TextView = findViewById(R.id.result3TextView);

        unit1TextView = findViewById(R.id.unit1TextView);
        unit2TextView = findViewById(R.id.unit2TextView);
        unit3TextView = findViewById(R.id.unit3TextView);

        unitSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                currentUnit = units.values()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @SuppressLint("DefaultLocale")
    public void calculate (View view) {
        if(input.getText().toString().matches("")){
            Toast.makeText(this, "First enter a value", Toast.LENGTH_SHORT).show();
        }

        else if (!view.getTag().toString().equals( currentUnit.name())){
            Toast.makeText(this, "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
        }

        else {
            DecimalFormat df = new DecimalFormat("#.##");
            switch(view.getTag().toString()) {
                case "CELSIUS":
                    unit1TextView.setText("Fahrenheit");
                    unit2TextView.setText("Kelvin");
                    unit3TextView.setText("");

                    result1TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 1.8 + 32));
                    result2TextView.setText(df.format(Double.parseDouble(input.getText().toString()) + 273.15));
                    result3TextView.setText("");

                    break;
                case "KILOGRAMS":
                    unit1TextView.setText("Grams");
                    unit2TextView.setText("Ounces");
                    unit3TextView.setText("Pounds");

                    result1TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 1000));
                    result2TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 35.274));
                    result3TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 2.205));

                    break;
                case "METRES":
                    unit1TextView.setText("Centimetres");
                    unit2TextView.setText("Feet");
                    unit3TextView.setText("Inches");

                    result1TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 100));
                    result2TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 3.281));
                    result3TextView.setText(df.format(Double.parseDouble(input.getText().toString()) * 39.37));

                    break;
                default:
                    break;
            }
        }
    }
}