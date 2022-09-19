package com.thread.robot;

import com.thread.Resources;

import java.util.Random;

public class FirstRobot extends Thread{
    Resources instance = Resources.getInstance();


    @Override
    public void run() {
        while (true) {
            System.out.println("ROBOT ONE " + Thread.currentThread().getName() + "started");
            while (!instance.isCompleteWork()) {
                int takenFuel = new Random().nextInt(500, 1000);
                System.out.println("First robot took " + takenFuel + " galoons of fuel");
                int totalTakenFuel = instance.getFuelCount() + takenFuel;
                instance.setFuelCount(totalTakenFuel);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Total count of TRANSPORTED fuel galoons " + totalTakenFuel);
            }
        }
    }
}
