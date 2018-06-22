package com.webgnose.ucsalagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetalhe extends AppCompatActivity {


    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detalhe);

        ListView listView = this.findViewById(R.id.list_view_agenda);

        final ArrayList<Agendamento> agenda = new ArrayList<Agendamento>();
        final ArrayAdapter<Agendamento> adapter = new ArrayAdapter<Agendamento>(this,android.R.layout.simple_list_item_1,agenda);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(UserDetalhe.this,DetalhesActivity.class);

                intent.putExtra("Titulo",agenda.get(i).getTitulo());
                intent.putExtra("Mensagem",agenda.get(i).getMensagem());
                intent.putExtra("Requerente",agenda.get(i).getRequerente());
                intent.putExtra("Horario",agenda.get(i).getHorario());
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        DatabaseReference refDb = refDB.child("scheduling");


        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                Log.i("teste",dataSnapshot.getValue().toString());
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Agendamento agendamento = data.getValue(Agendamento.class);
                    adapter.add(agendamento);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
