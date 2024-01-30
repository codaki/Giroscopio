package com.example.giroscopio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

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

            String gyroscopeValues = String.format("Gyroscope Values: %.2f - %.2f - %.2f", x, y, z);
            TextView gyroscopeTextView = findViewById(R.id.gyroscopeValues);
            gyroscopeTextView.setText(gyroscopeValues);
            if(x > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            } else if(x < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }else if(x == 0.0f){
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }else if(y > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            } else if(y < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }else if(y == 0.0f){
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }else if(z > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
            } else if(z < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
            }else if(z == 0.0f){
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle accuracy changes if needed
        }
    };
}