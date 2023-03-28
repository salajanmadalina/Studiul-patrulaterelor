package View;

import Presenter.GuestPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GuestGUI extends JFrame implements IGuest {
    private GuestPresenter presenter;
    private Color roz = new Color(204, 153, 153);
    private Color albastru = new Color(63, 127, 190);
    private Color verde = new Color(52, 136, 56);
    private static JFrame frame;
    private JTextArea textArea;
    private JButton btnSolicitareCont;
    private JButton btnAfisareDate;
    private JPanel panelCircle;
    private JPanel panel;
    private JButton btnBack;
    private JComboBox comboBox;
    private JScrollPane scrollBar;
    ArrayList<Integer> alX = new ArrayList<>();
    ArrayList<Integer> alY = new ArrayList<>();
    int x, y;

    public GuestGUI(){
        presenter = new GuestPresenter(this);

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 1125, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(538, 65, 260, 236);
        frame.getContentPane().add(panel);

        JLabel lblNewLabel = new JLabel("Deseneaza un patrulater!");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel.setBounds(538, 23, 260, 32);
        frame.getContentPane().add(lblNewLabel);

        textArea = new JTextArea();
        textArea.setBounds(39, 65, 479, 345);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(39, 75, 479, 345);
        frame.getContentPane().add(scrollBar);

        JLabel lblNewLabel_1 = new JLabel("Caracteristici patrulater");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(39, 23, 260, 27);
        frame.getContentPane().add(lblNewLabel_1);

        btnSolicitareCont = new JButton("Inregistreaza-te!");
        btnSolicitareCont.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnSolicitareCont.setBounds(866, 471, 213, 46);
        btnSolicitareCont.addActionListener(action -> {
            presenter.setFrameRegister();
        });
        frame.getContentPane().add(btnSolicitareCont);

        btnAfisareDate = new JButton("Apasa aici!");
        btnAfisareDate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnAfisareDate.setBounds(231, 471, 149, 46);
        btnAfisareDate.addActionListener(action -> {
            presenter.giveInfoToView();
        });
        frame.getContentPane().add(btnAfisareDate);

        panelCircle = new JPanel();
        panelCircle.setBounds(819, 65, 260, 236);
        frame.getContentPane().add(panelCircle);

        JLabel lblNewLabel_2 = new JLabel("Cercuri speciale");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel_2.setBounds(819, 21, 170, 36);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_1_1 = new JLabel("Nu ai un cont?");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel_1_1.setBounds(888, 434, 178, 27);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Doresti sa afli mai multe despre patrulaterul desenat?");
        lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblNewLabel_1_1_1.setBounds(49, 434, 559, 27);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        btnBack = new JButton("Inapoi");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnBack.setBounds(622, 471, 149, 46);
        btnBack.addActionListener(action -> {
            presenter.setFrameMain();
        });
        frame.getContentPane().add(btnBack);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"ROZ", "ALBASTRU", "VERDE"}));
        comboBox.setSelectedIndex(1);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBox.setBounds(538, 358, 162, 32);
        frame.getContentPane().add(comboBox);

        JLabel lblSelecteazaCuloareaPentru = new JLabel("Selecteaza culoarea pentru desen:");
        lblSelecteazaCuloareaPentru.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblSelecteazaCuloareaPentru.setBounds(538, 311, 342, 32);
        frame.getContentPane().add(lblSelecteazaCuloareaPentru);

        MouseListener m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                alX.add(x);
                alY.add(y);

                Graphics g = panel.getGraphics();
                if (comboBox.getSelectedItem().toString().equals("ROZ"))
                    g.setColor(roz);
                if (comboBox.getSelectedItem().toString().equals("ALBASTRU"))
                    g.setColor(albastru);
                if (comboBox.getSelectedItem().toString().equals("VERDE"))
                    g.setColor(verde);

                g.fillRect(x-3, y-3, 6, 6);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                alX.clear();
                alY.clear();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int[] xArray = new int[5];
                int[] yArray = new int[5];
                int i;

                for(i = 0; i < alX.size(); i ++){
                    xArray[i] = alX.get(i);
                    yArray[i] = alY.get(i);
                }

                xArray[i] = xArray[0];
                yArray[i] = yArray[0];

                Graphics g2 = panel.getGraphics();
                if (comboBox.getSelectedItem().toString().equals("ROZ"))
                    g2.setColor(roz);
                if (comboBox.getSelectedItem().toString().equals("ALBASTRU"))
                    g2.setColor(albastru);
                if (comboBox.getSelectedItem().toString().equals("VERDE"))
                    g2.setColor(verde);

                g2.drawPolyline(xArray, yArray, alX.size()+1 );
            }
        };
        panel.addMouseListener(m2);

        frame.setVisible(true);

    }

    @Override
    public void changeFrameToRegister() {
        frame.dispose();
        new MainView();
    }

    @Override
    public void giveInformation(String info, ArrayList<Integer> coords) {
        textArea.setText(info);

        Graphics g = panelCircle.getGraphics();
        super.paint(g);
        g.drawOval((coords.get(0) - coords.get(2)), (coords.get(1) - coords.get(2)), 2 * coords.get(2), 2 * coords.get(2));
        g.drawOval((coords.get(3) - coords.get(5)), (coords.get(4) - coords.get(5)), coords.get(5) * 2, coords.get(5) * 2);
    }

    @Override
    public ArrayList<Integer> getInfoFromView() {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < 4; i ++){
            result.add(alX.get(i));
            result.add(alY.get(i));
        }
        return result;
    }

    @Override
    public void changeFrameToMain() {
        frame.dispose();
        new MainView();
    }

}
