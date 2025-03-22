package main.java.accesso;

import java.io.*;
import java.util.Scanner;

public class GestioneAccount {

    public GestioneAccount () {

    }


    public boolean addUtente(String nomeUtente,String password) {

        File f = new File(nomeUtente+".txt");
        if ( !f.exists() ) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FileWriter f1;
            try {
                f1 = new FileWriter(nomeUtente+".txt",true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                f1.write(password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }//addUtente

    public boolean rimuoviUtente(String nomeUtente,String password) {

        File f = new File(nomeUtente+".txt");

        Scanner scanner;
        try {
            scanner = new Scanner (new FileReader(nomeUtente+".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if ( f.exists() ) {
            String riga = scanner.nextLine();
            if ( password.equals(riga) ){
                if  (f.delete() ){return true;}
            }
        }
        return false;
    }

    public boolean login(String nomeUtente,String password)  {
        File f = new File(nomeUtente+".txt");
        Scanner scanner;
        try {
            scanner = new Scanner (new FileReader(nomeUtente+".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if ( f.exists() ){
            String riga = scanner.nextLine();
            if ( password.equals(riga) ){
                return true;
            }
        }
        return false;
    }//accesso


    private void addCronologia( String nomeUtente,String s ) {
        FileWriter f;
        try {
            f = new FileWriter(nomeUtente+".txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            f.write("\n"+s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
