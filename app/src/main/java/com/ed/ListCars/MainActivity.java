package com.ed.ListCars;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private  int dia,mes,ano;
    ArrayList<Carros> ListaPelisArr = new ArrayList<>();
    Button btG, btC, btfecha;
    String GeneroPelicula; RadioGroup radioGroup;

    TextInputEditText ENom, EdD;
    RadioButton OpEs, OpEn;
    EditText edfecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                                  radioGroup = findViewById(R.id.radioGroup);
                                  EdD = findViewById(R.id.idEdPlaca);

                                  ENom = findViewById(R.id.idEdNombreAuto);
                                  OpEs = findViewById(R.id.BoEs);
                                  btC = findViewById(R.id.idBtCancelar);
                                  OpEn = findViewById(R.id.BoEn);
                                  btG = findViewById(R.id.idBtGuardar);
        btfecha=(Button)findViewById(R.id.bfecha);
        edfecha=(EditText)findViewById(R.id.efecha);
        btfecha.setOnClickListener(this);
        btG.setOnClickListener(this);
        btC.setOnClickListener(this);
    }









    @Override
    public void onClick(View v) {



        switch (v.getId()) {
            case R.id.idBtGuardar:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Quiere Guardar esta Vehiculo?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idioma;
                        switch (radioGroup.getCheckedRadioButtonId()) {
                            case R.id.BoEs:
                                idioma = getString(R.string.mecanica);
                                break;
                            case R.id.BoEn:
                                idioma = getString(R.string.Automatica);
                                break;
                            default:
                                idioma = getString(R.string.Automatica);
                                break;
                        }
                        Carros carros = new Carros(ENom.getText().toString(), EdD.getText().toString(), idioma, edfecha.getText().toString());
                        ListaPelisArr.add(carros);
                        ENom.setText("");
                        EdD.setText("");
                        edfecha.setText("");
                        Toast.makeText(getApplicationContext(), "Vehiculo Guardada a la lista", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;
            case R.id.idBtCancelar:
                ENom.setText("");
                EdD.setText("");
                edfecha.setText("");
                break;
        }

        if(v==btfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edfecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_listado:
                Intent i = new Intent(this, ListadoC.class);
                i.putParcelableArrayListExtra("pelis", ListaPelisArr);
                startActivityForResult(i, 5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            ListaPelisArr = data.getParcelableArrayListExtra("pelis");

        }

    }
}
