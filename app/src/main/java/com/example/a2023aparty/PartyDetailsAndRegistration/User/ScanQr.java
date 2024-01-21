package com.example.a2023aparty.PartyDetailsAndRegistration.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2023aparty.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Arrays;

public class ScanQr extends AppCompatActivity {

    EditText CodeName;
    ImageView QRCode;
    Button GenerateButton, BackQR;

    TextView NameText, PhoneText, TimeText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        //CodeName=findViewById(R.id.CodeName);

        GenerateButton=findViewById(R.id.GenerateButton);

        QRCode=findViewById(R.id.QRCode);

        CodeName=findViewById(R.id.CodeName);

        NameText=findViewById(R.id.NameText);
        PhoneText=findViewById(R.id.PhoneText);
        TimeText=findViewById(R.id.TimeText);


        Userinfo(NameText,PhoneText,TimeText);


        BackQR=findViewById(R.id.BackQR);
        BackQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScanQr.this, UserAttendConfirmation.class));
            }
        });



        GenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try {

                    String codename = CodeName.getText().toString();

                    if (!codename.isEmpty())
                    {

                        BitMatrix bitMatrix = multiFormatWriter.encode(Arrays.toString(Userinfo(NameText,PhoneText,TimeText)), BarcodeFormat.QR_CODE,300,300);

                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                        QRCode.setImageBitmap(bitmap);
                    }
                    else {
                        Toast.makeText(ScanQr.this, "Please Enter a Name for the QR Code", Toast.LENGTH_SHORT).show();
                    }



                }catch (WriterException e){
                    throw new RuntimeException(e);
                }
            }
        });





    }

    private String[] Userinfo(TextView nameText, TextView phoneText, TextView timeText) {
        String Name = getIntent().getStringExtra("keyname");
        String Telephone = getIntent().getStringExtra("keytele");
        String Time = getIntent().getStringExtra("keytime");


        NameText.setText(Name);
        PhoneText.setText(Telephone);
        TimeText.setText(Time);

        String[] User = new String[]{Name, Telephone, Time};

        return User;
    }
}