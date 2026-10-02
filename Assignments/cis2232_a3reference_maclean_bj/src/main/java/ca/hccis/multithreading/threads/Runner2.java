package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;

public class Runner2 implements Runnable{
    @Override
    public void run() {
        long total = 0;

        int start = Controller.MAX / 2 + 1;


        for (int i = start; i < Controller.MAX; i++) {
            total = total + i;
        }
        IO.println("Runner2 total: " + total);
    }

}
