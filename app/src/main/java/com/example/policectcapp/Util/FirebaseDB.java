package com.example.policectcapp.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.andrognito.flashbar.Flashbar;
import com.andrognito.flashbar.anim.FlashAnim;
import com.example.policectcapp.MainActivity;
import com.example.policectcapp.R;
import com.example.policectcapp.RegisterSecond;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FirebaseDB {

    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageReference uploadFileReferenace;
    AppConfig appConfig;
    User user;
    Location location;
    Context context;
    Dialog dialog;
    Vibrator vibrate;
    int hw_slots = 0;
    private GoogleMap mMap;

    public FirebaseDB(Context context) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        this.context = context;
        vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        appConfig = new AppConfig(context);
    }

    private void showProgressDialog() {
        dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.loading_view);
        dialog.show();
        dialog.setCancelable(false);
    }

    private void dismissProgressDialog() {
        if (dialog != null)
            dialog.cancel();
    }
    public void checkAndCreateUser(final Context context, final String fname, final String lname, final String userType, final String NIC, final String email, final String phone, final String vehicleType, final String vehicleNumber, final String fromDate, final String toDate) {

        showProgressDialog();

        user = new User(fname,lname,userType, NIC, email, phone, vehicleType, vehicleNumber, fromDate, toDate);
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("users").child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null)
                    createNewUser(context, phone);
                else {
                    showAlertDialog("User Already Exists. Please Login").show();
//                    Toast.makeText(context,"User Already Exists",Toast.LENGTH_LONG).show();
                    vibrate.vibrate(100);
                    dismissProgressDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dismissProgressDialog();
            }
        });

    }
    public void createNewUser(final Context context, String phone) {
        databaseReference.child("users").child(phone).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("FIREBASE", "USER DATA ADDED");
                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show();
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finish();
                } else
                    Log.i("FIREBASE", "FAILED TO ADD USER");

                dismissProgressDialog();
            }
        });
    }
    public void createNewLocation(String phone) {
        databaseReference.child("location").child(phone).setValue(location).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("FIREBASE", "USER DATA ADDED");
                } else
                    Log.i("FIREBASE", "FAILED TO ADD USER");
            }
        });
    }
    public void checkAndCreateLocation(final String phone, final String lontitude, final String latitude ) {

        showProgressDialog();

       location = new Location(phone, lontitude, latitude);
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("location").child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null)
                    createNewLocation(phone);
                else {
                    showAlertDialog("User Already Exists. Please Login").show();
//                    Toast.makeText(context,"User Already Exists",Toast.LENGTH_LONG).show();
                    vibrate.vibrate(100);
                    dismissProgressDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dismissProgressDialog();
            }
        });

    }


    private Flashbar showAlertDialog(String message) {
        return new Flashbar.Builder((Activity) context)
                .gravity(Flashbar.Gravity.TOP)
                .duration(1000)
                .message(message)
                .messageColor(ContextCompat.getColor(context, R.color.white))
                .backgroundColor(ContextCompat.getColor(context, R.color.errorMessageBackgroundColor))
                .showIcon()
                .iconColorFilterRes(R.color.errorMessageIconColor)
                .icon(R.drawable.ic_cross)
                .enterAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(200)
                        .slideFromLeft()
                        .overshoot())
                .exitAnimation(FlashAnim.with(context)
                        .animateBar()
                        .duration(1000)
                        .slideFromLeft()
                        .accelerate())
                .build();
    }
}
