package com.example.disha_school.dishaschool;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class bout_app extends AppCompatActivity {
TextView txt;

    final static String about_app=
            "How to use:\n" +
            "1.Select the day of travel\n" +
            "2.Select Location from the Map(tap search at the top ,and type the destination location)\n" +
            "3.Tap on the  best direction Button to know the best direction of travel.\n" +
            "4.If travelling direction cant be avoided ,then wear clothes having the same color as shown.\n" +
            "\n" +
            "NOTE:This app requires Location access,GPS,and the Internet connection.\"";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bout_app);
        txt=(TextView)findViewById(R.id.textView);


        final Typeface tp11 = Typeface.createFromAsset(getAssets(), "fonts/a.ttf");
        txt.setTypeface(tp11);



        txt.setText("The app is a kind Astro App, which helps deciding users selecting day of the meeting on the basis of the Day,  location of their House and location of the meeting, And it suggests selecting the color of clothes to wear on the day of meeting.\n" +
                "\n" +
                "E.g.\n" +
                "A has a meeting on Monday and he needs to travel in North direction. As per Disha Shool Traveling towards north on Monday is not good. If it can not be avoided A should wear yellow color clothes to minimize the effects.\n" +
                "\n" +
                "How to use:\n" +
                "1.Select the day of travel\n" +
                "2.Select Location from the Map(tap search at the top ,and type the destination location)\n" +
                "3.Tap on the  best direction Button to know the best direction of travel.\n" +
                "4.If travelling direction cant be avoided ,then wear clothes having the same color as shown.\n" +
                "\n" +
                "NOTE:This app requires Location access,GPS,and the Internet connection.\"");
    }
}
