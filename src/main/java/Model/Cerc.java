package Model;

import java.util.ArrayList;

public class Cerc extends FiguraGeometrica{

    private Punct centru;
    private float raza;

    public Cerc(Punct centru, float raza) {
        this.centru = centru;
        this.raza = raza;
    }

    public Cerc(Punct centru) {
        this.centru = centru;
    }

    public Cerc(){

    }

    public Punct getCentru() {
        return centru;
    }

    public void setCentru(Punct centru) {
        this.centru = centru;
    }

    public float getRaza() {
        return raza;
    }

    public void setRaza(float raza) {
        this.raza = raza;
    }

    @Override
    public void desenare() {
        
    }

    @Override
    public float aria() {
        return 0;
    }

    @Override
    public String toString() {
        return centru.getX() + " " + centru.getY() + " " + raza;
    }

    public ArrayList<Integer> toInt(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(centru.getX());
        list.add(centru.getY());
        list.add((int)Math.round(raza));

        return list;
    }
}
