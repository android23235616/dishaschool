package com.example.disha_school.dishaschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Best_direction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_direction);
        Intent i =getIntent();
        String s = i.getStringExtra("direction");
        final ImageView image = (ImageView)findViewById(R.id.compass);
        final TextView text = (TextView)findViewById(R.id.SayDirection);

        if(s=="s1"){

        }else if(s=="s2"){

        }
        else if(s=="m"){

        }
        else if(s=="t1"){

        }
        else if(s=="w"){

        }
        else if(s=="t2"){

        }
        else if(s=="f"){

        }else if(s=="tanmay"){
            Drawable dr = getResources().getDrawable(R.drawable.north);
            Bitmap bmp = ((BitmapDrawable)dr).getBitmap();
            image.setImageBitmap(bmp);
            text.setText(s);
            Typeface type = Typeface.createFromAsset(getAssets(),"fonts/a.ttf");
            text.setTypeface(type);
        }
    }
}
