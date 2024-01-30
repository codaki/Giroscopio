package com.example.giroscopio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Update UI with gyroscope values
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Your existing code for updating UI based on gyroscope values

            // Open different apps based on gyroscope values
            if (x > 0.5f) {
                openCalculator();
            } else if (x < -0.5f) {
                openCalendar();
            } else if (y > 0.5f) {
                openCamera();
            } else if (y < -0.5f) {
                openBrowser();
            } else if (z > 0.5f) {
                openSettings();
            } else if (z < -0.5f) {
                openSpotify();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle accuracy changes if needed
        }
    };

    private void openCalculator() {
        Intent intent =  new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivity(intent);
    }

    private void openCalendar() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_CALENDAR);
        startActivity(intent);
    }

    private void openCamera() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.openai.com"));
        startActivity(intent);
    }

    private void openBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

    private void openSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    private void openSpotify() {
        String number = "+593978960525";
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}