package ar.edu.utn.frsf.andini_fpaz.lab03c2016;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AltaTrabajo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_trabajo);
        Button btnCreate = (Button)findViewById(R.id.alta);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tv = (EditText) findViewById(R.id.descripcion);
                Trabajo trabajo = new Trabajo(20, tv.getText().toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("trabajo",trabajo);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
