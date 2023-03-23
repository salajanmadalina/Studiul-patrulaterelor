package Model;

public class Test {
    private int id;
    private String intrebari;
    private int punctaj;

    public Test(int id, int punctaj, String intrebari) {
        this.intrebari = intrebari;
        this.id = id;
        this.punctaj = punctaj;
    }

    public Test(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(String intrebari) {
        this.intrebari = intrebari;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }
}
