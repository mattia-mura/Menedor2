package main.java.menu;

import main.java.accesso.RoundedButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvestiFrame extends JFrame {

    private int tempo = 0;
    private int rischio = 0;
    private int nInvestimenti = 0;
    private double importo = 0;

    private final JPanel panel;
    private final GridBagConstraints gbc;

    private JButton breveT;
    private JButton medioT;
    private JButton lungoT;

    private JButton bassoR;
    private JButton medioR;
    private JButton altoR;

    private JButton annulla;
    private JButton investi;

    private final JLabel importoLable;//scrtta IMPORTO
    private JTextField importoField;//quadratino IMPORTO

    int nMaxInvestimenti = 5;
    //public int getNMaxInvestimenti(){return nMaxInvestimenti;}
    int tempInvestimento[] = new int [nMaxInvestimenti]; //mesi mancanti alla fine dell'investimento.
    //public int[] getTempInvestimento(){return tempInvestimento;}
    double Investimenti[] = new double [nMaxInvestimenti]; //contiene gli investimentin fino al suo return
    //public double[] getInvestimenti(){return Investimenti;}

    int probabilitaGuadagno = 0;
    int percentualeMinMaxGuadagno[] = new int[2];
    int percentualeMinMaxPerdita[] = new int[2];

    public InvestiFrame() {

        setTitle("Investimento");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(215, 215, 215));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        breveT = new JButton("BREVE DURATA");
        medioT = new JButton("MEDIA DURATA");
        lungoT = new JButton("LUNGA DURATA");

        bassoR = new JButton("BASSO RISCIO");
        medioR = new JButton("MEDIO RISCIO");
        altoR = new JButton("ALTO RISCIO");

        annulla = new RoundedButton("ANNULLA");
        investi = new RoundedButton("INVESTI");

        importoLable = new JLabel("Importo");
        importoLable.setForeground(Color.black);
        importoLable.setFont(new Font("Sans Serif", Font.BOLD, 32));
        importoLable.setHorizontalAlignment(SwingConstants.CENTER);

        importoField = new JTextField(15);
        importoField.setFont(new Font("Sans Serif", Font.BOLD, 16));
        importoField.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        JButton[] bottoni = {breveT, medioT, lungoT, bassoR, medioR, altoR};

        for (JButton jButton : bottoni) {
            jButton.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            jButton.setBackground(new Color(219, 219, 219));
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(true);
            jButton.setBorder(new LineBorder(Color.black, 1));
            jButton.setPreferredSize(new Dimension(25, 100));
            jButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(new Color(194, 194, 194));
                }

                public void mouseExited(MouseEvent e) {
                    jButton.setBackground(new Color(219, 219, 219));
                }
            });
        }

        annulla.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        annulla.setBackground(new Color(184, 184, 184));
        annulla.setForeground(Color.black);
        annulla.setFocusPainted(false);
        annulla.setBorderPainted(true);
        annulla.setBorder(new LineBorder(Color.black, 1));
        annulla.setPreferredSize(new Dimension(100, 25));
        annulla.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                annulla.setBackground(new Color(177, 177, 177));
            }

            public void mouseExited(MouseEvent e) {
                annulla.setBackground(new Color(184, 184, 184));
            }
        });

        investi.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        investi.setBackground(new Color(184, 184, 184));
        investi.setForeground(Color.black);
        investi.setFocusPainted(false);
        investi.setBorderPainted(true);
        investi.setBorder(new LineBorder(Color.black, 1));
        investi.setPreferredSize(new Dimension(100, 25));
        investi.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                investi.setBackground(new Color(177, 177, 177));
            }

            public void mouseExited(MouseEvent e) {
                investi.setBackground(new Color(184, 184, 184));
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(breveT, gbc);
        gbc.gridy = 1;
        panel.add(medioT, gbc);
        gbc.gridy = 2;
        panel.add(lungoT, gbc);
        gbc.gridy = 3;
        panel.add(annulla, gbc);
        //gbc.gridy = 4; panel.add(investi, gbc);

        breveT.addActionListener(ignore -> {
            tempo = 1;
            Rischio();
        });

        medioT.addActionListener(ignore -> {
            tempo = 2;
            Rischio();
        });

        lungoT.addActionListener(ignore -> {
            tempo = 3;
            Rischio();
        });

        bassoR.addActionListener(ignore -> {
            rischio = 1;
            importo();
        });
        medioR.addActionListener(ignore -> {
            rischio = 2;
            importo();
        });

        altoR.addActionListener(ignore -> {
            rischio = 3;
            importo();
        });

        annulla.addActionListener(e -> dispose());

        investi.addActionListener(e -> investi(tempo, rischio));

        add(panel);
        setVisible(true);

    }

    public boolean investi(int tempo, int rischio) {

        if (tempo == 1 ) {
            tempInvestimento [nInvestimenti]= 1;
        } else {
            if (tempo == 2 ) {
                tempInvestimento [nInvestimenti]= 5;
            } else {
                tempInvestimento [nInvestimenti]= 10;
            }
        }

        if(rischio == 1) {
            probabilitaGuadagno = 95;
            percentualeMinMaxGuadagno[0] = 5;
            percentualeMinMaxGuadagno[1] = 10;
            percentualeMinMaxPerdita[0] = 5;
            percentualeMinMaxPerdita[1] = 8;
        }

        if(rischio == 2) {
            probabilitaGuadagno = 60;
            percentualeMinMaxGuadagno[0] = 20;
            percentualeMinMaxGuadagno[1] = 30;
            percentualeMinMaxPerdita[0] = 15;
            percentualeMinMaxPerdita[1] = 25;
        }

        if(rischio == 3) {
            probabilitaGuadagno = 40;
            percentualeMinMaxGuadagno[0] = 60;
            percentualeMinMaxGuadagno[1] = 85;
            percentualeMinMaxPerdita[0] = 50;
            percentualeMinMaxPerdita[1] = 125;
        }

        String text = importoField.getText();
        double soldi = 0;
        if (text != null) {
            try {
                soldi = Double.parseDouble(text);

                if (soldi > 0) {
                    this.importo = soldi;
                } else {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero maggiore di 0.", "Errore", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }

        soldi = investimentoo(soldi,probabilitaGuadagno,percentualeMinMaxGuadagno,percentualeMinMaxPerdita);

        this.Investimenti[nInvestimenti] = soldi;
        this.nInvestimenti++;
        return true;
    }

    private void Rischio() {
        annulla();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(bassoR, gbc);
        gbc.gridy = 1;
        panel.add(medioR, gbc);
        gbc.gridy = 2;
        panel.add(altoR, gbc);
        gbc.gridy = 3;
        panel.add(annulla, gbc);
//        gbc.gridy = 4;
//        panel.add(investi, gbc);
        panel.updateUI();
    }

    public void annulla() {
        panel.removeAll();
        panel.updateUI();
    }



    private void importo() {
        annulla();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(importoLable, gbc);
        gbc.gridy = 1;
        panel.add(importoField, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        panel.add(investi, gbc);
        gbc.gridx = 1;
        panel.add(annulla, gbc);
        panel.updateUI();

    }

    public double investimentoo (double capitale, int probabilitaGuadagno, int rangeGuadagno[],int rangePerdita[]) {


        int probabilita = (int) (Math.random() * 100 + 1);

        if (probabilita <= probabilitaGuadagno) {
            int percentualeGuadagno = (int) (Math.random() * (rangeGuadagno[1] - rangeGuadagno[0] + 1)
                    + rangeGuadagno[0]);
            capitale *= 1 + (double) percentualeGuadagno / 100;
        } else {
            int percentualePerdita = (int) (Math.random() * (rangePerdita[1] - rangePerdita[0] + 1) + rangePerdita[0]);
            capitale *= 1 - (double) percentualePerdita / 100;
        }

        return capitale;
    }


}
