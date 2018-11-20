package com.example.bolivarrafael.silvercar;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import static com.example.bolivarrafael.silvercar.MainActivity.mAuth;

public class RegistrarActivity extends AppCompatActivity implements  View.OnClickListener{
ProgressDialog progressDialog;
String user;
String pass;
String confpass;
EditText username;
EditText password;
EditText confirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarlayout_activity);





       progressDialog= new ProgressDialog(this);

       findViewById(R.id.botoncrear).setOnClickListener(this);

    }


    public void registrarUser(){

        username=findViewById(R.id.editText3);
        password=findViewById(R.id.editText4);
        confirmpass=findViewById(R.id.editText5);

        user= username.getText().toString().trim();
        pass= password.getText().toString().trim();
        confpass= confirmpass.getText().toString().trim();

// validaciones

        if(user.equals("")) {
            Toast.makeText(this, "Debe ingresar un email!", Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.equals("")) {
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if(pass.length()<6){

            Toast.makeText(this,"La contraseña debe tener 6 caracteres o mas!",Toast.LENGTH_LONG).show();

        }

        if(!pass.equals(confpass)){

            Toast.makeText(this, "Fallo al confirmar la contraseña", Toast.LENGTH_LONG).show();
            return;


        }

        progressDialog.setMessage("Realizando registro en linea");
        progressDialog.show();

        //registrando

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                           Toast.makeText(RegistrarActivity.this,"Se ha registrado: "+user,Toast.LENGTH_LONG).show();

                        } else {


                            if(task.getException() instanceof FirebaseAuthUserCollisionException){

                                Toast.makeText(RegistrarActivity.this,"Esa cuenta ya esta creada!",Toast.LENGTH_LONG).show();

                            }else{

                                Toast.makeText(RegistrarActivity.this,"No se pudo registrar",Toast.LENGTH_LONG).show();

                            }










                        }
                     progressDialog.dismiss();
                    }
                });







    }


    @Override
    public void onClick(View v) {
       // Toast.makeText(this, "pass: "+pass+"  confirmpass: "+confpass, Toast.LENGTH_LONG).show();
        registrarUser();

    }
}
