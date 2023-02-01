package Lez1;

import java.util.Calendar;

class DatePrinterRunnable implements Runnable{
    public static void main(String[] args){
    DatePrinterRunnable runnable = new DatePrinterRunnable();
    Thread thread = new Thread(runnable);

    thread.start();
    System.out.println(Thread.currentThread().getName());

    }
    @Override
    public void run() {
        while (true) {
            System.out.println(Calendar.getInstance().getTime());
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrotta");
                return 0;
            }
        }
    }
}
