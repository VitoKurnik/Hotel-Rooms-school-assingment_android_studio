package com.example.rezervacijasob;

import android.app.Application;
import android.util.Log;

import com.example.libdatastructure.IzjemaNegativnaCena;
import com.example.libdatastructure.Hotel;
import com.example.libdatastructure.Soba;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class ApplicationMy extends Application {
    public static final String TAG = ApplicationMy.class.getName();
    public static final String MY_FILE_NAME = "hotelbrisi.json";
    public String id;
    public Hotel currentHotel;
    static private Gson gson;
    static private File file;

    private File getFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_FILE_NAME);
        }
        Log.i(TAG, file.getPath());
        return file;
    }


    public static Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (!readFromFile()) {
            currentHotel = new Hotel("Hilton"); //init data object
            try {
                for (int i = 0; i < 100; i++) {
                    currentHotel.add(new Soba(499.99, 2));
                }
            } catch (IzjemaNegativnaCena izjemaNegativnaCena) {
                izjemaNegativnaCena.printStackTrace();
            }
        }
        String tmp = getGson().toJson(currentHotel);
        Log.d(TAG, tmp);
        Hotel h = getGson().fromJson(tmp, Hotel.class);
        Log.d(TAG, h.toString());
    }

    public void saveToFile() {
        try {
            FileUtils.writeStringToFile(getFile(), getGson().toJson(currentHotel));
        } catch (IOException e) {
            Log.d(TAG, "Can't save " + file.getPath());
        }
    }

    private boolean readFromFile() {
        if (!getFile().exists()) return false;
        try {
            currentHotel = getGson().fromJson(FileUtils.readFileToString(getFile()), Hotel.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Hotel getCurrentHotel() {
        return currentHotel;
    }

    public void setCurrentHotel(Hotel currentHotel) {
        this.currentHotel = currentHotel;
    }

    public int getHotelSize() {

        return currentHotel.size();
    }

    public Soba getRoom(int position) {

        return currentHotel.getRoom(position);
    }
}
