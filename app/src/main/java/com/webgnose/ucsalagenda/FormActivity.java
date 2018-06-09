package com.webgnose.ucsalagenda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextTelefone;
    private EditText editTextNome;

    private Button btnCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        findViewsById();

        firebaseAuth = FirebaseAuth.getInstance();

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void findViewsById() {

        editTextEmail = findViewById(R.id.form_email);
        editTextPassword = findViewById(R.id.form_pass);
        editTextTelefone = findViewById(R.id.form_tel);
        editTextNome = findViewById(R.id.form_nome);

        btnCadastra = findViewById(R.id.form_btn_cadastrar);

    }

    public void cadastrar(){

        final String nome = editTextNome.getText().toString();
        final String telefone = editTextTelefone.getText().toString();
        final String senha = editTextPassword.getText().toString();
        final String email = editTextEmail.getText().toString();


        firebaseAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //TODO:Tela de logar
                            writeData(nome,telefone,email,user);
                            startActivity(new Intent(FormActivity.this, IndexActivity.class));

                        }

                    }
                });

    }

    public void writeData(String nome, String telefone, String email, FirebaseUser user){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refDB = database.getReference();

        refDB.child("users").child(user.getUid());

        User userModel = new User(nome,email,telefone);

        refDB.setValue(userModel);


    }

}
