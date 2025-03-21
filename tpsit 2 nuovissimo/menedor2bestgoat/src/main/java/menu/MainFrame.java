package main.java.menu;

import main.java.ContoBanca;
import main.java.Main;
import main.java.Portafoglio;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    LocalDate date = LocalDate.now();

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

    private Portafoglio soldiSolidi = new Portafoglio(0);
    private ContoBanca soldiVirtuali = new ContoBanca(0);


    public MainFrame(String[] datiUtenti) {
        this.datiUtenti = datiUtenti;

        //size del frame
        setTitle("Menu");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        //creo pannello
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(215, 215, 215));

        //creo dei limiti che posso modificare
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10); //padding
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        //elementi prima pagina
        portafoglio = new JLabel("Soldi solidi: " + soldiSolidi.getSchei());
        banca = new JLabel("Soldi virtuali: " + soldiVirtuali.getSaldo());
        data = new JLabel("Data: " + date);

        JLabel[] labels = {portafoglio, banca, data};

        for (JLabel label : labels) {
            label.setForeground(Color.black);
            label.setFont(new Font("Sans Serif", Font.PLAIN, 10));
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
            jButton.setBackground(new Color(219, 219, 219));
            jButton.setForeground(Color.black);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(true);
            jButton.setBorder(new LineBorder(Color.black, 1));
            jButton.setPreferredSize(new Dimension(100, 25));

            jButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    jButton.setBackground(new Color(194, 194, 194));
                }

                public void mouseExited(MouseEvent e) {
                    jButton.setBackground(new Color(219, 219, 219));
                }
            });
        }

        salvaEEsci.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        salvaEEsci.setBackground(new Color(184, 184, 184));
        salvaEEsci.setForeground(Color.black);
        salvaEEsci.setFocusPainted(false);
        salvaEEsci.setBorderPainted(true);
        salvaEEsci.setBorder(new LineBorder(Color.black, 1));
        salvaEEsci.setPreferredSize(new Dimension(100, 25));
        salvaEEsci.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                salvaEEsci.setBackground(new Color(177, 177, 177));
            }

            public void mouseExited(MouseEvent e) {
                salvaEEsci.setBackground(new Color(184, 184, 184));
            }
        });

        deposita.addActionListener(e -> deposita());
        prelieva.addActionListener(e -> prelieva());
        investi.addActionListener(e -> nuovoInvestimento());
        transizioni.addActionListener(e -> transizioni());
        avanzaMese.addActionListener(e -> avanzaMese());
        tris.addActionListener(e -> tris());
        salvaEEsci.addActionListener(e -> salvaEEsci());


        ImageIcon logoIcon = new ImageIcon("Logo_MenedorBank.PNG");
        JLabel logoLabel = new JLabel(logoIcon);

// Posizionamento del logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;  // Occupa tre colonne
        panel.add(logoLabel, gbc);

// Reset gridwidth dopo l'uso
        gbc.gridwidth = 1;

// Posizionamento delle etichette
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(portafoglio, gbc);
        gbc.gridx = 1;
        panel.add(banca, gbc);
        gbc.gridx = 2;
        panel.add(data, gbc);

// Posizionamento dei bottoni
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(deposita, gbc);
        gbc.gridx = 1;
        panel.add(prelieva, gbc);
        gbc.gridx = 2;
        panel.add(investi, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(transizioni, gbc);
        gbc.gridx = 1;
        panel.add(tris, gbc);
        gbc.gridx = 2;
        panel.add(avanzaMese, gbc);

// Posizionamento del bottone "Salva ed Esci"
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 3;  // Il bottone occupa tre colonne
        panel.add(salvaEEsci, gbc);

// Reset gridwidth dopo l'uso
        gbc.gridwidth = 1;


        add(panel);
        setVisible(true);

    }

    private void mostraMessaggio(String messaggio, boolean successo) {
        JOptionPane.showMessageDialog(this, messaggio, successo ? "Successo" : "Errore",
                successo ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    private void gestisciTransazione(int tipo) {
        String input = JOptionPane.showInputDialog(this, tipo == 1 ? "Deposita:" : "Prelieva:", "0");
        try {
            double importo = Double.parseDouble(input);
            boolean esito = Main.depositPreleva(tipo, importo, soldiSolidi, soldiVirtuali);
            mostraMessaggio(esito ? "Operazione completata" : "Operazione fallita", esito);
        } catch (NumberFormatException e) {
            mostraMessaggio("Inserisci un numero valido.", false);
        }
    }

    public void deposita() {
        gestisciTransazione(1);

        portafoglio.setText("Soldi solidi: " + soldiSolidi.getSchei());
        banca.setText("Soldi virtuali: " + soldiVirtuali.getSaldo());
        data.setText("Data: " + date);

        panel.revalidate();
        panel.repaint();
    }

    public void prelieva() {
        gestisciTransazione(2);

        portafoglio.setText("Soldi solidi: " + soldiSolidi.getSchei());
        banca.setText("Soldi virtuali: " + soldiVirtuali.getSaldo());
        data.setText("Data: " + date);

        panel.revalidate();
        panel.repaint();
    }

    public void nuovoInvestimento() {

        InvestiFrame investmentiFrame = new InvestiFrame();

        portafoglio.setText("Soldi solidi: " + soldiSolidi.getSchei());
        banca.setText("Soldi virtuali: " + soldiVirtuali.getSaldo());
        data.setText("Data: " + date);

        panel.revalidate();
        panel.repaint();

    }


    public void transizioni() {


    }

    public void avanzaMese() {
        soldiSolidi.aumentaSchei(100);
        date = date.plusMonths(1); // Aggiorna la data

        portafoglio.setText("Soldi solidi: " + soldiSolidi.getSchei());
        banca.setText("Soldi virtuali: " + soldiVirtuali.getSaldo());
        data.setText("Data: " + date);

        panel.revalidate();
        panel.repaint();
    }

    public void tris() {

    }

    public void salvaEEsci() {
        dispose();
    }


}
