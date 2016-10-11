package com.example.disha_school.dishaschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Direction_travelling extends AppCompatActivity {
    double resultant_lat,resultant_lon;
    String d=null;
    CardView wd, bd, fi, ms;
    TextView txt;
    ImageView img,worst,best,worstT, bestT;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView Best, Worst, Shirt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anotherlayout);
        appBarLayout = (AppBarLayout)findViewById(R.id.appBarLayout);
        wd = (CardView)findViewById(R.id.wd);
        bd = (CardView)findViewById(R.id.bd);
        fi = (CardView)findViewById(R.id.fi);
        ms =(CardView)findViewById(R.id.ms);
        doAnimation(wd);
        doAnimation(bd);
        doAnimation(fi);
        doAnimation(ms);
        Best = (TextView)findViewById(R.id.bestDirectionText);
        Shirt = (TextView)findViewById(R.id.bestShirtText);
        Worst = (TextView)findViewById(R.id.worstDirectionText);
        worst = (ImageView)findViewById(R.id.arrowworst);
        worstT = (ImageView)findViewById(R.id.arrowworst2);
        bestT = (ImageView)findViewById(R.id.arrorwbest1);
        worstT.setVisibility(View.GONE);
        bestT.setVisibility(View.GONE);
        best = (ImageView)findViewById(R.id.arrorwbest);
        img=(ImageView)findViewById(R.id.imageView);
        img.setVisibility(View.INVISIBLE);
        Bundle bun=getIntent().getExtras();
        Double dest_lat=bun.getDouble("dest_lat", 123);
        Double dest_lon=bun.getDouble("dest_lon",1234);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("Disha Shool");
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#8E44AD"));
        dynamicChange();
        Navigatio_tool crr_loc=new Navigatio_tool();
        setType(Shirt);
        setType(Worst);
        setType(Best);

        if(crr_loc.flag!=10) {
            resultant_lat = dest_lat - crr_loc.CurrentLatitude;
            resultant_lon = dest_lon - crr_loc.CurrentLongitude;
        }
        else
        {
            resultant_lat = dest_lat - crr_loc.SourceLatitude;
            resultant_lon = dest_lon - crr_loc.SourceLongitude;
        }

//longitude hai x axis and latitude hai y axis

        if(resultant_lat>0 && resultant_lon==0)
        {
            d = "NORTH";
            img.setVisibility(View.VISIBLE);
        }
        else if (resultant_lat<0 && resultant_lon==0)
        {

            d = "SOUTH";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(180).setDuration(2500).setListener(null);
        }
        else if (resultant_lat==0 && resultant_lon>0) {
            d = "EAST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(90).setDuration(2500).setListener(null);

        }
        else if (resultant_lat==0 && resultant_lon < 0) {
            d = "WEST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(270).setDuration(2500).setListener(null);
        }
        else if (resultant_lat > 0 && resultant_lon > 0) {
            d = "NORTH-EAST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(45).setDuration(2500).setListener(null);


        }
        else if (resultant_lat>0 && resultant_lon<0) {
            d = "NORTH-WEST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(315).setDuration(2500).setListener(null);

        }
        else if (resultant_lat<0 && resultant_lon>0) {
            d = "SOUTH-EAST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(135).setDuration(2500).setListener(null);


        }
        else if (resultant_lat<0 && resultant_lon<0)
        {
            d = "SOUTH-WEST";
            img.setVisibility(View.VISIBLE);
            img.animate().rotationBy(225).setDuration(2500).setListener(null);
        }
        else {
            d = "Wrong Direction";
            Toast.makeText(Direction_travelling.this, "WONG INPUT", Toast.LENGTH_SHORT).show();
        }

        //this for the worst case according to the database

        if(crr_loc.week_days==1)
        {
            //this for the sunday
            //for SOUTH WEST
            worst.animate().rotationBy(225).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==2)
        {
            //this for monday
            //north
            worst.animate().rotation(0).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==3)
        {
            //this is for tuedsay
            //south east
            worst.animate().rotation(135).setDuration(2000).setListener(null);

        }
        else if (crr_loc.week_days==4)
        {
            //this is for wednesday
            //west
            worst.animate().rotation(270).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==5)
        {
            //this is for thursday
        }

        else if(crr_loc.week_days==6)
        {
           // this is for friday
            worst.animate().rotation(180).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==7)
        {
            //for saturday
            worst.animate().rotation(315).setDuration(2000).setListener(null);
        }

//here the worst cases ends


//here best cases starts
        String a="Best Direction to go  - ";
        String b = "Worst Direction to go  - " ;
        String c="Clothes to wear if travel can't be avoided - ";
        String bD[] = {"North East","South","North West","East","South West and West","North","South East"};
        String wD[]={"South West","North","South East","West","North East and East","South","North West"};
        String s[]={"Cream or Green", "Cream or Yellow","Yellow Or Blue","Blue, Black or Red", "White or Grey", "White or Grey", "Blue or Yellow", "Cream or Green"};
        if(crr_loc.week_days==1)
        {
            //this for the sunday
            //for NORTH EAST
            best.animate().rotationBy(45).setDuration(2000).setListener(null);
            worst.animate().rotationBy(180+45).setDuration(2000).setListener(null);



        }
        else if(crr_loc.week_days==2)
        {
            //this for monday
            //south
            best.animate().rotation(180).setDuration(2000).setListener(null);
            //worst.animate().rotationBy(180+45).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==3)
        {
            //this is for tuedsay
            //nort west
            best.animate().rotation(315).setDuration(2000).setListener(null);
            worst.animate().rotationBy(180 + 45 - 90).setDuration(2000).setListener(null);

        }
        else if (crr_loc.week_days==4)
        {
            //this is for wednesday
            //east
            best.animate().rotation(90).setDuration(2000).setListener(null);
            worst.animate().rotationBy(-90).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==5)
        {
            //this is for thursday
            worstT.setVisibility(View.VISIBLE);
            bestT.setVisibility(View.VISIBLE);
            best.animate().rotation(-90-45).setDuration(2000).setListener(null);
            worst.animate().rotationBy(45).setDuration(2000).setListener(null);
            bestT.animate().rotation(-90).setDuration(2000).setListener(null);
            worstT.animate().rotationBy(90).setDuration(2000).setListener(null);
            
        }

        else if(crr_loc.week_days==6)
        {
            // this is for friday
            //north
            best.animate().rotation(0).setDuration(2000).setListener(null);
            worst.animate().rotationBy(180).setDuration(2000).setListener(null);
        }
        else if(crr_loc.week_days==7)
        {
            //for saturday
            //south east
            best.animate().rotation(135).setDuration(2000).setListener(null);
            worst.animate().rotationBy(90 + 45).setDuration(2000).setListener(null);
        }

//here the BEST cases ends

        Best.setText(a+bD[crr_loc.week_days-1]);
        Worst.setText(b+wD[crr_loc.week_days-1]);
        if(crr_loc.week_days!=5){
            Shirt.setText(c+s[crr_loc.week_days-1]);
        }else{
            Shirt.setText("Wear White or Grey if you are travelling  East.\nWear Green if you are travelling North East.");
        }
    }

    private void dynamicChange() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }

    private void setType(TextView t){
        Typeface tp = (Typeface.createFromAsset(getAssets(),"fonts/a.ttf"));
        t.setTypeface(tp);
    }
    private void doAnimation(View v){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        anim.setDuration(1000);
        v.startAnimation(anim);
    }
}
