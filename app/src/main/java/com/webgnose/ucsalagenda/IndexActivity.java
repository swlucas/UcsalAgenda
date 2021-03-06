package com.webgnose.ucsalagenda;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class IndexActivity extends AppCompatActivity {

    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth;
    private Button btn_detalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        btn_detalhe = findViewById(R.id.btn_detalhe);
        ListView listView = this.findViewById(R.id.list_view_users);

        final ArrayList<User> users = new ArrayList<User>();
        final ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,users);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
                Intent intent = new Intent(IndexActivity.this,UserScheduling.class);
//                intent.putExtra("user_id",user.getUid());
                intent.putExtra("id",users.get(i).getId());
                intent.putExtra("nome",users.get(i).getNome());
                intent.putExtra("email",users.get(i).getEmail());
                intent.putExtra("tel",users.get(i).getTelefone());
//                intent.putExtra("userP", (Parcelable) users.get(i));
//                intent.putExtra("userS", (Serializable) users.get(i));
                startActivity(intent);
            }
        });


        DatabaseReference refDb = refDB.child("users");

        refDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                Log.i("teste",dataSnapshot.getValue().toString());
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);
                    adapter.add(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_detalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this,UserDetalhe.class);
                startActivity(intent);
            }
        });

    }

}
