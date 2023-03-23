package Presenter;

import Model.Dao.IntrebareDAO;
import Model.Dao.TestDAO;
import Model.Intrebare;
import Model.Test;
import View.IUser;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

public class UserPresenter {
    private IUser iUser;
    private IntrebareDAO intrebareDAO;
    private TestDAO testDAO;
    private String raspunsuri;
    private int nr;
    private String intrebariRezerva;

    public UserPresenter(IUser iUser) {
        this.iUser = iUser;
        this.intrebareDAO = new IntrebareDAO();
        this.testDAO = new TestDAO();
        this.nr = findAll().size() + 1;
    }

    public IUser getUser() {
        return iUser;
    }

    public void setUser(IUser user) {
        this.iUser = user;
    }

    public Intrebare findById(int id) {
        Intrebare st = intrebareDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Intrebare with id =" + id + " was not found!");
        }
        return st;
    }

    public Test insertTest(Test test) {
        return testDAO.insert(test);
    }

    public ArrayList<Test> findAll(){
        ArrayList<Test> list = new ArrayList<Test>();
        list = (ArrayList<Test>) testDAO.findAll();

        return list;
    }

    public void addTest(){
        ArrayList<Integer> intrebari = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt((10 - 1) + 1) + 1;
            intrebari.add(num);
        }

        this.intrebariRezerva = intrebari.stream().map(Object::toString).collect(Collectors.joining(", "));

        String text = "";
        String raspunsuri = "";

        for(int i = 0; i < 10; i ++){
            text += findById(intrebari.get(i)).getIntrebare() + "\n";
            raspunsuri += findById(intrebari.get(i)).getRaspuns() + "\n";
        }
        this.raspunsuri = raspunsuri;

        iUser.addTest(text);
    }

    public void getPunctajTest(String raspunsuri, String intrebari){
        String[] strValues = raspunsuri.split("\n");
        String[] raspunsuriCorecte = this.raspunsuri.split("\n");

        int punctaj = 0;

        for (int i = 0; i < strValues.length; i ++) {
            if(strValues[i].equals(raspunsuriCorecte[i]))
                punctaj ++;
        }

        insertTest(new Test(nr, punctaj, this.intrebariRezerva));
        nr++;
        iUser.getRaspunsuri(punctaj);
    }

    public void setFrameToMain(){
        iUser.changeFrameToMain();
    }

}
