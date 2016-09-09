package ar.edu.utn.frsf.andini_fpaz.lab03c2016;

import android.content.Context;
import java.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public class MiAdapter extends ArrayAdapter<Trabajo> {
        LayoutInflater inflater;
        MiAdapter(Context context, List<Trabajo> items) {
            super(context, R.layout.row1, items);
            inflater = LayoutInflater.from(context);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            DecimalFormat df = new DecimalFormat("#.##");
            View row = inflater.inflate(R.layout.row1, parent, false);
            Trabajo trabajo = this.getItem(position);

            TextView categoria = (TextView)row.findViewById(R.id.categoria);
            TextView descripcion = (TextView)row.findViewById(R.id.descripcion);
            TextView horasPrecio = (TextView)row.findViewById(R.id.horasPrecio);
            TextView fecha = (TextView)row.findViewById(R.id.fecha);

            ImageView moneda = (ImageView)row.findViewById(R.id.moneda);

            String textoHorasPrecio = "";
            String textoFecha = "";

            categoria.setText(trabajo.getCategoria().getDescripcion());

            return(row);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
