package Model;

import static java.lang.Math.sqrt;

public class Punct extends ElementGeometric {
    private int x;
    private int y;

    public Punct(){

    }

    public Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float distanta(Punct punct){
        return (float) sqrt((this.x - punct.getX()) * (this.x - punct.getX()) +
                (this.y - punct.getY()) * (this.y - punct.getY()));
    }

    public boolean distincte(Punct punct){
        if(this.x != punct.getX() || this.y != punct.getY())
            return false;
        return true;
    }

    public boolean coliniare(Punct punct1, Punct punct2){
        float panta1 = (float) (punct1.getY() - this.y) / (punct1.getX() - this.x);
        float panta2 = (float) (punct2.getY() - punct1.getY()) / (punct2.getX() - punct1.getX());
        return panta1 == panta2;
    }

    @Override
    public void desenare() {

    }
}
