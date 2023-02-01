package Lez2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MainPower {
    public static void main(String[] args){
        int n;
        int acc = 0;
       ArrayList<Future<Integer>> tasks = new ArrayList<>();
        Scanner in=new Scanner(System.in);

        try {n = in.nextInt();} //ricevo n da input
        catch (InputMismatchException e){throw e;}

        ThreadPoolExecutor powerExec;

        powerExec=(ThreadPoolExecutor) Executors.newCachedThreadPool();
        for(int i=2;i<=50;i++) {
           tasks.add(powerExec.submit(new Power(n, i)));
        }
        for(Future<Integer> t: tasks){
            try {
                acc += t.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Result:"+ acc);

    }


}
