package main.java.accesso;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccessoFrame extends JFrame {

    private final JPanel panel;
    private GridBagConstraints gbc;

    private JLabel nomeUtente;
    private JLabel creaPass;
    private JLabel riprovaPass;
    private JLabel passUtente;

    private JTextField nU;

    private JPasswordField passLogin;
    private JPasswordField passRegistra;
    private JPasswordField passRiprova;

    private JButton registrazione;
    private JButton avanzamento;
    private JButton accessione;
    private JButton creazione;
    private JButton indietrimento;
    private AccessoUtenteMain aU = new AccessoUtenteMain();

    private String[] datiUtente = new String[2];

    public AccessoFrame() {

        //size del frame
        setTitle("Login");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //non si allarga

        //creo pannello
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(215,215,215));

        //creo dei limiti che posso modificare
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10); //padding
        gbc.anchor = GridBagConstraints.CENTER;

        //elementi prima pagina
        nomeUtente = new JLabel("Username:");
        creaPass = new JLabel("Crea Password:");
        riprovaPass = new JLabel("Reinserisci la Password:");
        passUtente = new JLabel("Password:");

        JLabel[] labels = {nomeUtente,creaPass,riprovaPass,passUtente};

        for (JLabel label : labels){
            label.setForeground(Color.black);
            label.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        }

        nU = new JTextField(15);

        passLogin = new JPasswordField(15);
        passRegistra = new JPasswordField(15);
        passRiprova = new JPasswordField(15);

        // Aggiungo i placeholder per i campi di password
        aggiungiPlaceholder(passLogin, "Inserisci la password");
        aggiungiPlaceholder(passRegistra, "Crea una password");
        aggiungiPlaceholder(passRiprova, "Ripeti la password");


        // Aggiungo il placeholder ai campi di testo
        aggiungiPlaceholder(nU, "Inserisci il tuo username");

        aggiungiPlaceholder(passLogin, "Inserisci la password");
        aggiungiPlaceholder(passRegistra, "Crea una password");
        aggiungiPlaceholder(passRiprova, "Ripeti la password");

        JTextField[] testo = {nU,passLogin,passRegistra,passRiprova};

        for (JTextField field : testo){
            field.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            field.setBorder(new LineBorder(Color.black, 1));
        }


