package main.java.accesso;

import main.java.ContoBanca;
import main.java.Portafoglio;

import java.io.*;
import java.time.LocalDate;

public class AccessoUtenteMain {

        private static final String name = "CartellaUtenti/";

        public AccessoUtenteMain() {

            File CartellaUtenti = new File(name);
            if ( !CartellaUtenti.exists() ) {CartellaUtenti.mkdir();}
        }

        public static boolean addUtent(String nomeUtente,String password) { // controlla password e nomeUtente --> solo caratteri e numeri , no spazi
            File Utente = new File(name+nomeUtente+".txt");
            File Utente2 = new File(name+nomeUtente+".csv");
            if (Utente.exists() || Utente2.exists() ) { return false;}
            try ( BufferedWriter BW = new BufferedWriter (new FileWriter( Utente,true ) ) ){
                Utente.createNewFile();
                BW.write(password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try ( BufferedWriter bw = new BufferedWriter (new FileWriter( Utente2,true ) ) ){
                Utente2.createNewFile();
                bw.write(password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        public static boolean findUtent(String nomeUtente,String password) {
            File Utente = new File(name+nomeUtente+".txt");
            if ( Utente.exists() ) {
                try ( BufferedReader BW = new BufferedReader (new FileReader( Utente ) ) ){
                    //Utente.createNewFile();
                    String passwordUtent = BW.readLine();
                    if( passwordUtent.equals(password) ) {return true;}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }//accesso

        public boolean addInfo (String nomeUtente, Portafoglio portafoglio, ContoBanca contoBanca, LocalDate localDate) {
            File Utente = new File(name+nomeUtente+".txt");
            if ( Utente.exists() ) {
                BufferedWriter BW;

                try {
                    BW = new BufferedWriter(new FileWriter(nomeUtente + ".csv", true));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String s = portafoglio.getSchei()+";"+contoBanca.getSaldo()+";"+ localDate.getDayOfMonth()+";"+localDate.getMonthValue()+";"+ localDate.getYear();
                try {
                    BW.write("\n" +s);//cosa devi scrivere
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
            return false;
        }//addInfo

}//class


