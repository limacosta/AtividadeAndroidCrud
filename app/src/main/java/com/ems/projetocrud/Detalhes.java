package com.ems.projetocrud;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Detalhes extends AppCompatActivity {

    Button btEditar,btExcluir;
    TextView id,id1,nome,artista,gravadora,ano;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Detalhes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = openOrCreateDatabase("db_cd", Context.MODE_PRIVATE, null);
        id = findViewById(R.id.editId);
        id1 = findViewById(R.id.editId);
        nome = findViewById(R.id.editNome);
        artista = findViewById(R.id.editArtista);
        gravadora = findViewById(R.id.editGravadora);
        ano = findViewById(R.id.editAno);
        btEditar = findViewById(R.id.btEditar);
        btExcluir = findViewById(R.id.btExcluir);

        Intent itCD = getIntent();


        final CD cd = (CD) itCD.getExtras().getSerializable("objCD");
        id.setText(String.valueOf(cd.getId()));
        nome.setText(cd.getNome());
        artista.setText(cd.getArtista());
        gravadora.setText(cd.getGravadora());
        ano.setText(cd.getAno());

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(getApplicationContext(), Editar.class);
                editar.putExtra("objCD", cd);
                startActivity(editar);
            }
        });
        //Cria um botão excluir que a partir do ID exclui o dado selecionado
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int identificador = Integer.parseInt(id1.getText().toString());
                //int identificador =4;
                try {
                    db.execSQL("DELETE FROM cd WHERE ID =" +identificador);
                    //db.delete("db_cd", id+"=" + identificador, null);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                Intent insert = new Intent(getApplicationContext(), Pesquisar.class);
                startActivity(insert);

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