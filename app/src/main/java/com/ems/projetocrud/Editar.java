package com.ems.projetocrud;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Editar extends AppCompatActivity  {


    EditText nome, artista, gravadora, ano;
    Button btEditar;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Edição de CD`S");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nome = findViewById(R.id.editNome);
        artista = findViewById(R.id.editArtista);
        gravadora = findViewById(R.id.editGravadora);
        ano = findViewById(R.id.editAno);
        btEditar = findViewById(R.id.btEditar);

        final Intent itCD = getIntent();
        final CD cd = (CD) itCD.getExtras().getSerializable("objCD");
        //id.setText(String.valueOf(aluno.getId()));
        nome.setText(cd.getNome());
        artista.setText(cd.getArtista());
        gravadora.setText(cd.getGravadora());
        ano.setText(cd.getAno());

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ContentValues values = new ContentValues();
                values.put("nome", nome.getText().toString());
                values.put("artista", artista.getText().toString());
                values.put("gravadora", gravadora.getText().toString());
                values.put("ano", ano.getText().toString());

                CD novosDados = new CD();
                novosDados.setNome(nome.getText().toString());
                novosDados.setArtista(artista.getText().toString());
                novosDados.setGravadora(gravadora.getText().toString());
                novosDados.setAno(ano.getText().toString());

                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_cd", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE cd SET " +
                        "nome='" + novosDados.getNome() + "'," +
                        "artista='" + novosDados.getArtista() + "'," +
                        "gravadora='" + novosDados.getGravadora() + "'," +
                        "ano='" + novosDados.getAno() + "' " +
                        "WHERE id=" + cd.getId()
                );

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Mensagem mensagem = new Mensagem(Editar.this);
                mensagem.show(
                        "Dados Atualizados com Sucesso!",
                        novosDados.getDados(),
                        R.drawable.ic_add);
                ;
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
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
