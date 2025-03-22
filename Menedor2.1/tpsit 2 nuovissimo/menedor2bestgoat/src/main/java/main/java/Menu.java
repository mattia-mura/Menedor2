package main.java;

import java.util.Scanner;

public class Menu {

    private Scanner tastiera = new Scanner(System.in);

    private int n;
    private int x;

    public Menu (int x) {
        this.x = x;
    }


    public int scelta(){
        boolean ok = true;
        int scelta = -1;
        do {
            if ( x == 1 ) {
                n = 6;
                menuPrincipale();
            }
            if ( x == 2 ) {
                n = 3;
                menuDurataInvestimento();
            }
            if ( x == 3 ) {
                n = 3;
                menuRiskLevel();
            }
            String intero = tastiera.nextLine();
            intero.trim();
            try {

                scelta = (int) Integer.parseInt(intero);

            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Non e' stato inserito un intero!");
                ok=false;
            }
            if (scelta<0 || scelta>this.n) {ok=false;}
        }while(!ok);
        return scelta;
    }


    public static void menuPrincipale() {
        System.out.println("-----MENU-----");
        System.out.println("1. DEPOSITARE");
        System.out.println("2. PRELEVARE");
        System.out.println("3. INVESTIRE");
        System.out.println("4. Visualizzare lo stato del conto in banca");
        System.out.println("5. Visualizzare lo stato del portafoglio");
        System.out.println("6. Mese successivo\n");

        System.out.println("0. Esci.");
    }


    public static void menuDurataInvestimento() {
        System.out.println("-----MENU DURATA DELL'INVESTIMENTO-----");
        System.out.println("1. BREVE");
        System.out.println("2. MEDIA");
        System.out.println("3. LUNGA");
    }

    public static void menuRiskLevel() {
        System.out.println("-----MARGINI DI RISCHIO E GUADAGNO-----");
        System.out.println("1. BASSO");
        System.out.println("2. MEDIO");
        System.out.println("3. ALTO");
    }

}
