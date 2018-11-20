package com.example.bolivarrafael.silvercar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolivarrafael.silvercar.Models.PublishCar;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddcarActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private boolean imagesubida;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT=1;
    private String img;
    private ProgressDialog progressDialog;
    private Uri uri;
    private StorageReference filepath;

    String uname;
    String pathdelabaina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);

        imagesubida=false;
        TextView username= findViewById(R.id.textViewusername2);

        int loc=MainActivity.USER.getEmail().indexOf("@");

        uname=MainActivity.USER.getEmail().substring(0,loc);

        username.setText(username.getText()+"  "+uname);

        mDatabase=FirebaseDatabase.getInstance().getReference();
        mStorage=FirebaseStorage.getInstance().getReference();
        progressDialog=new ProgressDialog(this);






    }


    public void PublicarCarro(View v){
        EditText etmarca=findViewById(R.id.editTextmarca);
        EditText etmodelo=findViewById(R.id.editTextmodelo);
        EditText etphone=findViewById(R.id.editTextphone);
        EditText etprize=findViewById(R.id.editTextprize);

      final  String marca=etmarca.getText().toString();
       final String modelo=etmodelo.getText().toString();
       final String phone=etphone.getText().toString();
        final String precio=etprize.getText().toString();

        if(marca.equals("")){

            Toast.makeText(this,"Debe ingresar una marca!",Toast.LENGTH_LONG).show();
            return;

        }

        if(modelo.equals("")){

            Toast.makeText(this,"Debe ingresar un modelo!",Toast.LENGTH_LONG).show();
            return;
        }

        if(precio.equals("")){

            Toast.makeText(this,"Debe ingresar el precio del vehiculo en $",Toast.LENGTH_LONG).show();

            return;

        }

        if(phone.equals("")){

            Toast.makeText(this,"Debe ingresar un numero telefonico!",Toast.LENGTH_LONG).show();

            return;

        }





        if(imagesubida){

 final String id=mDatabase.push().getKey();

            progressDialog.setMessage("Registrando vehiculo");
            progressDialog.show();


/*
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagesubida=true;


                        Task<Uri> downUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        //img = downUrl.getResult().toString();


                    PublishCar pcar=new PublishCar(id,marca,modelo,uname,phone,img,precio);
                    mDatabase.child("Carros").child(id).setValue(pcar);

                    progressDialog.dismiss();

                    Toast.makeText(AddcarActivity.this, "Vehiculo registrado!", Toast.LENGTH_SHORT).show();

                }
            });


*/


// alternativo

UploadTask uploadTask;
            final StorageReference ref = mStorage.child("carphotos/"+pathdelabaina) ;
            uploadTask = filepath.putFile(uri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String downloadURL = downloadUri.toString();

                        img = downloadURL;
                        PublishCar pcar=new PublishCar(id,marca,modelo,uname,phone,img,precio);
                        mDatabase.child("Carros").child(id).setValue(pcar);

                        progressDialog.dismiss();

                        Toast.makeText(AddcarActivity.this, "Vehiculo registrado!", Toast.LENGTH_SHORT).show();


                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });


            // hasta aqui






        }else{

            Toast.makeText(this,"Debe ingresar una imagen del vehiculo!",Toast.LENGTH_LONG).show();

        }







    }

    public void subirImagen(View v){


       Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
         startActivityForResult(intent,GALLERY_INTENT);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK ){

           uri=data.getData();

           pathdelabaina= uri.getLastPathSegment();

           filepath=mStorage.child("carphotos").child(pathdelabaina);
            imagesubida=true;
            Toast.makeText(AddcarActivity.this, "Foto cargada", Toast.LENGTH_SHORT).show();






        }

    }
}
