package main.java.accesso;


import main.java.menu.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {

        AccessoFrame accessoFrame = new AccessoFrame();

        accessoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                SwingUtilities.invokeLater(() -> new MainFrame(accessoFrame.getDatiUtente()));
            }
        });
    }

}
