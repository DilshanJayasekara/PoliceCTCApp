package com.example.policectcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.policectcapp.Util.FirebaseDB;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFirst extends AppCompatActivity {

    EditText txtFname;
    EditText txtLname;
    EditText txtUtype;
    EditText txtNIC;
    EditText txtEmail;
    EditText txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_first);

        Button next1btn = (Button) findViewById(R.id.next1_btn);
        Button cancle = (Button) findViewById(R.id.cancle_btn);
        txtFname = (EditText)findViewById(R.id.txtFirstName);
        txtLname = (EditText)findViewById(R.id.txtLastName);
        txtUtype = (EditText)findViewById(R.id.txtUtype);
        txtNIC = (EditText)findViewById(R.id.txtNIC);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtMobile = (EditText)findViewById(R.id.txtMobile);

        next1btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              // FirebaseDB firebaseDB = new FirebaseDB(RegisterFirst.this);
             // firebaseDB.checkAndCreateUser(RegisterFirst.this, txtFname.getText().toString(), txtLname.getText().toString(), txtOccupation.getText().toString(), txtNIC.getText().toString(), txtEmail.getText().toString(),txtMobile.getText().toString());
                // Do something in response to button click
                Intent intent = new Intent(getBaseContext(), RegisterSecond.class);
                intent.putExtra("fname", txtFname.getText().toString());
                intent.putExtra("lname", txtLname.getText().toString());
                intent.putExtra("Utype", txtUtype.getText().toString());
                intent.putExtra("nic", txtNIC.getText().toString());
                intent.putExtra("email", txtEmail.getText().toString());
                intent.putExtra("mobile", txtMobile.getText().toString());
                startActivity(intent);
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                RegisterFirst.super.finish();
            }
        });

    }
}