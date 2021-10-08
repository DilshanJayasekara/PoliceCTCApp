package com.example.policectcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQRCode extends AppCompatActivity {
    Button generateBtn;
    ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);

        generateBtn = (Button) findViewById(R.id.generatebtn);
        qrImage = (ImageView) findViewById(R.id.imageQR);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "962660142v";
                QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT,100);
                try {
                    // Getting QR-Code as Bitmap
                    Bitmap bitmap = qrgEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    qrImage.setImageBitmap(bitmap);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

    }
}