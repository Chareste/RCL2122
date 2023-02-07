package Ass4;

import java.util.ArrayList;
import java.util.Random;


public class Tutor {
    static ArrayList<Computer> computers = new ArrayList<>(20); //lista  computer
    private int tesistiQueue,s,p,t,total;
    private final ArrayList<Integer> profQueue= new ArrayList<>(20); //queue professori per pc
    Random rand = new Random();

    public Tutor(int s, int p, int t){
        for(int i=0;i<20;i++)profQueue.add(0);
        for(int i=0;i<20;i++)computers.add(new Computer());
        tesistiQueue=0;
        this.s=s;
        this.t=t;
        this.p=p*20;


    }

    private synchronized int getComputerStudente(){
        for(int i=0;i<20;i++)
            if(computers.get(i).isUsing() && profQueue.get(i)==0 && (i!=0 || tesistiQueue==0)) return i;
        return -1;
    }


    public synchronized int requestComputerProfessore(Professore p){
        int curr;
        while(true){
            curr = rand.nextInt(20);
            if(!p.visited.get(curr)){
                System.out.println("Professore "+p.getId()+": in attesa di computer "+ curr +" libero");
                profQueue.set(curr,profQueue.get(curr)+1);
                while(computers.get(curr).isUsing()){


                    try { wait();
                    } catch (InterruptedException e) {}
                }

                computers.get(curr).setUse(true);
                profQueue.set(curr,profQueue.get(curr)-1);
                break;
            }
        }
        return curr; //metti in for fino a 20
    }

    public synchronized void requestComputerTesista(Tesista t){
        tesistiQueue++;
        System.out.println("Tesista "+t.getId()+": in attesa di computer 0 libero");
        while(profQueue.get(0)!=0 || computers.get(0).isUsing()){

            try { wait();
            } catch (InterruptedException e) {}
        }
        computers.get(0).setUse(true);
        tesistiQueue--;
    }

    public synchronized int requestComputerStudente(Studente s){
        while(this.getComputerStudente()==-1){ //pc tutti occupati
            System.out.println("Studente "+s.getId()+": in attesa di un computer libero");
            try { wait();
            } catch (InterruptedException e) {}
        }
        int id = this.getComputerStudente();
        computers.get(id).setUse(true);
        return id;
    }

    public synchronized void releaseComputer(int u, int id){
        computers.get(id).setUse(false);
        notifyAll();
        if(u==0)s--;
        if(u==1)t--;
        if(u==2)p--;
        System.out.printf("s: %d | t: %d | p: %d\n",s,t,p);
    }
}
