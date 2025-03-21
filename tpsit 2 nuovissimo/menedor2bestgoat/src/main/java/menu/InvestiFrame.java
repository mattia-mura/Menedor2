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

    public InvestiFrame() {

        setTitle("Investimento");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(215,215,215));

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
        importoLable.setForeground(Color.cyan);
        importoLable.setFont(new Font("Sans Serif", Font.BOLD, 32));
        importoLable.setHorizontalAlignment(SwingConstants.CENTER);

        importoField = new JTextField(15);
        importoField.setFont(new Font("Sans Serif", Font.BOLD, 16));
        importoField.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        JButton[] bottoni = {breveT, medioT, lungoT, bassoR, medioR, altoR};

        for (JButton jButton : bottoni) {
            jButton.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            jButton.setBackground(new Color(219,219,219));
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(true);
            jButton.setBorder(new LineBorder(Color.black, 1));
            jButton.setPreferredSize(new Dimension(25, 100));
            jButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(new Color(194,194,194));
                }

                public void mouseExited(MouseEvent e) {
                    jButton.setBackground(new Color(219,219,219));
                }
            });
        }

        annulla.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        annulla.setBackground(new Color(184,184,184));
        annulla.setForeground(Color.black);
        annulla.setFocusPainted(false);
        annulla.setBorderPainted(true);
        annulla.setBorder(new LineBorder(Color.black, 1));
        annulla.setPreferredSize(new Dimension(100, 25));
        annulla.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                annulla.setBackground(new Color(177,177,177));
            }

            public void mouseExited(MouseEvent e) {
                annulla.setBackground(new Color(184,184,184));
            }
        });

        investi.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        investi.setBackground(Color.green);
        investi.setForeground(Color.black);
        investi.setFocusPainted(false);
        investi.setBorderPainted(true);
        investi.setBorder(new LineBorder(Color.black, 1));
        investi.setPreferredSize(new Dimension(100, 25));
        investi.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                investi.setBackground(new Color(194,194,194));
            }

            public void mouseExited(MouseEvent e) {
                investi.setBackground(Color.green);
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

        investi.addActionListener(e -> investi());

        add(panel);
        setVisible(true);

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

    public void investi() {

        String text = importoField.getText();
        double soldi;
        if (text != null) {
            try {
                soldi = Double.parseDouble(text);

                if (soldi > 0) {
                    this.importo = soldi;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero maggiore di 0.", "Errore", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
        add(panel);
        setVisible(true);
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

}
