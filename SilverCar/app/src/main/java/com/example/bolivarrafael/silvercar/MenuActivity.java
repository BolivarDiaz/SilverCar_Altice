package com.example.bolivarrafael.silvercar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        username= findViewById(R.id.textviewusername);

        int loc=MainActivity.USER.getEmail().indexOf("@");

        String uname=MainActivity.USER.getEmail().substring(0,loc);

        username.setText(username.getText().toString()+"  "+uname);



    }


    public void verCars(View v){

Intent i=new Intent(this,VercarrosActivity.class);
startActivity(i);


    }

    public void addCars(View v){

        Intent i =new Intent(this,AddcarActivity.class);
        startActivity(i);


    }

}
