package View;

import Presenter.MainPresenter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements IMainView {
    private static JFrame frame;
    private JTextField userName;
    private JPasswordField passwordField;
    private JButton guestMode;
    private JButton logIn;
    private JTextField rolField;
    private MainPresenter presenter;
    private JButton btnRegister;

    public static void main(String[] args) {
        MainView view = new MainView();
    }

    public MainView(){
        presenter = new MainPresenter(this);
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 934, 503);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        guestMode = new JButton("Mod invitat");
        guestMode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        guestMode.setBounds(138, 209, 146, 50);
        guestMode.addActionListener(actionEvent ->{
            presenter.setFrameGuestMode();
        });
        frame.getContentPane().add(guestMode);

        logIn = new JButton("Conectare");
        logIn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        logIn.setBounds(442, 386, 146, 50);
        logIn.addActionListener(actionEvent -> {
            presenter.setFrameUserOrAdmin();
        });
        frame.getContentPane().add(logIn);

        userName = new JTextField();
        userName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        userName.setBounds(518, 176, 164, 50);
        frame.getContentPane().add(userName);
        userName.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordField.setBounds(518, 243, 164, 50);
        frame.getContentPane().add(passwordField);

        JLabel lblUsername = new JLabel("Nume:");
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblUsername.setBounds(414, 176, 94, 50);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Parola:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblPassword.setBounds(414, 241, 94, 50);
        frame.getContentPane().add(lblPassword);

        JLabel lblStudiulPatrulaterelor = new JLabel("STUDIUL PATRULATERELOR");
        lblStudiulPatrulaterelor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
        lblStudiulPatrulaterelor.setBounds(281, 31, 396, 80);
        frame.getContentPane().add(lblStudiulPatrulaterelor);

        rolField = new JTextField();
        rolField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rolField.setColumns(10);
        rolField.setBounds(518, 307, 164, 50);
        frame.getContentPane().add(rolField);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblRol.setBounds(414, 307, 94, 50);
        frame.getContentPane().add(lblRol);

        btnRegister = new JButton("Inregistreaza-te");
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRegister.setBounds(628, 386, 171, 50);
        btnRegister.addActionListener(action -> {
            presenter.insereazaUser(userName.getText(), String.valueOf(passwordField.getPassword()), rolField.getText());
        });
        frame.getContentPane().add(btnRegister);

        frame.setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        MainView.frame = frame;
    }


    @Override
    public String getUsername() {
        return userName.getText();
    }

    @Override
    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    @Override
    public String getRole() {
        return userName.toString();
    }

    @Override
    public void changeFrameToGuest() {
        frame.dispose();
        new GuestGUI();
    }

    @Override
    public void changeFrameToUser() {
        frame.dispose();
        new UserGUI();
    }

    @Override
    public void changeFrameToAdmin() {
        frame.dispose();
        new AdminGUI();
    }
}
