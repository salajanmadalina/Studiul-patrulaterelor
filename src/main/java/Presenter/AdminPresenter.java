package Presenter;

import Model.Dao.UserDAO;
import Model.User;
import View.IAdmin;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AdminPresenter {
    private IAdmin iAdmin;
    private UserDAO userDAO;

    public AdminPresenter(IAdmin admin) {
        this.iAdmin = admin;
        this.userDAO = new UserDAO();
    }

    public ArrayList<User> getAllUsers(){
        return (ArrayList<User>) userDAO.findAll();
    }

    public User insertUser(User user) {
        return userDAO.insert(user);
    }

    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    public void updateUser(String field, String value, int id) {
        userDAO.update(field, value, id);
    }

    public User findById(int id) {
        User st = userDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The User with id =" + id + " was not found!");
        }
        return st;
    }

    public void vizualizareUsers(){
        String info = "";
        ArrayList<User> users = getAllUsers();
        for(User u: users){
            info += u.toString();
        }
        iAdmin.listaUtilizatori(info);
    }

    public void stergeUser(){
        String id = iAdmin.getId();
        deleteUser(Integer.parseInt(id));
    }

    public void modificaUser(){
        String id = iAdmin.getId();
        String nume = iAdmin.getNume();
        String parola = iAdmin.getParola();
        if(!nume.isEmpty())
            updateUser("nume", nume, Integer.parseInt(id));
        if(!parola.isEmpty())
            updateUser("parola", parola, Integer.parseInt(id));
    }

    public void insereazaUser(){
        String id = iAdmin.getId();
        String nume = iAdmin.getNume();
        String parola = iAdmin.getParola();
        String rol = iAdmin.getRol();
        if(!nume.isEmpty() && !parola.isEmpty() && !rol.isEmpty())
            insertUser(new User(nume, parola, rol, Integer.parseInt(id)));
    }

    public void setFrameToMain(){
        iAdmin.changeFrameToMain();
    }
}
