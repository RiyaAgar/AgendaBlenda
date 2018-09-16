package com.example.agendablenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    private  static final String TAG = "Calendar";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(Calendar.this,MainActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });
    }
}
