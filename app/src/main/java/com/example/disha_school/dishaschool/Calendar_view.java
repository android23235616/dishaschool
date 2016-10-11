package com.example.disha_school.dishaschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

public class Calendar_view extends AppCompatActivity {
CalendarView cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        cal=(CalendarView)findViewById(R.id.calendarView);

        final Navigatio_tool crr_loc=new Navigatio_tool();
        //now on date change
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            Calendar calendar=Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int day=calendar.get(Calendar.DAY_OF_WEEK);
                if(day==1)
                {//sunday
                   // Toast.makeText(view.getContext(),"sunday",Toast.LENGTH_SHORT).show();
                    crr_loc.week_days=1;
                    Toast.makeText(Calendar_view.this, "Sunday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();
                }
                else if(day==2)
                {//monday

                    crr_loc.week_days=2;
                    Toast.makeText(Calendar_view.this, "Monday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();

                }
                else if(day==3)
                {//tueday

                    crr_loc.week_days=3;
                    Toast.makeText(Calendar_view.this, "Tuesday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();


                }
                else if(day==4)
                {// wednesday

                    crr_loc.week_days=4;
                    Toast.makeText(Calendar_view.this, "Wednesday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();

                }
                else if(day==5)
                {
                    Toast.makeText(Calendar_view.this, "Thursday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();

                    crr_loc.week_days=5;

                }
                else if(day==6)
                {
                    Toast.makeText(Calendar_view.this, "Friday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();

                    crr_loc.week_days=6;
                }
                else if(day==7)
                {
                    Toast.makeText(Calendar_view.this, "Saturday has been selected ,go back to select location", Toast.LENGTH_SHORT).show();

                    crr_loc.week_days=7;
                }
                else
                {

                    crr_loc.week_days=-1;
                }




            }
        });


    }
}
