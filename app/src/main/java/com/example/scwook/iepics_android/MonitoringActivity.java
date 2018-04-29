package com.example.scwook.iepics_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonitoringActivity extends AppCompatActivity {

    ListView listView;
    MonitoringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        listView = (ListView) findViewById(R.id.monitoringListView);

        adapter = new MonitoringAdapter();

        for(int i = 0; i < 50; i++) {
            adapter.addItems(new MonitoringListItem("Process Name1", "10.0"));
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MonitoringListItem item = (MonitoringListItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "Select : " + item.getPVName(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public class MonitoringAdapter extends BaseAdapter {
        ArrayList<MonitoringListItem> items = new ArrayList<>();

        @Override public int getCount() {
            return items.size();
        }

        public void addItems(MonitoringListItem item) {
            items.add(item);
        }

        @Override public Object getItem(int position) {
            return items.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup viewGroup) {
            MonitoringListView view = new MonitoringListView(getApplicationContext());
            MonitoringListItem item = items.get(position);
            view.setPVName(item.getPVName());
            view.setPVValue(item.getPVValue());
            view.setArrayImage(item.getArrayImage());
            view.setClockImage(item.getClockImageID());

            return view;
        }
    }
}
