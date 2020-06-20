package com.example.rezervacijasob;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import com.example.libdatastructure.IzjemaNegativnaCena;
import com.example.libdatastructure.Hotel;
import com.example.libdatastructure.Soba;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private Hotel hilton;
    TextView tvTitle;
    private TextView tvHtml;
    private TextView tvInfo;
    private boolean firstRun;
    private ApplicationMy app;

    EditText etCena;
    Button btnOdpri;
    Button btnBonus;

    private static final String TAG = "MyActivity";
    public static final String PARA_ID = "INFO_ID";
    public static final int HOTEL_ACTIVITY = 2;
    private static final String APP_ID = "APP_ID_KEY";
    private SharedPreferences sp;
    private String idAPP;

    public double povpCena = 499.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hilton = new Hotel("Hilton");
        tvTitle = findViewById((R.id.tvTitle));
        tvInfo = findViewById(R.id.tvInfo);
        etCena = findViewById(R.id.etCena);
        btnOdpri = findViewById(R.id.btnOpen);
        btnBonus = findViewById(R.id.btnBon);
        tvHtml = findViewById(R.id.tvHtml);
        app = (ApplicationMy) getApplication();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sp == null) {
            Log.e(TAG, "Missing DefaultSharedPreferences");
        }

        btnOdpri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ActivityInfo.class);
                double visina = 1.83;
                String s = String.format("Vitova visina: %.2f" ,visina);
                i.putExtra(PARA_ID, s);
                startActivityForResult(i, 1);
            }
        });

        btnBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), BonusActivity.class);
                i.putExtra(PARA_ID, hilton.getIme());
                startActivityForResult(i, HOTEL_ACTIVITY);
            }
        });
        initAppID();
        update();
    }

    private void update() {
        tvTitle.setText(app.getCurrentHotel().getIme());
        Log.d(TAG,""+app.getCurrentHotel().toString());
        etCena.setText("499.99");
        String welcomeStr=String.format("Povprecna cena sobe: %.2f" ,povpCena);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvHtml.setText(Html.fromHtml(welcomeStr, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvHtml.setText(Html.fromHtml(welcomeStr));
        }
        tvHtml.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initAppID() {
        if (sp.contains(APP_ID)) {//READ IT FROM FILE
            idAPP = sp.getString(APP_ID, "DEFAULT VALUE ");
            firstRun = false;
        }
        else { //FIRST TIME GENERATE ID AND SAVE IT
            firstRun = true;
            idAPP = UUID.randomUUID().toString().replace("-", "");
            //UUID se uporablja za identifikacijo specifične instance aplikacije
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(APP_ID,idAPP);
            editor.apply();
            editor.commit(); //
        }
        tvInfo.setText(idAPP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        app.saveToFile();
    }

    public void dodaj(View v) {
                try {
                    app.getCurrentHotel().add(new Soba(Double.parseDouble(etCena.getText().toString()),2));
                    update();
                } catch (IzjemaNegativnaCena izjemaNegativnaCena) {
                    izjemaNegativnaCena.printStackTrace();
        }
    }

    public void izhod(View v) {
        finish();
        System.exit(0);
    }

    public void info(View v) {
        Log.i(TAG, "Stevilo elementov v seznamu: " +hilton.size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == HOTEL_ACTIVITY){
                Double roomPrice = data.getDoubleExtra("roomPrice", 499.99);
                //TODO
                try {
                    hilton.add(new Soba(roomPrice,2));
                    update();
                } catch (IzjemaNegativnaCena izjemaNegativnaCena) {
                    izjemaNegativnaCena.printStackTrace();
                }
            }
        }
    }

    public void openList(View view) {
        Intent i = new Intent(getBaseContext(), ActivityList.class);
        startActivity(i);
    }
}
