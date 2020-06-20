package com.example.rezervacijasob;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class BonusActivity extends AppCompatActivity {
    private TextView tvBonus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        tvBonus = findViewById(R.id.tvName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String name = extras.getString(MainActivity.PARA_ID);
            tvBonus.setText(name);
        } else
            Log.i("ActivityInfo", "NI PODATKA!");
    }

    public void save(View v){
        Intent result = new Intent();
        result.putExtra("roomPrice", 499.99);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "To je konec:",Toast.LENGTH_LONG).show();
    }
}
