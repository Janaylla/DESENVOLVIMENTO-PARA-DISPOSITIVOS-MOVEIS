package com.example.sensormanagerandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private SensorDataAdapter adapter;
    private ArrayList<SensorData> sensorDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listSensor = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorDataList = new ArrayList<SensorData>();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        for (Sensor s : listSensor) {
            sensorDataList.add(new SensorData(s.getName(), ""));
            mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        adapter = new SensorDataAdapter(this , sensorDataList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        for (int i = 0; i < sensorDataList.size(); i++) {
            SensorData s = sensorDataList.get(i);
            if (s.getName().equals(event.sensor.getName())) {
                StringBuilder valueText = new StringBuilder();
                for (float value : event.values) {
                    valueText.append(value).append("\n");
                }
                s.setValue(valueText.substring(0, valueText.length() - 1));

                int finalI = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemChanged(finalI);
                    }
                });
                break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}