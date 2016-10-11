package com.example.disha_school.dishaschool;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Navigatio_tool extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    //Button b;
    ActionBarDrawerToggle toggle;
    TextView b;
    FloatingActionButton fab;
    TextView connecting;
    ProgressBar progressBar;
    public static GoogleApiClient gpi;
    int i;

    static int flag;

    Location l;
    Handler handler;
    final int placePickerSource = 4136;
    //Button retry;
    int[] ColorArray={Color.MAGENTA,Color.GREEN,Color.CYAN,Color.BLACK,Color.BLUE,Color.RED,Color.YELLOW};
    Context context;
    TextView bout_app;
    TextView source,destinition;
    static String address = "Current location can't be determined. " +
            "Please check your location settings or choose a different source location.";
  private Activity mContext=this;
   final int per_rqst_code=1;
    List<Address> addrList;

    Thread showM;
    DrawerLayout drawer;
    public static double CurrentLatitude, CurrentLongitude;
    static double SourceLatitude, SourceLongitude;
  static int week_days=-1;//just for onclick in the navigation drawer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigatio_tool);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bout_app = (TextView)findViewById(R.id.intro);
        bout_app.setText(new bout_app().about_app);
        //For the marshmallow part where the dialogue box will confirm the app permission
        source = (TextView)findViewById(R.id.source);
        destinition = (TextView)findViewById(R.id.destination);
        addrList=null;
        Boolean  b11=checkPermission();
        if(b11==false) {
            request_permission();
        }

        else
        {


        }

    //for the hamburger icon
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/a.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build());
        if(gpi==null)
        gpi = new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();

        context=this;

        View v = LayoutInflater.from(this).inflate(R.layout.nav_header_navigatio_tool,null);
        //final TextView headerText = (TextView)v.findViewById(R.id.textView33);

        handler=new Handler();

        b=(TextView)findViewById(R.id.text);

        connecting = (TextView)findViewById(R.id.connecting);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#80DAEB"),
                android.graphics.PorterDuff.Mode.MULTIPLY);

        final Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/a.ttf");

        connecting.setTypeface(tp);
       // headerText.setTypeface(tp);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
       // b.setVisibility(View.GONE);




        checkForInternetConnection();
        //int i=0;
         l=null;

        //getting the location

        //getTheLocation:






        drawer.openDrawer(Gravity.LEFT);//it will open the side drawer automatically when the page gets open


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);//for displaying the icon in navigation drawer
        navigationView.setNavigationItemSelectedListener(this);

        fab.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (week_days != -1) {
                    Intent first = new Intent(Navigatio_tool.this, Map_google.class);
                    startActivity(first);
                } else {

                    Toast.makeText(Navigatio_tool.this, "Select the day of travel", Toast.LENGTH_LONG).show();
                    // to open next activity for map
                    //dont remove this part
                }
                if (i >= 3) {
                    fab.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    connecting.setVisibility(View.GONE);

                }

            }
        });

        fab.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        connecting.setVisibility(View.GONE);
       

        destinition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(week_days!=-1)
                {
                    Intent first = new Intent(Navigatio_tool.this, Map_google.class);
                    startActivity(first);
                }
                else {

                    Toast.makeText(Navigatio_tool.this, "Select the day of travel", Toast.LENGTH_LONG).show();
                    // to open next activity for map
                    //dont remove this part
                }
                if(i>=3){


                }

            }
        });
        final Context placePickerContext  = this;

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                flag=10;

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build((Activity) placePickerContext),placePickerSource);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

         showM = new Thread(new Runnable() {
             @Override
             public void run() {
                 showMessage();
             }
         });
        showM.start();
    }


    public void initiliase(){
        gpi = new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
    }

    private void showMessage(){
        final Context localContext = this;
       handler.post(new Runnable() {
           @Override
           public void run() {

               int Max = ColorArray.length-1;
               int Min=0;
               AlertDialog.Builder builder = new AlertDialog.Builder(localContext);
               LayoutInflater inflater = getLayoutInflater();
               View ll = inflater.inflate(R.layout.builder_layout,null);
               builder.setView(ll);
               final TextView buliderText = (TextView)ll.findViewById(R.id.buidlerlocation);
               final Button builderButton = (Button)ll.findViewById(R.id.buttonBuidler);

               //buliderText.setVisibility(View.GONE);


               buliderText.setText(address);

               final AlertDialog alertDialog = builder.create();
               alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               //alertDialog.setContentView(R.layout.tabs);

               builderButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                    alertDialog.cancel();
                   }
               });
               alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
               alertDialog.show();
               WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
               lp.copyFrom(alertDialog.getWindow().getAttributes());
               lp.width = WindowManager.LayoutParams.MATCH_PARENT;
               lp.height = WindowManager.LayoutParams.MATCH_PARENT;
               alertDialog.getWindow().setAttributes(lp);


           }
       });
    }

    @Override
    public void onActivityResult(int requestCode, int result, Intent data){
        if(requestCode==placePickerSource&&result==RESULT_OK){
            Place place = PlacePicker.getPlace(data,this);
            LatLng latlng = place.getLatLng();
           // CurrentLatitude=latlng.latitude;
            //CurrentLongitude=latlng.longitude;

            SourceLatitude=latlng.latitude;
            SourceLongitude=latlng.longitude;

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private String getLocation() {
        i=0;
       new Thread(new Runnable() {
           @Override
           public void run() {
               while(l==null&&i<5){
                   l = LocationServices.FusedLocationApi.getLastLocation(gpi);
                   i++;
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

               }

               if(i==5){

                  handler.post(new Runnable() {
                      @Override
                      public void run() {

                          Toast.makeText(context,"Current Location can't be determined. Please retry.",Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                          connecting.setVisibility(View.GONE);
                          Drawable drawable = getResources().getDrawable(R.drawable.retry);
                          Bitmap bmp = ((BitmapDrawable)drawable).getBitmap();
                          fab.setImageBitmap(bmp);
                          fab.setVisibility(View.GONE);

                      }
                  });
               }
               else{
                   handler.post(new Runnable() {
                       @Override
                       public void run()
                       {
                           Geocoder geocoder = new Geocoder(context,Locale.getDefault());
                           Drawable drawable = getResources().getDrawable(R.drawable.compass);
                           Bitmap bmp = ((BitmapDrawable)drawable).getBitmap();
                           fab.setImageBitmap(bmp);
                           progressBar.setVisibility(View.GONE);
                           connecting.setVisibility(View.GONE);
                           fab.setVisibility(View.GONE);

                           CurrentLatitude = l.getLatitude();

                           CurrentLongitude=l.getLongitude();

                           try{
                               addrList = geocoder.getFromLocation(CurrentLatitude,CurrentLongitude,1);
                           }
                           catch (IOException e) {
                               e.printStackTrace();
                           }
                            finally {

                               if(addrList!=null&&addrList.size()>0) {
                                Address add = addrList.get(0);
                               if(add.getMaxAddressLineIndex()>0){
                                   address = add.getAddressLine(0)+","+add.getLocality()+add.getCountryName();

                               }
                           }}
                           Log.i("t", CurrentLatitude + " " + CurrentLongitude + "");
                           //showM.start();//idher error a rhi hai is line me
                       }
                   });
               }
           }
       }).start();
        return address;
    }

    private void checkForInternetConnection() {

      //  ConnectivityManager manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        //NetworkInfo info = manager.getActiveNetworkInfo();


        if(!isNetworkAvailable(this))
        {
            startActivity(new Intent(this,newnew.class ));
            finish();

        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if(gpi==null)
        gpi = new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
       // gpi.connect();
        LocationRequest lr=LocationRequest.create();
        lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        lr.setInterval(2000);

    }
    @Override
    public void onStop()
    {
        super.onStop();
        gpi.disconnect();
    }


    @SuppressWarnings("StatementWithEmptyBody")//it ll not show the warning in the screen

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sunday)
        {

            week_days=1;
            drawer.closeDrawers();

        }
        else if (id == R.id.monday) {
            week_days=2;
            drawer.closeDrawers();


        }
        else if (id == R.id.tuesday)
        {
            week_days=3;
            drawer.closeDrawers();

        }
        else if (id == R.id.wednesday) {
            week_days=4;
            drawer.closeDrawers();

        }
        else if (id == R.id.thursday)
        {
            week_days=5;
            drawer.closeDrawers();

        }
        else if (id == R.id.friday)
        {
            week_days=6;
            drawer.closeDrawers();

        }
        else if(id==R.id.saturday)
        {
            week_days=7;
            drawer.closeDrawers();

        }
        else if(id==R.id.calendar)
        {
            Intent calv=new Intent(Navigatio_tool.this,Calendar_view.class);//opening the calendar view for the the date
            startActivity(calv);
        }
        else if (id==R.id.about_app)
        {
           Intent fcuk=new Intent(this,bout_app.class);
            startActivity(fcuk);
            drawer.closeDrawers();
        }
        else {
            week_days=-1;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public Boolean checkPermission()
    {
        int result= ContextCompat.checkSelfPermission((Activity)mContext, Manifest.permission.ACCESS_FINE_LOCATION);
        if(result== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else
            return false;


    }
    private void request_permission()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale((Activity)mContext,Manifest.permission.ACCESS_FINE_LOCATION ))
        {
           // Toast.makeText(MainActivity.this, "abhi to sahi hai", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ActivityCompat.requestPermissions((Activity)mContext,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},per_rqst_code);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case per_rqst_code:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                  //  Toast.makeText(this,"sahi khel gya bc",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   // Toast.makeText(this,"laude lg gaye",Toast.LENGTH_SHORT);
                }

        }
    }



}
