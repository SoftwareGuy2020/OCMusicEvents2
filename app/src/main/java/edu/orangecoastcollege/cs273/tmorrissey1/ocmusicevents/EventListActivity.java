package edu.orangecoastcollege.cs273.tmorrissey1.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {
    private ListView eventListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventListView = (ListView) findViewById(R.id.eventListView);

        // Set the adapter which binds the listView with the data in MusicView.java
        // Since the data is in an array , we use an ArrayAdapter
        // setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));

        //setContentView(R.layout.activity_event_list);
        try {
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        }
        catch (IOException e) {
            Log.e("OC Music Events", "Error loading JSON data. " + e.getMessage());
        }

        setListAdapter(new MusicEventAdapter(context,
                R.layout.music_event_list_item_layout, allMusicEvents));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        MusicEvent clickedEvent = allMusicEvents.get(position);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();

        Intent eventDetailsIntent = new Intent(this, EventDetailsActivity.class);

        eventDetailsIntent.putExtra("Title", title);
        eventDetailsIntent.putExtra("Date", date);
        eventDetailsIntent.putExtra("Day", day);
        eventDetailsIntent.putExtra("Time", time);
        eventDetailsIntent.putExtra("Location", location);
        eventDetailsIntent.putExtra("Address1", address1);
        eventDetailsIntent.putExtra("Address2", address2);

        startActivity(eventDetailsIntent);

    }
}
