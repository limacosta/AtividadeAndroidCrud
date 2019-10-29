package com.ems.projetocrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


        Button btInserir, btExibir, btPesquisar, btSair;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            //Botão para chamar a View
            btInserir = findViewById(R.id.btAdicionar);
            btInserir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent insert = new Intent(getApplicationContext(), Inserir.class);
                    startActivity(insert);
                }
            });

            //Botão para chamar a View
            btExibir = findViewById(R.id.btExibir);
            btExibir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent insert = new Intent(getApplicationContext(), Exibir.class);
                    startActivity(insert);
                }
            });
            //Botão para chamar a View
            btPesquisar = findViewById(R.id.btPesquisar);
            btPesquisar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent insert = new Intent(getApplicationContext(), Pesquisar.class);
                    startActivity(insert);
                }
            });
            //Botão para fechar a view atual
            btSair = findViewById(R.id.btSair);
            btSair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finishAffinity();
                }
            });
        }
    }

