package main.java.menu;

import main.java.ContoBanca;
import main.java.Main;
import main.java.Portafoglio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel portafoglio;
    private JLabel banca;
    private JLabel data;
    private JButton deposita;
    private JButton prelieva;
    private JButton investi;
    private JButton transizioni;
    private JButton avanzaMese;
    private JButton tris;
    private JButton salvaEEsci;
    private String[] datiUtenti;

    public MainFrame(String[] datiUtenti) {
        this.datiUtenti=datiUtenti;

        //size del frame
        setTitle("Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        //creo pannello
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(215,215,215));

        //creo dei limiti che posso modificare
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10); //padding
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        //elementi prima pagina
        portafoglio = new JLabel("Soldi solidi:");
        banca = new JLabel("Soldi virtuali:");
        data = new JLabel("Mesi:");

        JLabel[] labels = {portafoglio, banca, data};

        for (JLabel label : labels) {
            label.setForeground(Color.black);
            label.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        }

        deposita = new JButton("DEPOSITA");
        prelieva = new JButton("PRELIEVA");
        investi = new JButton("INVESTI");
        transizioni = new JButton("TRANSIZIONI");
        avanzaMese = new JButton("AVANZA MESE");
        tris = new JButton("TRIS");
        salvaEEsci = new JButton("SALVA ED ESCI");

        JButton[] bottoni = {deposita, prelieva, investi, transizioni, avanzaMese, tris};

        for (JButton jButton : bottoni) {
            jButton.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            jButton.setBackground(new Color(219,219,219));
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(true);
            jButton.setBorder(new LineBorder(Color.black, 1));
            jButton.setPreferredSize(new Dimension(100, 25));

            jButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(new Color(194,194,194));
                }

                public void mouseExited(MouseEvent e) {
                    jButton.setBackground(new Color(219,219,219));
                }
            });
        }

        salvaEEsci.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        salvaEEsci.setBackground(new Color(184,184,184));
        salvaEEsci.setForeground(Color.black);
        salvaEEsci.setFocusPainted(false);
        salvaEEsci.setBorderPainted(true);
        salvaEEsci.setBorder(new LineBorder(Color.black, 1));
        salvaEEsci.setPreferredSize(new Dimension(100, 25));
        salvaEEsci.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                salvaEEsci.setBackground(new Color(177,177,177));
            }

            public void mouseExited(MouseEvent e) {
                salvaEEsci.setBackground(new Color(184,184,184));
            }
        });

        deposita.addActionListener(e -> deposita(new Portafoglio(0),new ContoBanca(0)));
        prelieva.addActionListener(e -> prelieva());
        investi.addActionListener(e -> nuovoInvestimento() );
        transizioni.addActionListener(e -> transizioni());
        avanzaMese.addActionListener(e -> avanzaMese());
        tris.addActionListener(e -> tris());
        salvaEEsci.addActionListener(e -> salvaEEsci());


        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(portafoglio, gbc);
        gbc.gridx = 1;
        panel.add(banca, gbc);
        gbc.gridx = 2;
        panel.add(data, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;

        panel.add(deposita, gbc);
        gbc.gridx = 1;
        panel.add(prelieva, gbc);
        gbc.gridx = 2;
        panel.add(investi, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        panel.add(transizioni, gbc);
        gbc.gridx = 1;
        panel.add(tris, gbc);
        gbc.gridx = 2;
        panel.add(avanzaMese, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;

        panel.add(salvaEEsci, gbc);


        add(panel);
        setVisible(true);

    }

    public void deposita(Portafoglio portafoglio, ContoBanca contoBanca) {
        String deposita = JOptionPane.showInputDialog(this, "Deposita:", "0");
        double importo = 0.0;
        try {
            importo = Double.parseDouble(deposita);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "scrivi un numero dio bell.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        Main.depositPreleva(1,importo, portafoglio, contoBanca);

    }

    public void prelieva() {

    }

    public void nuovoInvestimento() {

        InvestiFrame investmentiFrame = new InvestiFrame();

    }


    public void transizioni() {

    }

    public void avanzaMese() {

    }

    public void tris() {

    }

    public void salvaEEsci() {
        dispose();
    }


}
