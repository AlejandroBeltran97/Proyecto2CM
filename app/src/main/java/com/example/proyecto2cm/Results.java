package com.example.proyecto2cm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    ListView lstBooks;

    DatabaseHelper myDB;
    //Button viewData;

    private static final String TAG = "Results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //viewData = (Button) findViewById(R.id.view_data);
        myDB = new DatabaseHelper(this);
        lstBooks = (ListView) findViewById(R.id.lst_items);

        Cursor res = myDB.getAllData();
        Log.d(TAG, "Number of Items: " + res.getCount());
        String[][] data = new String[res.getCount()][7];
        List<Integer> images = new ArrayList<Integer>();
        int indx = 0;
        while(res.moveToNext()){
            data[indx][0] = res.getString(0);
            Log.d(TAG, "ID: " + data[indx][0]);
            data[indx][1] = res.getString(1);
            Log.d(TAG, "TITLE: " + data[indx][1]);
            data[indx][2] = res.getString(2);
            Log.d(TAG, "AUTHOR: " + data[indx][2]);
            data[indx][3] = res.getString(3);
            Log.d(TAG, "EDITORIAL: " + data[indx][3]);
            data[indx][4] = res.getString(4);
            Log.d(TAG, "PLACE: " + data[indx][4]);
            data[indx][5] = res.getString(5);
            Log.d(TAG, "GENRE: " + data[indx][5]);
            switch(data[indx][5]){
                case "Biography":
                case "Biografía":
                case "Biographie":
                    images.add(R.drawable.biography);
                    break;
                case "Childish":
                case "Infantil":
                case "Bébé":
                    images.add(R.drawable.child);
                    break;
                case "Fantasy":
                case "Fantasía":
                case "Fantaisie":
                    images.add(R.drawable.fantasy);
                    break;
                case "History":
                case "Historia":
                case "L'histoire":
                    images.add(R.drawable.history);
                    break;
                case "Languages":
                case "Idiomas":
                case "Langues":
                    images.add(R.drawable.language);
                    break;
                case "Math":
                case "Matemáticas":
                case "Matematiques":
                    images.add(R.drawable.math);
                    break;
                case "Medieval":
                case "Médiéval":
                    images.add(R.drawable.medieval);
                    break;
                case "Nature":
                case "Naturaleza":
                case "La Nature":
                    images.add(R.drawable.nature);
                    break;
                case "Overcoming":
                case "Superación":
                case "Surmonter":
                    images.add(R.drawable.superation);
                    break;
                case "Poetry":
                case "Poesía":
                case "Poésie":
                    images.add(R.drawable.poetry);
                    break;
                case "Research":
                case "Investigación":
                case "Enquête":
                    images.add(R.drawable.research);
                    break;
                case "Romance":
                    images.add(R.drawable.romance);
                    break;
                case "Science":
                case "Ciencia":
                    images.add(R.drawable.science);
                    break;
                case "Terror":
                case "Horreur":
                    images.add(R.drawable.crime);
                    break;
                case "Theater":
                case "Teatro":
                case "Théâtre":
                    images.add(R.drawable.theatre);
                    break;
                case "Thesis":
                case "Tésis":
                case "Thèse":
                    images.add(R.drawable.thesis);
                    break;
            }
            data[indx][6] = res.getString(6);
            Log.d(TAG, "YEAR: " + data[indx][6]);
            indx = indx + 1;
        }

        lstBooks.setAdapter(new AdapterBook(this, data, images));

        //viewAll();
    }

    /*public void viewAll(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount() == 0) {
                    showMessage("Error", "Nothing here");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("TITLE: " + res.getString(1) + "\n");
                    buffer.append("AUTHOR: " + res.getString(2) + "\n");
                    buffer.append("EDITORIAL: " + res.getString(3) + "\n");
                    buffer.append("PLACE: " + res.getString(4) + "\n");
                    buffer.append("GENRE: " + res.getString(5) + "\n");
                    buffer.append("YEAR: " + res.getString(6) + "\n\n");
                }

                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/
}
