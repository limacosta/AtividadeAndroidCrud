package com.ems.projetocrud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;




import java.util.ArrayList;

public class Exibir extends AppCompatActivity {

    ListView Lista;
    ArrayList<CD> cd = new ArrayList<>();
    ArrayAdapter<CD> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Lista de CD`S");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //Cria ou abre o Banco db_cd
        db = openOrCreateDatabase("db_cd", Context.MODE_PRIVATE, null);

        Lista = findViewById(R.id.listagem);

        //Busca os dados na tabela e ordena em ordem alfabética
        cd.clear();
        Cursor c = db.rawQuery("SELECT * FROM cd ORDER BY nome ASC", null);
        while (c.moveToNext()) {
            cd.add(new CD(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
        }


        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                cd);



        Lista.setAdapter(adaptador);

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CD cd = (CD) Lista.getItemAtPosition(position);
                Intent itCD = new Intent(getApplicationContext(), Detalhes.class);
                itCD.putExtra("objCD", cd);
                startActivity(itCD);
            }
        });
    }


    // Configura o botão (seta) na ActionBar (Barra Superior)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}