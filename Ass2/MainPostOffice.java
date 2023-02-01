package Ass2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

public class MainPostOffice {
    public static void main(String[] args){
        Office office = new Office();
        BlockingQueue<OfficeClient> sala = new LinkedBlockingQueue<>();

        int i = 0;

        do {
            if (i <= 200) {sala.add(new OfficeClient()); i++;}

            OfficeClient client = sala.peek();
            try {
                office.manageClient(client);
                sala.poll();
            } catch (RejectedExecutionException e) {
                System.out.println("Sala 2 piena");
            }

        }while(!sala.isEmpty());
        office.chiudiSala();
    }

}


//coda senza limite
//coda con limite k
//threadpool fixed 4