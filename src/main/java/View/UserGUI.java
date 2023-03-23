package View;

import Presenter.UserPresenter;

import javax.swing.*;
import java.awt.*;

public class UserGUI extends JFrame implements IUser{
    private UserPresenter presenter;
    private static JFrame frame;
    private JTextArea textArea;
    private JButton btnTest;
    private JButton btnGenereazaPunctaj;
    private JTextArea textRaspunsuri;
    private JTextArea textPunctaj;
    private JButton btnBack;

    public UserGUI() {
        presenter = new UserPresenter(this);

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 1125, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(39, 65, 479, 345);
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        JLabel lblNewLabel_1 = new JLabel("Intrebari test");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(39, 23, 260, 27);
        frame.getContentPane().add(lblNewLabel_1);

        btnTest = new JButton("Genereaza Test");
        btnTest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnTest.setBounds(39, 441, 187, 46);
        btnTest.addActionListener(action -> {
            presenter.addTest();
        });
        frame.getContentPane().add(btnTest);

        textRaspunsuri = new JTextArea();
        textRaspunsuri.setEditable(true);
        textRaspunsuri.setBounds(600, 65, 479, 345);
        frame.getContentPane().add(textRaspunsuri);

        JLabel lblNewLabel_1_1 = new JLabel("Raspunsurile tale");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel_1_1.setBounds(600, 23, 260, 27);
        frame.getContentPane().add(lblNewLabel_1_1);

        textPunctaj = new JTextArea();
        textPunctaj.setEditable(false);
        textPunctaj.setBounds(923, 441, 156, 51);
        textPunctaj.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        frame.getContentPane().add(textPunctaj);

        JLabel lblNewLabel_1_1_1 = new JLabel("Punctajul obtinut ");
        lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel_1_1_1.setBounds(704, 460, 248, 27);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        btnGenereazaPunctaj = new JButton("Genereaza punctaj");
        btnGenereazaPunctaj.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnGenereazaPunctaj.setBounds(236, 441, 217, 46);
        btnGenereazaPunctaj.addActionListener(action -> {
            presenter.getPunctajTest(textRaspunsuri.getText(), textArea.getText());
        });
        frame.getContentPane().add(btnGenereazaPunctaj);

        btnBack = new JButton("Inapoi");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnBack.setBounds(463, 441, 156, 46);
        btnBack.addActionListener(action -> {
            presenter.setFrameToMain();
        });
        frame.getContentPane().add(btnBack);


        frame.setVisible(true);
    }

    @Override
    public void addTest(String test) {
        textArea.setText(test);
    }

    @Override
    public void getRaspunsuri(int punctaj) {
        textPunctaj.setText(punctaj + "");
    }

    @Override
    public void changeFrameToMain() {
        frame.dispose();
        new MainView();
    }
}
