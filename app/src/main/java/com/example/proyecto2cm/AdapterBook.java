package com.example.proyecto2cm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterBook extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;
    List<Integer> datosIMG;

    public AdapterBook(Context contexto, String[][] datos, List<Integer> imagenes){
        this.contexto = contexto;
        this.datos = datos;
        this.datosIMG = imagenes;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.list_element, null);
        TextView title = (TextView) vista.findViewById(R.id.TitleBook);
        TextView author = (TextView) vista.findViewById(R.id.AuthorBook);
        TextView editorial = (TextView) vista.findViewById(R.id.EditorialBook);
        TextView place = (TextView) vista.findViewById(R.id.PlaceBook);
        TextView gen = (TextView) vista.findViewById(R.id.GenBook);
        TextView year = (TextView) vista.findViewById(R.id.YearBook);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView);

        title.setText(datos[i][1]);
        author.setText(datos[i][2]);
        editorial.setText(datos[i][3]);
        place.setText(datos[i][4]);
        gen.setText(datos[i][5]);
        year.setText(datos[i][6]);

        imagen.setImageResource(datosIMG.get(i));
        imagen.setTag(i);

        return vista;
    }

    @Override
    public int getCount() {
        return datosIMG.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
