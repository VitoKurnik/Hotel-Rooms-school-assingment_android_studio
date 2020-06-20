package com.example.libdatastructure;

import java.awt.Color;

public class PredsedniskaSuita extends Soba {
    private Color barvaStene;
    public PredsedniskaSuita(double cena, int stPostelj, Color a) throws IzjemaNegativnaCena {
        super(cena, stPostelj);
        barvaStene = a;
    }

    @Override
    public String toString() {
        return "PredsedniskaSuita{" +
                "barva stene=" + barvaStene +
                "cena=" + cena +
                ", stevilo postelj=" + stPostelj +'}';
    }
}
