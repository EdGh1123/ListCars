package com.ed.ListCars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListadoC extends AppCompatActivity {
    RecyclerView ReAutos;
    ArrayList<Carros> ListaAutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

                       ListaAutos = new ArrayList<>();
                       ReAutos = findViewById(R.id.idRcPeliculas);
                                     ReAutos.setLayoutManager(new LinearLayoutManager(this));
                                       Intent Int = getIntent();
                                       ListaAutos = Int.getParcelableArrayListExtra("pelis");
        CarrosA carrosAdapter = new CarrosA(ListaAutos);
        ReAutos.setAdapter(carrosAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        CarrosA carrosAdapter;

        switch (id) {

            case R.id.action_ordenar_nombre:
                Collections.sort(ListaAutos, new Comparator<Carros>() {
                    @Override
                    public int compare(Carros o1, Carros o2) {
                        if (o1.getMarca().compareTo(o2.getMarca()) < 0) {
                            return -1;
                        }
                        if (o1.getMarca().compareTo(o2.getMarca()) > 0) {
                            return 1;
                        }
                        return 0;
                    }
                });
                carrosAdapter = new CarrosA(ListaAutos);
                ReAutos.setAdapter(carrosAdapter);
                break;
            case R.id.action_invertir:
                Collections.reverse(ListaAutos);
                carrosAdapter = new CarrosA(ListaAutos);
                ReAutos.setAdapter(carrosAdapter);
                break;
            case R.id.action_eliminar_aleatorio:
                AlertDialog.Builder builder = new AlertDialog.Builder(ListadoC.this);
                builder.setMessage("Desea eliminar un auto aleatorio?")
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ListaAutos.remove(0);
                                CarrosA carrosAdapter = new CarrosA(ListaAutos);
                                ReAutos.setAdapter(carrosAdapter);
                                Toast.makeText(getApplicationContext(), "Vehiculo removido de la lista", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alerta = builder.create();
                alerta.show();
                break;


            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                i.putParcelableArrayListExtra("pelis", ListaAutos);
                setResult(Activity.RESULT_OK, i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }













}
