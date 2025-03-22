package main.java;

import accesso.AccessoUtenteMain;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ContoBanca contoBancario = new ContoBanca(0);
        Portafoglio portafoglio = new Portafoglio(0);
        Menu menu1 = new Menu(1);
        Menu menu2 = new Menu(2);
        Menu menu3 = new Menu(3);

        Scanner tastiera = new Scanner(System.in);
        LocalDate data = LocalDate.now();
//        int mesi = 1;
//        int anno = 2025;
        int scelta = -1;
        double capitale = 0;
        int nMaxInvestimenti = 5;
        int tempInvestimento[] = new int [nMaxInvestimenti]; //mesi mancanti alla fine dell'investimento.
        double Investimenti[] = new double [nMaxInvestimenti]; //contiene gli investimentin fino al suo return
        for (int i=0;i<nMaxInvestimenti;i++){
            Investimenti[i] = -777777;
        }
        int nInvestimenti = 0;

        /*
        double returnsHolder[] = new double[nMaxInvestimenti];
        int anni[] = new int[nMaxInvestimenti]; // durate degli investimenti
        int tempMesi[] = new int[nMaxInvestimenti]; // mi serve per tenere traccia dell'inizio di ogni investimento
        int contAnni = 0;
        */
        boolean accesso = false;
        AccessoUtenteMain acs = new AccessoUtenteMain();
        String password="",nomeUtente="";


        do {


            System.out.println("- - - Benvenuti alla nostra banca - - -");

            if (!accesso){
                System.out.println();
                System.out.println("1-accedi");
                System.out.println("2-registrati");
                System.out.println("0-esci");
                int sceltaUtente = Tools.getIntero();
                while(sceltaUtente<0 || sceltaUtente>2) {
                    System.out.println("- - - Benvenuti alla nostra banca - - -");
                    System.out.println();
                    System.out.println("1-accedi");
                    System.out.println("2-registrati");
                    System.out.println("0-esci");
                }
                boolean enter = true;
                do {
                    if ( sceltaUtente == 1) {
                        System.out.print("Inserisci il nome utente : ");
                        nomeUtente = tastiera.nextLine(); // controlla nome
                        System.out.println();
                        System.out.print("Inserisci la password : ");
                        password = tastiera.nextLine(); //controlla password
                        System.out.println();
                        if (acs.findUtent(nomeUtente,password)) {
                            System.out.println("Accesso eseguito !");accesso = true;
                        }else{System.out.println("Accesso non eseguito !");enter = false;}
                    }//login
                    if ( sceltaUtente == 2) {
                        System.out.print("Inserisci il nome utente : ");
                        nomeUtente = tastiera.nextLine();
                        System.out.println();
                        System.out.print("Inserisci la password : ");
                        password = tastiera.nextLine();
                        System.out.println();
                        if (acs.addUtent(nomeUtente,password)) {
                            System.out.println("Utente creato con successo !");accesso = true;
                        }else{System.out.println("Utente non creato !");enter = false;}
                    }
                    if(sceltaUtente == 0) {scelta=0;}
                }while( !enter || scelta == 0 || !accesso);

            }
            if (scelta != 0) {
                System.out.println("Non hai soldi nel tuo portafoglio.");
                System.out.println("Desideri visualizzare lo stato del tuo account? o venire il mese successivo?");
                System.out.println("Inserisci cosa vuoi fare?");
                scelta = menu1.scelta();
            }


            switch (scelta) {
                case 1: {

                    double depositMoney ;

                    if ( portafoglio.getSchei() <= 0) {
                        System.out.println("Soldi non sufficiente nel portafoglio.");

                    } else {
                        System.out.println("Quanti soldi desideri depositare?");
                        depositMoney = Tools.getDouble();
                        depositMoney = Tools.cifreDopoVirgola(depositMoney);
                        if (depositMoney <= 30) {
                            System.out.println("Non puoi prelevare meno di 30$ [C'e' l'IVA] !");
                        }else {
                            if (!depositPreleva(scelta,depositMoney,portafoglio, contoBancario)) {
                                System.out.println("Soldi non sufficiente ne portafoglio.");
                            } else {

                                acs.addInfo(nomeUtente,portafoglio,contoBancario,data);
                            }//else3
                        }//else2
                    }//else1

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
                    System.out.println();
                    break;
                }
                case 2: {

                    double withdrawals;// --- > prelievi

                    if (contoBancario.getSaldo() <= 0) {
                        System.out.println("Non hai soldi in banca. Deposita.");

                    } else {
                        System.out.println("Quanti soldi desideri prelevare?");
                        withdrawals = Tools.getDouble();
                        withdrawals = Tools.cifreDopoVirgola(withdrawals);
                        if (withdrawals <= 30) {
                            System.out.println("Non puoi prelevare meno di 30$ [C'e' l'IVA] !");
                        }else {
                            if (!depositPreleva(scelta, withdrawals,portafoglio, contoBancario)) {
                                System.out.println("Non hai soldi sufficiente in banca.");
                            } else {
                                acs.addInfo(nomeUtente,portafoglio,contoBancario,data);
                            }//else3
                        }//else2
                    }//else1

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
                    System.out.println();
                    break;
                }
                case 3: {
                    int durata;
                    capitale = 0;
                    boolean ok = true;

                    if (nInvestimenti == nMaxInvestimenti) {
                        System.out.println("Non puoi fare altri investimenti. Hai gia fatto " + nMaxInvestimenti + " investimenti.");
                    } else {
                        if (contoBancario.getSaldo() <= 0) {
                            System.out.println("Non hai soldi in banca. Deposita.");
                        } else {

                            System.out.println("Somma da investire: ");
                            capitale = Tools.getDouble();
                            if (capitale <= 0) {
                                System.out.println("Bisogna investire una somma maggiore di 0");
                            }
                            else{

                                contoBancario.decrementaSaldo(capitale);

                                durata = menu2.scelta();
                                if (durata == 1 ) {
                                     tempInvestimento [nInvestimenti]= 1;
                                } else {
                                    if (durata == 2 ) {
                                        tempInvestimento [nInvestimenti]= 5;
                                    } else {
                                        tempInvestimento [nInvestimenti]= 10;
                                    }
                                }

                            int probabilitaGuadagno = 0;
                            int percentualeMinMaxGuadagno[] = new int[2];
                            int percentualeMinMaxPerdita[] = new int[2];

                            int rischio = menu3.scelta();
                            switch (rischio) {
                                case 1: {

                                    // Le probabilita' sono in percentuale
                                    probabilitaGuadagno = 95;
                                    percentualeMinMaxGuadagno[0] = 5;
                                    percentualeMinMaxGuadagno[1] = 10;
                                    percentualeMinMaxPerdita[0] = 5;
                                    percentualeMinMaxPerdita[1] = 8;

//								System.out.println("Premere un tasto per continuare...");
//								new java.util.Scanner(System.in).nextLine();
                                    System.out.println();
                                    break;
                                }
                                case 2: {

                                    probabilitaGuadagno = 60;
                                    percentualeMinMaxGuadagno[0] = 20;
                                    percentualeMinMaxGuadagno[1] = 30;
                                    percentualeMinMaxPerdita[0] = 15;
                                    percentualeMinMaxPerdita[1] = 25;

//								System.out.println("Premere un tasto per continuare...");
//								new java.util.Scanner(System.in).nextLine();
                                    System.out.println();
                                    break;
                                }
                                case 3: {

                                    probabilitaGuadagno = 40;
                                    percentualeMinMaxGuadagno[0] = 60;
                                    percentualeMinMaxGuadagno[1] = 85;
                                    percentualeMinMaxPerdita[0] = 50;
                                    percentualeMinMaxPerdita[1] = 125;

//								System.out.println("Premere un tasto per continuare...");
//								new java.util.Scanner(System.in).nextLine();
                                    System.out.println();
                                    break;
                                }
                            }
                            capitale = investimento(capitale, probabilitaGuadagno, percentualeMinMaxGuadagno,percentualeMinMaxPerdita);


                            if (Investimenti[nInvestimenti] == -777777) Investimenti[nInvestimenti] = capitale;
                            nInvestimenti++;
                            }

                        }
                    }

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
//					break;
                }
                case 4: {

                    System.out.println("Quantita' di soldi in banca: " + contoBancario.getSaldo());

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
                    System.out.println();
                    break;
                }
                case 5: {

                    System.out.println("Quantita' di soldi nel portafoglio: " + portafoglio.getSchei());

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
                    System.out.println();
                    break;
                }
                case 6: {

                    portafoglio.aumentaSchei(100);

//                    if (mesi == 12){
//                        mesi = 0;
//                        anno++;
//                    }
//                    mesi++;
                    data.plusMonths(1);

                    for (int i=0;i<nInvestimenti;i++){
                        tempInvestimento[i] --;
                        if (tempInvestimento[i]==0) {
                            System.out.println("Hai ricevuto sul tuo conto :" + Investimenti[i] + "\n Da un vecchio investimento!");
                            if (Investimenti[i] <= 0) {
                                contoBancario.decrementaSaldo(Investimenti[i]);
                            } else {
                                contoBancario.aumentaSaldo(Investimenti[i]);
                                Investimenti[i] = -777777;
                                scalaInvestimenti(Investimenti,nMaxInvestimenti);
                            }

                        }
                    }


//                    //vecchio codice
//                    for (int i = 0; i < contAnni;) {
//                        tempMesi[i]++;
//                        if (tempMesi[i] == anni[i] * 12) {
//                            contoBancario.aumentaSaldo(returnsHolder[i]);
//                            for (int j = i; j < contAnni - 1; j++) {
//                                anni[j] = anni[j + 1];
//                                returnsHolder[j] = returnsHolder[j + 1];
//                                tempMesi[j] = tempMesi[j + 1];
//                            }
//                            contAnni--;
//
//                            System.out.println("E' terminato il periodo del tuo investimento.");
//                            char carattere;
//                            do {
//                                System.out.println(
//                                        "Vuoi vedere quanto hai ricevuto? (premi 's' per dire \"si\" o 'n' per dire \"no\")");
//                                carattere = tastiera.next().charAt(0);
//                            } while (carattere != 's' && carattere != 'n');
//                            if (carattere == 's') {
//                                System.out.println("Risultato dell'investimento: " + returnsHolder[i]);
//                            }
//
//                        } else {
//                            i++;
//                        }
//                    }

//					System.out.println("Premere un tasto per continuare...");
//					new java.util.Scanner(System.in).nextLine();
                    System.out.println();
                    break;
                }
            }
        } while (scelta != '0');
        tastiera.close();
    }


    public static boolean depositPreleva (int scelta, double money, Portafoglio portafoglio, ContoBanca contoBancario) {
        if (scelta == 1) {
            if (money > portafoglio.getSchei()) {return false;}
            portafoglio.decrementaSchei(money);
            contoBancario.aumentaSaldo(money);
        }else {
            if (money > contoBancario.getSaldo()) {return false;}
            portafoglio.aumentaSchei(money);
            contoBancario.decrementaSaldo(money);

        }
        return true;

//		if ((money <= confronto) && (money > 0)) {
//			portafoglio.aumentaSchei(money);
//			contoBancario.decrementaSaldo(money);;
//			return true;
//		}
//		return false;

    }

//	public static double money (double x) {
//
//		return x*(-1);
//
//	}

    public static double investimento(double capitale, int probabilitaGuadagno, int rangeGuadagno[],int rangePerdita[]) {


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

    public static void scalaInvestimenti (double Investimenti[],int nMaxInvestimenti){
        for (int i=0;i<nMaxInvestimenti;i++){
            if (Investimenti[i] == -777777){
                for (int j=i;j<nMaxInvestimenti-1;j++){
                    Investimenti[i]=Investimenti[i+1];
                }
                Investimenti[nMaxInvestimenti-1] = -777777;
            }
        }

    }

}//class