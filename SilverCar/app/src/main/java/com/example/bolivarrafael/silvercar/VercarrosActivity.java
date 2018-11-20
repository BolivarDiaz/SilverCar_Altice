package com.example.bolivarrafael.silvercar;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.bolivarrafael.silvercar.Adapters.AdapterCars;
import com.example.bolivarrafael.silvercar.Models.PublishCar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VercarrosActivity extends AppCompatActivity {
DatabaseReference mdbReference;
TextView txtmarca;
    AdapterCars adapter;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog progressDialog;

    RecyclerView rv;
    ArrayList listacar=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcar);

      progressDialog=new ProgressDialog(this);
        txtmarca=findViewById(R.id.textViewmarca);
        mdbReference=FirebaseDatabase.getInstance().getReference();


       rv = (RecyclerView) findViewById(R.id.reciclador);
        rv.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        mLayoutManager= new LinearLayoutManager(this);

        rv.setLayoutManager(mLayoutManager);


        progressDialog.setMessage("Cargando datos");
        progressDialog.show();

        mdbReference.child("Carros").addValueEventListener(new ValueEventListener() {
                                                              @Override
                                                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                  for(final DataSnapshot snapshot: dataSnapshot.getChildren()){

                                                                  mdbReference.child("Carros").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                                                                      @Override
                                                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                          PublishCar pc=snapshot.getValue(PublishCar.class);




                                                                          llenarlista(pc);



                                                                      }

                                                                      @Override
                                                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                      }
                                                                  });



                                                                  }

                                                                  adapter=new AdapterCars(listacar,VercarrosActivity.this);
                                                                  rv.setAdapter(adapter);


                                                                  progressDialog.dismiss();
                                                              }

                                                              @Override
                                                              public void onCancelled(@NonNull DatabaseError databaseError) {

                                                              }
                                                          });













    }


    public void llenarlista(PublishCar car){

listacar.add(car);





    }




}
