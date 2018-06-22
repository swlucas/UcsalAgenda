package com.webgnose.ucsalagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserScheduling extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView sc_nome;
    private TextView sc_email;
    private TextView sc_tel;
    private EditText assunto;
    private EditText msg;
    private EditText dia;
    private EditText hora;
    private Button btnAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scheduling);
        firebaseAuth = FirebaseAuth.getInstance();

        findViewsById();
        userInfo();

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                String titulo = assunto.getText().toString();
                String mensagem = msg.getText().toString();
                String horario = dia.getText().toString()+" às "+ hora.getText().toString();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                registerSchenduling(id,titulo,mensagem,horario,user);
            }
        });





    }


    public void userInfo(){

        String nome = getIntent().getStringExtra("nome");
        String email = getIntent().getStringExtra("email");
        String tel = getIntent().getStringExtra("tel");


        sc_nome = findViewById(R.id.sc_nome);
        sc_email = findViewById(R.id.sc_email);
        sc_tel = findViewById(R.id.sc_telefone);

        sc_nome.setText("nome: " + nome);
        sc_email.setText("email: "+email);
        sc_tel.setText("telefone: "+tel);

    }

    private void findViewsById() {

        assunto = findViewById(R.id.sc_assunto);
        msg = findViewById(R.id.sc_msg);
        dia = findViewById(R.id.sc_data);
        hora = findViewById(R.id.sc_hora);

        btnAgendar = findViewById(R.id.sc_btn_agendar);

    }

    public void registerSchenduling(String id,String titulo,String mensagem,String horario,FirebaseUser user){



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refDB = database.child("scheduling").child(user.getUid());

        Agendamento userAgendamento = new Agendamento(user.getEmail(),titulo,mensagem,horario);
        refDB.setValue(userAgendamento);

        Toast toast = Toast.makeText(getApplicationContext(),"Horário agendado com sucesso",Toast.LENGTH_LONG);
        toast.show();

        finish();

    }
}
