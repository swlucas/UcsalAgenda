package com.webgnose.ucsalagenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView mensagem;
    private TextView horario;
    private TextView requerente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        String t = getIntent().getStringExtra("Titulo");
        String h = getIntent().getStringExtra("Horario");
        String m = getIntent().getStringExtra("Mensagem");
        String r = getIntent().getStringExtra("Requerente");


        titulo = findViewById(R.id.titulo);
        mensagem = findViewById(R.id.mensagem);
        horario = findViewById(R.id.horario);
        requerente = findViewById(R.id.requerente);


        titulo.setText(t);
        mensagem.setText(m);
        horario.setText(h);
        requerente.setText(r);  
    }
}
