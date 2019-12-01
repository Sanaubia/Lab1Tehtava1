package com.example.lab1tehtava1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   private FusedLocationProviderClient mFusedLocationClient;




   private TextView teksti;
   private TextView teksti2;
   private TextView teksti3;
   private int persmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        this.teksti = (TextView) findViewById(R.id.teksti);
        this.teksti2 = (TextView) findViewById(R.id.teksti2);
        this.teksti3 = (TextView) findViewById(R.id.teksti3);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, persmission);
            }
        }

        else {
        ;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.d ("Location: ", location.toString ( ));

                    Geocoder geo = new Geocoder (MainActivity.this, Locale.getDefault ( ));

                    double lLong = location.getLongitude ( );
                    double lat = location.getLatitude ( );

                    try {
                        List<Address> osoitteet = geo.getFromLocation (lat, lLong, 1);
                        Address testi = osoitteet.get(0);
                        String kaupunki = testi.getAdminArea ();
                        String maa = testi.getCountryName ();

                        teksti.setText ("lat = " + String.format ("%.2f", lat) + "   " + "long = " + String.format ("%.2f", lLong));
                        teksti2.setText (kaupunki);
                        teksti3.setText (maa);

                        //lokaatio = String.valueOf (geo.getFromLocation (lat, lLong, 1));
                        //Log.d ("LOKAATIO", osoitteet.toString ());
                    } catch (IOException e) {
                        e.printStackTrace ( );
                    }



                }
            }
        });
    }



}

