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
        //verificam daca patrulaterul este convex
        if(convexSauConcav().equals("Concav"))
            return 0.0f;

        ArrayList<Float> laturi = lungimiLaturi();

        //verificam daca patrulaterul este circumscriptibil
        if(Math.round(laturi.get(0)) + Math.round(laturi.get(2)) != Math.round(laturi.get(1)) + Math.round(laturi.get(3))) {
            Dreapta ab = new Dreapta(p1, p2);
            Dreapta bc = new Dreapta(p2, p3);
            Dreapta cd = new Dreapta(p3, p4);
            Dreapta da = new Dreapta(p4, p1);
            Punct e = ab.punctIntersectie(cd);
            Punct f = bc.punctIntersectie(da);

            if(Math.round(p2.distanta(e)) + Math.round(p2.distanta(f)) != Math.round(p4.distanta(e)) + Math.round(p4.distanta(f))){
                return 0.0f;
            }
        }
        // calculam raza cercului circumscris cu formula lui Euler
        return  (laturi.get(0) * laturi.get(1) * laturi.get(2) * laturi.get(3)) / (4 * aria());
    }

    public float razaCerculuiInscris(){
        //verificam daca patrulaterul este convex
        if(convexSauConcav().equals("Concav"))
            return 0.0f;
        // calculam raza cercului inscris cu formula lui Brahmagupta
        return  (2 * aria()) / (perimetru());
    }

    public Cerc cercCircumscris(){
        float raza = razaCerculuiCircumscris();
        Dreapta d1 = new Dreapta(p1, p3);
        Dreapta d2 = new Dreapta(p2, p4);
        Punct centre = d1.punctIntersectie(d2);

        return new Cerc(centre, raza);
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
            if(masuriUnghiuri().get(i) > 180.0f || masuriUnghiuri().get(i).isNaN())
                return "Concav";
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
        information += "Raza cercului inscris este " + razaCerculuiInscris() + ";\n";

        return information;
    }
}
