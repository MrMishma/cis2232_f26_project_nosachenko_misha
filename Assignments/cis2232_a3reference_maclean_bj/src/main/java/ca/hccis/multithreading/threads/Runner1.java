package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;

import javax.naming.ldap.Control;

public class Runner1 extends Thread{

    @Override
    public void run() {
        long total = 0;
        int end = Controller.MAX / 2;
        for (int i = Controller.MIN; i < end; i++) {
            total = total + i;
        }
        IO.println("Runner1 total: " + total);
    }

}
