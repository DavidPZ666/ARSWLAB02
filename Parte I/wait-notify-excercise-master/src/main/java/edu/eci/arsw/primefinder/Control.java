/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.util.Scanner;

/**
 *
 */
public class Control extends Thread {

    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 3000;
    private final static int TMILISECONDS = 5000;
    private final int NDATA = MAXVALUE / NTHREADS;
    private PrimeFinderThread pft[];
    public Object pivote;


    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];
        this.pivote = new Object();
        Scanner entrada=new Scanner(System.in);

        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA, pivote);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1, pivote);
    }
    
    public static Control newControl() {
        return new Control();
    }

    @Override
    public void run() {
        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
        }
    }


    public void notificar(){
        synchronized (pivote){
            pivote.notifyAll();
        }
    }




    
}
