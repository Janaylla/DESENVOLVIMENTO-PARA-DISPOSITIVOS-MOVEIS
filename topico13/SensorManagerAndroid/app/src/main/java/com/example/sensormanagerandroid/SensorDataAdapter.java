package com.example.sensormanagerandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorDataAdapter extends RecyclerView.Adapter<SensorDataAdapter.ViewHolder> {

    private List<SensorData> sensorDataList;
    private Context context;

    public SensorDataAdapter(Context context, List<SensorData> sensorDataList) {
        this.context = context;
        this.sensorDataList = sensorDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sensor_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SensorData sensor = sensorDataList.get(position);

        holder.sensorName.setText(sensor.getName());
        holder.sensorValue.setText(String.valueOf(sensor.getValue()));
    }

    @Override
    public int getItemCount() {
        return sensorDataList.size();
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sensorName;
        TextView sensorValue;

        ViewHolder(View itemView) {
            super(itemView);
            sensorName = itemView.findViewById(R.id.sensorName);
            sensorValue = itemView.findViewById(R.id.sensorValue);
        }
    }
}