//        registrazione = new JButton("Registrati");
//        avanzamento = new JButton("Avanza");
//        accessione = new JButton("Accedi");
//        creazione = new JButton("Crea");
//        indietrimento = new JButton("Indietro");//per il meme:)

        registrazione = new RoundedButton("Registrati");
        avanzamento = new RoundedButton("Avanza");
        accessione = new RoundedButton("Accedi");
        creazione = new RoundedButton("Crea");
        indietrimento = new RoundedButton("Indietro");

        JButton[] bottoni = {registrazione, avanzamento, accessione, creazione, indietrimento};

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


        registrazione.addActionListener(e -> registrazione());
        avanzamento.addActionListener(e -> avanzamento());
        accessione.addActionListener(e -> accessione());
        creazione.addActionListener(e -> creazione());
        indietrimento.addActionListener(e -> indietrimento());


        loginUsername();
        add(panel);
        setVisible(true);

    }

    public void registrazione(){

        String username = getTestoValido(nU, "Inserisci il tuo username");
        datiUtente[0] = username;

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username non può essere vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
        }else{
            registra();
        }

    }

    private void registra(){
        panel.removeAll();
        gbc.gridy = 0; gbc.gridx = 0;
        panel.add(creaPass,gbc);
        gbc.gridx=1;panel.add(passRegistra,gbc);
        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(riprovaPass,gbc);
        gbc.gridx=1;panel.add(passRiprova,gbc);
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(indietrimento,gbc);
        gbc.gridy = 2; gbc.gridx = 1;
        panel.add(creazione,gbc);
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(indietrimento,gbc);
        panel.updateUI();//aggiorna

    }

    private void avanzamento() {

        String username = getTestoValido(nU, "Inserisci il tuo username");
        datiUtente[0] = username;

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username non può essere vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
        }else {
            avanti(username);
        }

    }
    private void avanti(String username){
        panel.removeAll();
        gbc.gridy = 0; gbc.gridx = 0;
        panel.add(passUtente,gbc);
        gbc.gridx=1;panel.add(passLogin,gbc);
        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(indietrimento,gbc);
        gbc.gridy = 1; gbc.gridx = 1;
        panel.add(accessione,gbc);

        panel.updateUI();//aggiorna
    }

    private void accessione() {

        String password = getPasswordValida(passLogin, "Inserisci la password");
        String utente =getTestoValido(nU, "Inserisci il tuo username");
        datiUtente[1] = password;

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password non può essere vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            if (aU.findUtent(utente,password)){
                if(AccessoUtenteMain.findUtent(utente, password)){
                    JOptionPane.showMessageDialog(null, "Accesso effettuato!");
                    dispose(); // Procedi con l'accesso
                } else {
                    JOptionPane.showMessageDialog(null, "L'account non esistente", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "eroor", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void creazione() {

        String password = getPasswordValida(passRegistra, "Crea una password");
        String passwordRiprova = getPasswordValida(passRiprova, "Ripeti la password");
        String utente = getTestoValido(nU, "Inserisci il tuo username");
        datiUtente[0] = utente;
        datiUtente[1] = password;

        if (password.isEmpty() && passwordRiprova.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le password non possono essere vuote", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            if (password.equals(passwordRiprova)) {
                    if(AccessoUtenteMain.addUtent(utente, password)){
                        JOptionPane.showMessageDialog(null, "Registrazione effettuata!");
                        dispose(); // Procedi con la registrazione
                    } else {
                        JOptionPane.showMessageDialog(null, "Account già esistente", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
            } else {
                JOptionPane.showMessageDialog(null, "Le password non coincidono", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private void indietrimento() {
        loginUsername();
    }

    private void loginUsername(){

        panel.removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomeUtente, gbc);
        gbc.gridx = 1;
        panel.add(nU, gbc);
        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(registrazione,gbc);
        gbc.gridx=1;
        panel.add(avanzamento,gbc);
        panel.updateUI();
    }

    private void aggiungiPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Metodo per ottenere il testo senza il placeholder
    private String getTestoValido(JTextField field, String placeholder) {
        String text = field.getText().trim();
        return text.equals(placeholder) ? "" : text;
    }

    private String getPasswordValida(JPasswordField passwordField) {
        char[] password = passwordField.getPassword();
        return (password == null || password.length == 0) ? "" : new String(password);
    }


    private void aggiungiPlaceholder(JPasswordField field, String placeholder) {
        // Impostiamo un testo fittizio per il placeholder
        field.setEchoChar((char) 0); // Per evitare che venga mostrato qualcosa quando c'è il placeholder
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText(""); // Pulisce il campo al focus
                    field.setEchoChar('*'); // Rende visibili i caratteri di password
                    field.setForeground(Color.BLACK); // Cambia il colore del testo
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (String.valueOf(field.getPassword()).trim().isEmpty()) {
                    field.setText(placeholder); // Ripristina il placeholder
                    field.setEchoChar((char) 0); // Nasconde la password
                    field.setForeground(Color.GRAY); // Cambia il colore del testo a grigio
                }
            }
        });
    }

    // Metodo per ottenere la password senza il placeholder
    private String getPasswordValida(JPasswordField passwordField, String placeholder) {
        char[] password = passwordField.getPassword();
        // Se il testo è uguale al placeholder, restituisci una stringa vuota
        return (password == null || password.length == 0 || String.valueOf(password).equals(placeholder)) ? "" : new String(password);
    }

    public String[] getDatiUtente() {
            return datiUtente;
    }

}
