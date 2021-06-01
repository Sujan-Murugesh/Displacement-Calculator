package com.sujan.caltest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;
    Button btn_start,btn_stop;
    EditText lat_text,lon_text;

    double latti1=0, long1 = 0, latti2 =0, long2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.lat_text1);
        t2=findViewById(R.id.lat_text2);
        t3=findViewById(R.id.long_text1);
        t4=findViewById(R.id.long_text2);
        t5=findViewById(R.id.text_out);

        lat_text=findViewById(R.id.lat1);
        lon_text=findViewById(R.id.long1);

        btn_start=findViewById(R.id.btnstart);
        btn_stop=findViewById(R.id.btnstop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latti1 = Double.parseDouble(lat_text.getText().toString());
                t1.setText(String.valueOf(latti1));

                long1 = Double.parseDouble(lon_text.getText().toString());
                t3.setText(String.valueOf(long1));

                lat_text.setText("");
                lon_text.setText("");
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latti2 = Double.parseDouble(lat_text.getText().toString());
                t2.setText(String.valueOf(latti2));

                long2 = Double.parseDouble(lon_text.getText().toString());
                t4.setText(String.valueOf(long2));

                lat_text.setText("");
                lon_text.setText("");

                distance(latti1,latti2,long1,long2);
            }
        });
    }

    private void distance(double latti1, double latti2, double long1, double long2) {

        double longDiff = long1-long2;

        //calculate distance
        double distance = Math.sin(deg2rad(latti1))
                * Math.sin(deg2rad(latti2))
                + Math.cos(deg2rad(latti1))
                * Math.cos(deg2rad(latti2))
                * Math.cos(deg2rad(longDiff));

        distance = Math.acos(distance);
        //convert distance radian to degree
        distance = rag2deg(distance);

        //distance in miles
        distance = distance * 60 * 1.1515;

        //distance in kilometers
        distance = distance * 1.609344;

        //t5.setText(String.valueOf(distance));
        t5.setText(String.format(Locale.US,"%2f ",distance));

    }

    private double rag2deg(double distance) {
        return  (distance * 180.0 / Math.PI);
    }

    private double deg2rad(double latti1) {
        return (latti1*Math.PI/180.0);
    }
    
    /*
    * function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
  var R = 6371; // Radius of the earth in km
  var dLat = deg2rad(lat2-lat1);  // deg2rad below
  var dLon = deg2rad(lon2-lon1);
  var a =
    Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
    Math.sin(dLon/2) * Math.sin(dLon/2)
    ;
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
  var d = R * c; // Distance in km
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180)
}
    * */
}