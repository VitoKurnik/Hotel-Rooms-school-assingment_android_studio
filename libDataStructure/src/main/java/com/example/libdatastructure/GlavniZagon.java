package com.example.libdatastructure;

import java.awt.Color;
import java.util.ArrayList;

public class GlavniZagon {
    public static void main(String[] args) {
        try {

            Soba a = new Soba(299.99, 1);
            PredsedniskaSuita b = new PredsedniskaSuita(999.99, 1, new Color(100, 100, 0));
            Soba c = new Soba(499.99, 2);
            System.out.println("Dela:" + a);
            Hotel hilton = new Hotel("Hilton");
            hilton.add(a);
            hilton.add(b);
            hilton.add(c);
            System.out.println("Hotel " + hilton.getIme() + " vsebuje:" + hilton.size());
            System.out.println("Vsebina:" + hilton.toString());
            hilton.sort();
            System.out.println("Vsebina:" + hilton.toString());
            hilton.zmesaj();
            hilton.sortPoStPostelj();
            ArrayList<Sizable> vseKarObstaja = new ArrayList<>();
            vseKarObstaja.add(hilton);
            vseKarObstaja.add(a);
            //..
            System.out.println("Vsebina:" + hilton.toString());
            c.setCena(c.getCena() - 10);
        } catch (IzjemaNegativnaCena e) {
            System.out.println("Napačna cena! Naj bo pozitivna");
        }
    }
}
