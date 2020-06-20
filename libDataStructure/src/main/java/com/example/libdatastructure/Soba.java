package com.example.libdatastructure;

import java.util.Comparator;

public class Soba implements Sizable, Comparable<Soba> {
    protected double cena;
    protected int stPostelj;

    public void setCena(double cena) throws IzjemaNegativnaCena {
        if (cena < 0) throw new IzjemaNegativnaCena();
        this.cena = cena;
    }

    public Soba(double cena, int stPostelj) throws IzjemaNegativnaCena {
        setCena(cena);
        this.stPostelj = stPostelj;
    }

    @Override
    public int getStPostelj() {
        return stPostelj;
    }

    public double getCena() {
        return cena;
    }

    static class ComparePostelje implements Comparator<Soba> {
        @Override
        public int compare(Soba s0, Soba s1) {
            return s0.stPostelj - s1.stPostelj;
        }
    }

    @Override
    public String toString() {
        return "Soba{" +
                "cena=" + cena +
                ", stevilo postelj=" + stPostelj +
                '}';
    }

    @Override
    public int compareTo(Soba soba) {
        if (cena > soba.cena) return 1;
        if (cena < soba.cena) return -1;
        return 0;
    }
}
