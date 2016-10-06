package ar.edu.utn.frsf.andini_fpaz.lab03c2016;

import android.content.Context;
import java.text.DecimalFormat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
            CheckBox enIngles = (CheckBox)row.findViewById(R.id.ingles);

            ImageView moneda = (ImageView)row.findViewById(R.id.moneda);
            String precio = df.format(trabajo.getPrecioMaximoHora());
            SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yy");
            String textoHorasPrecio = String.format("Horas: %d Max $/hora: %s", trabajo.getHorasPresupuestadas(), precio);
            String textoFecha = String.format("Fecha Fin: %s",datef.format(trabajo.getFechaEntrega()));

            categoria.setText(trabajo.getCategoria().getDescripcion());
            descripcion.setText(trabajo.getDescripcion());
            horasPrecio.setText(textoHorasPrecio);
            fecha.setText(textoFecha);
            enIngles.setChecked(trabajo.getRequiereIngles());

            int icono = R.drawable.ar;
            //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
            switch (trabajo.getMonedaPago()){
                case 1:
                    icono = R.drawable.us;
                    break;
                case 2:
                    icono = R.drawable.eu;
                    break;
                case 3:
                    icono = R.drawable.ar;
                    break;
                case 4:
                    icono = R.drawable.uk;
                    break;
                case 5:
                    icono = R.drawable.br;
                    break;
            }

            moneda.setImageResource(icono);
            row.setLongClickable(true);
            return(row);
        }


    }

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.trabajos);
        final Trabajo[] trabajos = Trabajo.TRABAJOS_MOCK;
        MiAdapter adapter = new MiAdapter(getApplicationContext(), Arrays.asList(trabajos));
        lv.setAdapter(adapter);
        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                toast(trabajos[position].getDescripcion());
                return true;
            }
        });
        Intent i = new Intent(this, AltaTrabajo.class);
        startActivityForResult(i,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Trabajo trabajo = (Trabajo) data.getSerializableExtra("trabajo");
        toast(trabajo.getDescripcion());
    }

    private void toast(String mensaje){
        Toast toast = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast.show();
    }

}
