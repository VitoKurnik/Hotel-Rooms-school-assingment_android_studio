package com.example.rezervacijasob;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInfo extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();

    private TextView tvInfo;
    private TextView tvHotelInfo;
    private ApplicationMy app;
    private SharedPreferences sp;

    Button shrani;
    EditText mail;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvInfo = findViewById(R.id.tvName);
        tvHotelInfo = findViewById(R.id.idHotelInfo);
        mail = findViewById(R.id.mail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        app = (ApplicationMy) getApplication();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        shrani = (Button) findViewById(R.id.shrani);
        shrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailString = mail.getText().toString();
                String usString = username.getText().toString();
                String pwString = password.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("MAIL",mailString);
                editor.putString("USERNAME",usString);
                editor.putString("PASSWORD",pwString);
                editor.apply();
                editor.commit();
                setResult(6);
                finish();
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvHotelInfo.setText(app.getCurrentHotel().getIme());
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String name = extras.getString(MainActivity.PARA_ID);
            tvInfo.setText(name);
        } else
            Log.i (TAG, "NI PODATKA!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "To je konec:",Toast.LENGTH_LONG).show();
    }
}
