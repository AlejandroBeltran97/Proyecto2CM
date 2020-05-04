package com.example.proyecto2cm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

    TextInputEditText titleText;
    TextInputEditText authorText;
    TextInputEditText editorialText;
    TextInputEditText placeText;

    TextInputLayout titleLayout;
    TextInputLayout authorLayout;
    TextInputLayout editorialLayout;
    TextInputLayout placeLayout;

    Button btnAdd;
    Button visData;
    ImageButton closePop;

    Dialog dataAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bgBooks = (BlurImageView) findViewById(R.id.bgBooks);
        bgBooks.setBlur(5);

        titleText = (TextInputEditText) findViewById(R.id.titleBookEdit);
        authorText = (TextInputEditText) findViewById(R.id.authorBookEdit);
        editorialText = (TextInputEditText) findViewById(R.id.editorialBookEdit);
        placeText = (TextInputEditText) findViewById(R.id.placeBookEdit);

        titleLayout = (TextInputLayout) findViewById(R.id.titleBookLayout);
        authorLayout = (TextInputLayout) findViewById(R.id.authorBookLayout);
        editorialLayout = (TextInputLayout) findViewById(R.id.editorialBookLayout);
        placeLayout = (TextInputLayout) findViewById(R.id.placeBookLayout);

        btnAdd = (Button) findViewById(R.id.btn_addData);
        visData = (Button) findViewById(R.id.vis_data);

        dataAdded = new Dialog(this);

        spinnerGen = (Spinner) findViewById(R.id.spin_gen);
        spinnerYear = (Spinner) findViewById(R.id.spin_year);

        myDB = new DatabaseHelper(this);
        myDB.deleteAll();

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

        validateData();
        resButton();

    }

    public void validateData(){
        dataAdded.setContentView(R.layout.custompopup);
        closePop = (ImageButton) dataAdded.findViewById(R.id.close_popup);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(titleText.getText())){
                    titleLayout.setError(getString(R.string.alert_field));
                    titleLayout.setErrorEnabled(true);
                }
                else{
                    titleLayout.setErrorEnabled(false);
                    if(TextUtils.isEmpty(authorText.getText())){
                        authorLayout.setError(getString(R.string.alert_field));
                        authorLayout.setErrorEnabled(true);
                    }
                    else{
                        authorLayout.setErrorEnabled(false);
                        if(TextUtils.isEmpty(editorialText.getText())){
                            editorialLayout.setError(getString(R.string.alert_field));
                            editorialLayout.setErrorEnabled(true);
                        }
                        else{
                            editorialLayout.setErrorEnabled(false);
                            if(TextUtils.isEmpty(placeText.getText())){
                                placeLayout.setError(getString(R.string.alert_field));
                                placeLayout.setErrorEnabled(true);
                            }
                            else{
                                placeLayout.setErrorEnabled(false);
                                boolean isInserted = myDB.insertData(titleText.getText().toString(),
                                        authorText.getText().toString(),
                                        editorialText.getText().toString(),
                                        placeText.getText().toString(),
                                        spinnerGen.getSelectedItem().toString(),
                                        spinnerYear.getSelectedItem().toString());
                                closePop.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dataAdded.dismiss();
                                    }
                                });
                                dataAdded.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dataAdded.show();
                                /*if(isInserted == true)
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();*/
                            }
                        }
                    }
                }
            }
        });
    }

    private void resButton(){
        visData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Results.class));
            }
        });
    }

    /*public void validate(View view){
        String error = null;
        if(TextUtils.isEmpty(titleText.getText()))
            error = getString(R.string.alert_field);
        toggleTextInputLayoutError(titleLayout, error);

        clearFocus();
    }

    private static void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                   String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }

    private void clearFocus() {
        View view = this.getCurrentFocus();
        if (view != null && view instanceof TextInputEditText) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
