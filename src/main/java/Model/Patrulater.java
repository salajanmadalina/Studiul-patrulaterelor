package Model;

import java.util.ArrayList;

public class Patrulater extends FiguraGeometrica{
    private Punct p1;
    private Punct p2;
    private Punct p3;
    private Punct p4;

    public Patrulater() {
    }

    public Patrulater(Punct p1, Punct p2, Punct p3, Punct p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Patrulater(ArrayList<Integer> coords){
        this.p1 = new Punct(coords.get(0), coords.get(1));
        this.p2 = new Punct(coords.get(2), coords.get(3));
        this.p3 = new Punct(coords.get(4), coords.get(5));
        this.p4 = new Punct(coords.get(6), coords.get(7));
    }

    public Punct getP1() {
        return p1;
    }

    public void setP1(Punct p1) {
        this.p1 = p1;
    }

    public Punct getP2() {
        return p2;
    }

    public void setP2(Punct p2) {
        this.p2 = p2;
    }

    public Punct getP3() {
        return p3;
    }

    public void setP3(Punct p3) {
        this.p3 = p3;
    }

    public Punct getP4() {
        return p4;
    }

    public void setP4(Punct p4) {
        this.p4 = p4;
    }

    public ArrayList<Float> lungimiLaturi(){
        ArrayList<Float> lungimi = new ArrayList<Float>();
        lungimi.add(p1.distanta(p2));
        lungimi.add(p2.distanta(p3));
        lungimi.add(p3.distanta(p4));
        lungimi.add(p4.distanta(p1));
        return lungimi;
    }

    public ArrayList<Float> masuriUnghiuri() {
        ArrayList<Float> masuri = new ArrayList<Float>();
        float l1 = p1.distanta(p2);
        float l2 = p2.distanta(p3);
        float l3 = p3.distanta(p4);
        float l4 = p4.distanta(p1);
        float cos1 = (l2 * l2 + l4 * l4 - l1 * l1) / (2 * l2 * l4);
        float cos2 = (l1 * l1 + l3 * l3 - l2 * l2) / (2 * l1 * l3);
        float cos3 = (l2 * l2 + l4 * l4 - l3 * l3) / (2 * l2 * l4);
        float cos4 = (l1 * l1 + l3 * l3 - l4 * l4) / (2 * l1 * l3);
        masuri.add((float) Math.toDegrees(Math.acos(cos1)));
        masuri.add((float) Math.toDegrees(Math.acos(cos2)));
        masuri.add((float) Math.toDegrees(Math.acos(cos3)));
        masuri.add((float) Math.toDegrees(Math.acos(cos4)));
        return masuri;
    }

    public float perimetru(){
        float perimetru = 0.0f;
        for (Float lungime : lungimiLaturi()) {
            perimetru += lungime;
        }
        return perimetru;
    }

    public float razaCerculuiCircumscris(){
        ArrayList<Float> laturi = lungimiLaturi();
        float arie = aria();

        // calculam raza cercului circumscris cu formula lui Euler
        float razaCircumscris = (laturi.get(0) * laturi.get(1) * laturi.get(2) * laturi.get(3)) / (4 * arie);

        return razaCircumscris;
    }

    public float razaCerculuiInscris(){
        float arie = aria();

        // calculam raza cercului inscris cu formula lui Brahmagupta
        float razaInscris = (2 * arie) / (perimetru());

        return razaInscris;
    }

    public Cerc cercCircumscris(){
        float raza = razaCerculuiCircumscris();
        Punct centru = new Punct();

        float x1 = p1.getX();
        float y1 = p1.getY();
        float x2 = p2.getX();
        float y2 = p2.getY();
        float x3 = p3.getX();
        float y3 = p3.getY();
        float x4 = p4.getX();
        float y4 = p4.getY();

        float a = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        float b = (float) Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        float c = (float) Math.sqrt(Math.pow(x4 - x3, 2) + Math.pow(y4 - y3, 2));
        float d = (float) Math.sqrt(Math.pow(x1 - x4, 2) + Math.pow(y1 - y4, 2));

        float A = (float) Math.acos((Math.pow(a, 2) + Math.pow(d, 2) - Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * d));
        float B = (float) Math.acos((Math.pow(b, 2) + Math.pow(a, 2) - Math.pow(c, 2) - Math.pow(d, 2)) / (2 * b * a));
        float C = (float) Math.acos((Math.pow(c, 2) + Math.pow(b, 2) - Math.pow(d, 2) - Math.pow(a, 2)) / (2 * c * b));
        float D = (float) Math.acos((Math.pow(d, 2) + Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2)) / (2 * d * c));

        float centerX = (float) ((a * Math.sin(D) + b * Math.sin(C) + c * Math.sin(B) + d * Math.sin(A)) / (Math.sin(A) + Math.sin(B) + Math.sin(C) + Math.sin(D)));
        float centerY = (float) ((a * Math.cos(D) + b * Math.cos(C) + c * Math.cos(B) + d * Math.cos(A)) / (Math.sin(A) + Math.sin(B) + Math.sin(C) + Math.sin(D)));

        centru.setX(Math.round(centerX));
        centru.setY(Math.round(centerY));

        return new Cerc(centru, raza);
    }

    public Cerc cercInscris(){
        float razaInscris = razaCerculuiInscris();
        Punct centru = new Punct(
                (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4,
                (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4);
        return new Cerc(centru, razaInscris);
    }

    public String convexSauConcav(){
        for(int i = 0; i < 4; i ++){
            if(masuriUnghiuri().get(i) > 180.0f)
                return "Concav;";
        }
        return "Convex";
    }

    public ArrayList<Integer> cercuri(){
        ArrayList<Integer> str = cercInscris().toInt();
        str.addAll(cercCircumscris().toInt());
        return str;
    }

    @Override
    public void desenare() {

    }

    @Override
    public float aria() {
        ArrayList<Float> laturi = lungimiLaturi();

        float semiperimetru = perimetru() / 2;

        // calculam aria patrulaterului cu formula lui Brahmagupta
        float arie = (float) Math.sqrt((semiperimetru - laturi.get(0)) * (semiperimetru - laturi.get(1)) * (semiperimetru - laturi.get(2)) * (semiperimetru - laturi.get(3)));

        return arie;
    }

    @Override
    public String toString() {

        String information = "Convex sau concav ? " + convexSauConcav() + "\n";
        String laturi = "";
        String unghiuri = "";

        for(int i = 0; i < lungimiLaturi().size(); i ++){
            laturi += lungimiLaturi().get(i);
            unghiuri += masuriUnghiuri().get(i);

            if(i < 3){
                laturi += ", ";
                unghiuri += ", ";
            }
        }

        information += "Lungimile laturilor sunt: " + laturi + ";\n";
        information += "Masurile unghiurilor sunt: " + unghiuri + ";\n";
        information += "Perimetrul este " + perimetru() + ";\n";
        information += "Aria este " + aria() + ";\n";
        information += "Raza cercului circumscris este " + razaCerculuiCircumscris() + ";\n";
        information += "Raza cerculuin inscris este " + razaCerculuiInscris() + ";\n";

        return information;
    }
}
