package Lez4;

public class DropboxMonitor extends Dropbox{

    @Override
    public synchronized int take(boolean e) {
        String s = e ? "Pari" : "Dispari";

        while (!full || e == (num % 2 != 0)){ //num non è quello cercato
            try { wait();
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
        }
        System.out.println(s + " <-> " + num);
        full = false;
        this.notifyAll();
        return num;

    }

    @Override
    public synchronized void put(int n) {
        while (full) {
            try { wait();
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
        }
        System.out.println("Producer ha inserito " + n);
        num = n;
        full = true;
        this.notify();
    }
}
