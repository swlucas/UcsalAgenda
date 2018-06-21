package com.webgnose.ucsalagenda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private Button btnAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        findViewsById();

        firebaseAuth = FirebaseAuth.getInstance();


        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acessar();
            }
        });


        TextView tv_cadastrar = findViewById(R.id.tv_cadastrar);
        tv_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,FormActivity.class);
                startActivity(i);
            }
        });


        TextView tv_forget = findViewById(R.id.tv_esqueceuSenha);
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(i);
            }
        });


    }

    private void findViewsById() {

        editTextEmail = findViewById(R.id.tp_email);
        editTextPassword = findViewById(R.id.tp_senha);

        btnAcessar = findViewById(R.id.btn_login);

    }

    private void acessar(){

        final String email = editTextEmail.getText().toString();
        final String senha = editTextPassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            FirebaseUser user = firebaseAuth.getCurrentUser();

                         Intent indexActivity =  new Intent(LoginActivity.this, IndexActivity.class);
                                indexActivity.putExtra("user_id",user.getUid());
                            startActivity(indexActivity);
                            finish();

                        }

                    }
                });


    }
}
