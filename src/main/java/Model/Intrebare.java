package Model;

public class Intrebare {
    private String intrebare;
    private String raspuns;
    private int id;

    public Intrebare(String intrebare, String raspuns, int id) {
        this.intrebare = intrebare;
        this.raspuns = raspuns;
        this.id = id;
        //nr++;
    }

    public Intrebare(){}

    public String getIntrebare() {
        return intrebare;
    }

    public void setIntrebare(String intrebare) {
        this.intrebare = intrebare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaspuns() {
        return raspuns;
    }

    public void setRaspuns(String raspuns) {
        this.raspuns = raspuns;
    }

    @Override
    public String toString() {
        return  intrebare + '\'';
    }
}
