package com.example.rezervacijasob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ActivityList extends AppCompatActivity {
    RecyclerView rv;
    ApplicationMy app;
    AdapterHotel adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rv = findViewById(R.id.rv);
        app = (ApplicationMy) getApplication();
        adapter = new AdapterHotel(app);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
