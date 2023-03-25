package Presenter;

import Model.Dao.UserDAO;
import Model.User;
import View.IMainView;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MainPresenter {
    private IMainView mainView;
    private UserDAO userDAO;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        this.userDAO = new UserDAO();
    }

    public User findById(int id) {
        User st = userDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The User with id =" + id + " was not found!");
        }
        return st;
    }

    public ArrayList<User> findAll(){
        ArrayList<User> list = new ArrayList<User>();
        list = (ArrayList<User>) userDAO.findAll();

        return list;
    }

    public User insertUser(User user) {
        return userDAO.insert(user);
    }

    public void setFrameGuestMode(){
        mainView.changeFrameToGuest();
    }

    public void insereazaUser(){
        String nume = mainView.getUsername();
        String parola = mainView.getPassword();
        if(!nume.isEmpty() && !parola.isEmpty()){
            int id = findAll().size() + 2;
            insertUser(new User(nume, parola, "ELEV", id));

        }
    }

    public void setFrameUserOrAdmin(){
        ArrayList<User> users = findAll();

        for(User user: users){
            if(mainView.getUsername().equals(user.getNume())){
                if(user.getRol().equals("ADMIN")){
                    mainView.changeFrameToAdmin();
                }
                else{
                    mainView.changeFrameToUser();
                }
            }
        }
    }

}
