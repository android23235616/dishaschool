package com.example.disha_school.dishaschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class Map_google extends AppCompatActivity {
    int placepicker_rqst=1;
static double lat_dest,lon_dest;//so that we can access that variable anywhere in the program
   LatLng latLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_google);

        PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this),placepicker_rqst);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent second =new Intent(Map_google.this,Navigatio_tool.class);
        startActivity(second);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==placepicker_rqst)
        {
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                String toastmsg=String.format("Place %s",place.getAddress());
               // Toast.makeText(Map_google.this,toastmsg, Toast.LENGTH_LONG).show();
                latLng=place.getLatLng();//used to take the
                lat_dest=latLng.latitude;
                lon_dest=latLng.longitude;

                Intent third=new Intent(this,Direction_travelling.class);
                Bundle bun=new Bundle();
                bun.putDouble("dest_lat",lat_dest);
                bun.putDouble("dest_lon",lon_dest);
                third.putExtras(bun);
                startActivity(third);
            }
        }

        finish();
    }


}
