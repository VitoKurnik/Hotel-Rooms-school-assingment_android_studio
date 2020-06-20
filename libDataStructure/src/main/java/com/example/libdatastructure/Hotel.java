package com.example.libdatastructure;

import java.util.ArrayList;
import java.util.Collections;

public class Hotel implements Sizable{
    private String ime;
    ArrayList<Soba> lista;

    public Hotel(String ime) {
        this.ime = ime;
        lista = new ArrayList<>();
    }

    public void add(Soba a) { lista.add(a); }

    public int size() { return lista.size(); }

    public String getIme() { return ime; }

    @Override
    public String toString() {
        return "Hotel{" +
                "ime='" + ime + '\'' +
                ", lista=" + lista +
                '}';
    }

    public void sort() {
        Collections.sort(lista);
    }

    public void sortPoStPostelj() {
        Collections.sort(lista, new Soba.ComparePostelje());
    }

    public void zmesaj() {
        Collections.shuffle(lista);
    }

    @Override
    public int getStPostelj() {
        int stPostelj = 0;
        for (Soba s:lista) stPostelj+=s.getStPostelj();
        return stPostelj;
    }

    public Soba getRoom(int position) {

        return lista.get(position);
    }
}