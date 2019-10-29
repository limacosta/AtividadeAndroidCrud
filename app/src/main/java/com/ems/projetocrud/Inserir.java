package com.ems.projetocrud;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Inserir extends AppCompatActivity {

    EditText nome, artista, gravadora, ano;
    Button btInserir;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir);

        //Cria ou abre o Banco db_cd
        db = openOrCreateDatabase("db_cd", Context.MODE_PRIVATE, null);

        //Cria as Tabelas no Banco
        db.execSQL("CREATE TABLE IF NOT EXISTS cd(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR NOT NULL, " +
                "artista VARCHAR NOT NULL, " +
                "gravadora VARCHAR NOT NULL, " +
                "ano VARCHAR NOT NULL);");

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Inserir CD`S");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Busca os dados do editbox e insere nas variaveis
        nome = findViewById(R.id.editNome);
        artista = findViewById(R.id.editArtista);
        gravadora = findViewById(R.id.editGravadora);
        ano = findViewById(R.id.editAno);
        btInserir = findViewById(R.id.btEditar);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instância um objeto da classe CD
                CD cd = new CD();

                cd.setNome(nome.getText().toString());
                cd.setArtista(artista.getText().toString());
                cd.setGravadora(gravadora.getText().toString());
                cd.setAno(ano.getText().toString());

                //Cria um Content e adiciona valores a ela
                ContentValues values = new ContentValues();
                values.put("Nome", cd.getNome());
                values.put("Artista", cd.getArtista());
                values.put("Gravadora",cd.getGravadora());
                values.put("Ano", cd.getAno());

                //Insere os dados na tabela
                db.insert("cd", null, values);

                //Mensagem de confirmação
                Mensagem mensagem = new Mensagem(Inserir.this);
                mensagem.show(
                        "Dados incluídos com sucesso!",
                        cd.getDados(),
                        R.drawable.ic_add);


                clearText();
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

    //Limpas os dados dos campos
    public void clearText() {
        nome.setText("");
        artista.setText("");
        gravadora.setText("");
        ano.setText("");
        nome.requestFocus();


        ((InputMethodManager) Inserir.this.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }

}
