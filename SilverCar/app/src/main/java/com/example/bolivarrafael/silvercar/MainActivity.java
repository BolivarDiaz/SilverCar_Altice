package com.example.bolivarrafael.silvercar;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static FirebaseAuth mAuth;
    private EditText textacount;
    private EditText textpass;
    private ProgressDialog progressDialog;
    public static FirebaseUser USER;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();

        findViewById(R.id.buttonlogin).setOnClickListener(this);
        findViewById(R.id.buttonregister).setOnClickListener(this);

        textacount= (EditText) findViewById(R.id.editText);
        textpass= (EditText) findViewById(R.id.editText2);
        progressDialog = new ProgressDialog(this);






    }




    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = mAuth.getCurrentUser();



    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.buttonlogin:
                //logear usuarios ya creados

                String email=textacount.getText().toString().trim();
                String password= textpass.getText().toString().trim();

                progressDialog.setMessage("Conectando");
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    // Sign in success, update UI with the signed-in user's information
                                     FirebaseUser user = mAuth.getCurrentUser();

                                     USER=user;

                                     int loc=user.getEmail().indexOf("@");
                                     String uname=USER.getEmail().substring(0,loc);
                                    Toast.makeText(MainActivity.this,"Welcome "+uname,Toast.LENGTH_LONG).show();

                                    progressDialog.dismiss();

                                Intent i=new Intent(MainActivity.this,MenuActivity.class);
                                startActivity(i);





                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this,"usuario o contraseña incorrecta!",Toast.LENGTH_LONG).show();
                                }

                                progressDialog.dismiss();



                                // ...
                            }
                        });









                break;


            case R.id.buttonregister:
                //crear nuevos usuarios


         Intent i=new Intent(this,RegistrarActivity.class);
         startActivity(i);





                break;





        }




    }






}
