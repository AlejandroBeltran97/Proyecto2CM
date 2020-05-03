package com.example.proyecto2cm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jgabrielfreitas.core.BlurImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BlurImageView bgBooks;
    Spinner spinnerGen;
    Spinner spinnerYear;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bgBooks = (BlurImageView) findViewById(R.id.bgBooks);
        bgBooks.setBlur(5);

        spinnerGen = (Spinner) findViewById(R.id.spin_gen);
        spinnerYear = (Spinner) findViewById(R.id.spin_year);

        myDB = new DatabaseHelper(this);

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 1900; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }
        Collections.reverse(years);

        ArrayAdapter<String> yearsAdapter = new ArrayAdapter<String>(this, R.layout.color_spinner_layout, years);
        yearsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinnerYear.setAdapter(yearsAdapter);

        String languageName = Locale.getDefault().getDisplayLanguage();

        ArrayAdapter genAdapterEN = ArrayAdapter.createFromResource(
                this,
                R.array.items_en,
                R.layout.color_spinner_layout
        );
        ArrayAdapter genAdapterES = ArrayAdapter.createFromResource(
                this,
                R.array.items_es,
                R.layout.color_spinner_layout
        );
        ArrayAdapter genAdapterFR = ArrayAdapter.createFromResource(
                this,
                R.array.items_fr,
                R.layout.color_spinner_layout
        );
        switch(languageName){
            case "English":
                genAdapterEN.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                spinnerGen.setAdapter(genAdapterEN);
                spinnerGen.setOnItemSelectedListener(this);
                break;
            case "Spanish":
                genAdapterES.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                spinnerGen.setAdapter(genAdapterES);
                spinnerGen.setOnItemSelectedListener(this);
                break;
            case "French":
                genAdapterFR.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                spinnerGen.setAdapter(genAdapterFR);
                spinnerGen.setOnItemSelectedListener(this);
                break;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
