package my.edu.khcy4jwnnottingham.crisisalertwithvisualandlocationsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Emergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView text;
        TextView text1;
        GPS_tracker gps;
        Button police;
        Button ambulane;
        Button animal;
        Button fire;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        text = (TextView)findViewById(R.id.textView14);
        text1 = (TextView)findViewById(R.id.textView23);

        gps = new GPS_tracker(Emergency.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongtitude();

            text.setText("Latitude: " + latitude);
            text1.setText("Longitude: " + longitude);
        } else {
            gps.showSettingAlert();
        }

        police = (Button)findViewById(R.id.button7);
        fire = (Button)findViewById(R.id.button5);
        animal = (Button)findViewById(R.id.button9);
        ambulane = (Button)findViewById(R.id.button6);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Emergency.this, Acknowledge.class));
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Emergency.this, Acknowledge.class));
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Emergency.this, Acknowledge.class));
            }
        });

        ambulane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Emergency.this, Acknowledge.class));
            }
        });
    }

}
