package com.example.disha_school.dishaschool;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Splash_screen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    ImageView imageView;
    GoogleApiClient api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        api = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addOnConnectionFailedListener(this).addConnectionCallbacks(this).build();
        api.connect();
        imageView = (ImageView) findViewById(R.id.img);

        imageView.setAlpha(0.0f);

        final Intent first = new Intent(this, Navigatio_tool.class);

        imageView.animate().alpha(1f).setDuration(2000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.animate().rotation(720f * 2f).setDuration(3000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        startActivity(first);
                        finish();
                    }
                });

            }
        });
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location l = LocationServices.FusedLocationApi.getLastLocation(api);
        new Navigatio_tool().CurrentLatitude = l.getLatitude();
        new Navigatio_tool().CurrentLongitude = l.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addressList = new ArrayList<>();
        try {
            addressList= geocoder.getFromLocation(l.getLatitude(),l.getLongitude(),1);
            if(addressList!=null&&addressList.size()>0){
                Address add  = addressList.get(0);
                if(add.getMaxAddressLineIndex()>0){
                    new Navigatio_tool().address = add.getAddressLine(0)+","+add.getLocality()+add.getCountryName();
                    Log.i("",add.getAddressLine(0)+" "+new Navigatio_tool().address);
                    display(add.getAddressLine(0)+" "+new Navigatio_tool().address);
                }
            }else{
                display("address is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    public void onStart(){
        super.onStart();
        api.connect();
        LocationRequest lr=LocationRequest.create();
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        lr.setInterval(2000);
    }
    private void display(String s){
        //Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
