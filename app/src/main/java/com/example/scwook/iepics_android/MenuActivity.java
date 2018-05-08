package com.example.scwook.iepics_android;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void onMonitoringButtonClick(View V) {
        Intent intent = new Intent(getApplicationContext(), MonitoringActivity.class);
        startActivity(intent);
    }

    public void onChartButtonClick(View V) {
        boolean isEnable = test();
        if(isEnable) {
            Toast.makeText(MenuActivity.this, "Enable", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MenuActivity.this, "Disable", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native boolean test();
}
