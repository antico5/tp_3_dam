package ar.edu.utn.frsf.andini_fpaz.lab03c2016;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Vector;

public class AltaTrabajo extends AppCompatActivity {
    Spinner spinner;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_trabajo);
        Button btnCreate = (Button)findViewById(R.id.alta);

        Vector<String> arraySpinner = new Vector<String>();

        for(Categoria categoria : Categoria.CATEGORIAS_MOCK){
            arraySpinner.add(categoria.getDescripcion());
        }
        spinner = (Spinner)findViewById(R.id.categoriasSpinner);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinner.setAdapter(adapter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tv = (EditText) findViewById(R.id.descripcion);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);

                Categoria categoria = Categoria.CATEGORIAS_MOCK[spinner.getSelectedItemPosition()];

                Trabajo trabajo = new Trabajo(20, tv.getText().toString());
                trabajo.setMonedaPago(idx + 1 );
                trabajo.setCategoria(categoria);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("trabajo",trabajo);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
