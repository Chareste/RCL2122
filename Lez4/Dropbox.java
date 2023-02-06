package Lez4;
import java.util.concurrent.ThreadLocalRandom;


class Dropbox {
    protected boolean full = false; //true se buf pieno
    protected int num; //valore buf

    /**
     * Attende che il buffer contenga un numero, poi lo recupera e lo ritorna
     * @param e indica l'interesse a consumare un numero pari o dispari
     * 			se e == True il numero contenuto è pari, altrimenti è dispari
     * @return numero consumato
     */
    public int take(boolean e) {

        String s = e ? "Pari" : "Dispari";

        while (!full || e == (num % 2 != 0)) { //num non è quello cercato
            System.out.println("Attendi per: " + s);
            try {
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        try {
            Thread.sleep((long) (Math.random()*1000));
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(s + " <-> " + num);
        full = false;
        return num;
    }

    /**
     * Attende che il buffer sia vuoto, poi inserisce n all'interno di esso
     * @param n intero da inserire nel buffer
     */
    public void put(int n) {
        while (full) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Producer ha inserito " + n);
        num = n;
        full = true;
    }
}