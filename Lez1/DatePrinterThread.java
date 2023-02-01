package Lez1;

import java.util.Calendar;

/******* Lez 1 ex 2 ******/
public class DatePrinterThread extends Thread {
    public static void main(String[] args){
        DatePrinterThread thread = new DatePrinterThread();
        thread.start();
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void run(){
        while(true){
            System.out.println(Calendar.getInstance().getTime());
            System.out.println(Thread.currentThread().getName());
            try{Thread.sleep(2000);}
            catch (InterruptedException e) {
                System.out.println("Sleep interrotta");
            }
        }
    }
}
