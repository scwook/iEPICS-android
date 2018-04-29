package com.example.scwook.iepics_android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MonitoringListView extends LinearLayout {
    TextView pvNameText;
    TextView pvValueText;
    ImageView arrayImage;
    ImageView clockImage;

    public MonitoringListView(Context context) {
        super(context);
        init(context);
    }

    public MonitoringListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.monitoring_list, this, true);

        pvNameText = findViewById(R.id.pvNameText);
        pvValueText = findViewById(R.id.pvValueText);
        arrayImage = findViewById(R.id.arrayImage);
        clockImage = findViewById(R.id.clockImage);
    }

    public void setPVName(String pvName) {
        pvNameText.setText(pvName);
    }

    public void setPVValue(String pvValue) {
        pvValueText.setText(pvValue);
    }

    public void setArrayImage(int resID) {
        arrayImage.setImageResource(resID);
    }

    public void setClockImage(int resID) {
        clockImage.setImageResource(resID);
    }
}
