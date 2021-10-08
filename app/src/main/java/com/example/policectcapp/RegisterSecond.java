package com.example.policectcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class RegisterSecond extends AppCompatActivity {

    EditText txtvehicleType;
    EditText txtvehiclNumber;
    DatePicker from;
    DatePicker to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_second);
        Button nextbtn = (Button) findViewById(R.id.next1_btn);
        Button backbtn = (Button)findViewById(R.id.back_btn);
        txtvehicleType = (EditText)findViewById(R.id.txtVehicleType) ;
        txtvehiclNumber = (EditText)findViewById(R.id.txtVehicleNumber);
        from = (DatePicker) findViewById(R.id.datePickerFrom);
        to = (DatePicker) findViewById(R.id.datePickerTo);
        int day = from.getDayOfMonth();
        int month = from.getMonth() + 1;
        int year = from.getYear();
        String fromDate = day + "/" + month + "/" + year;
        day = to.getDayOfMonth();
        month = to.getMonth() + 1;
        year = to.getYear();
        String toDate = day + "/" + month + "/" + year;

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterLast.class);
                intent.putExtra("fname", getIntent().getStringExtra("fname"));
                intent.putExtra("lname", getIntent().getStringExtra("lname"));
                intent.putExtra("Utype", getIntent().getStringExtra("Utype"));
                intent.putExtra("nic", getIntent().getStringExtra("nic"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("mobile", getIntent().getStringExtra("mobile"));
                intent.putExtra("vehicleType", txtvehicleType.getText().toString());
                intent.putExtra("vehicleNumber", txtvehiclNumber.getText().toString());
                intent.putExtra("fromDate", fromDate);
                intent.putExtra("toDate",toDate);
                startActivity(intent);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}