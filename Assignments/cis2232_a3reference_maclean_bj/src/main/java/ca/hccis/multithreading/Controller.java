package ca.hccis.multithreading;

import ca.hccis.multithreading.threads.Runner1;
import ca.hccis.multithreading.threads.Runner2;

import javax.swing.*;

public class Controller {

    public static final int MIN = 1;
    public static final int MAX = 1000000000;

    static void main(){

        String name = JOptionPane.showInputDialog("Name");

        IO.println("Name entered:"+name);


        IO.println("Multi-threading");

        long start = System.currentTimeMillis();
        System.out.println("Current time in ms: " + start);

        Runner1 runner1 = new Runner1();
        runner1.start();

        Thread runner2 = new Thread(new Runner2());
        runner2.start();

        try {
            runner1.join();
            runner2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Current time in ms: " + end);

        long duration = end - start;
        IO.println("Duration: " + duration + " ms");
    }
}
