package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    Double lat,longt;
    private Button SearchCity;
    private Button GPS;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        SearchCity=(Button)findViewById(R.id.SearchCity);
        SearchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchCityName.class);
                startActivity(intent);

            }
        });

        GPS=(Button)findViewById(R.id.GPS);
        GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED){
                        //get the location
                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {

                                if(location!=null){
                                    lat=location.getLatitude();
                                    longt=location.getLongitude();
                                    Intent intent=new Intent(MainActivity.this,MainWeather.class);
                                    intent.putExtra("lat",location.getLatitude());
                                    intent.putExtra("longt",location.getLongitude());
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                }

        });
    }
}