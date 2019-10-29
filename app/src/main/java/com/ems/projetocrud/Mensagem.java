package com.ems.projetocrud;


import android.app.AlertDialog;
import android.content.Context;

public class Mensagem {

    private Context _context;


    public Mensagem(Context context) {
        this._context = context;
    }


    public void show(String titulo, final String texto, int icone) {
        AlertDialog.Builder msg = new AlertDialog.Builder(_context);
        msg.setIcon(icone);
        msg.setTitle(titulo);
        msg.setMessage(texto);
        msg.setPositiveButton("OK", null);

        msg.show();
    }
}