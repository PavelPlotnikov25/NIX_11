package com.thread.robot;

import com.thread.Resources;

import java.util.Random;

public class LastRobot extends Thread {

    Resources instance = Resources.getInstance();
    Random random = new Random();

    @Override
    public void run() {
        while (true) {
            while (instance.getFinalDetailCount() < 100 && instance.getMicroschemaProgressCount() >= 100) {
                int fuelConsumption = random.nextInt(350, 750);
                System.out.println("Robot need " + fuelConsumption + " fuel");
                System.out.println("Robot have " + instance.getFuelCount() + " fuel");
                if (fuelConsumption < instance.getFuelCount()) {
                    instance.setFinalDetailCount(instance.getFinalDetailCount() + 10);

                    instance.setFuelCount(instance.getFuelCount() - fuelConsumption);
                    System.out.println("Progress " + instance.getFinalDetailCount());
                    if (instance.getFinalDetailCount() == 100) {
                        instance.setCompleteWork(true);
                        System.out.println("WORK IS FINISHED");
                        Thread.currentThread().interrupt();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
