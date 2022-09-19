package com.thread.robot;

import com.thread.Resources;

import java.util.Random;

public class SecondAndThirdRobot extends Thread {

    Resources instance = Resources.getInstance();

    @Override
    public void run() {
        while (true){
        while (instance.getBaseConstuctionCount() < 100){
            int progress = new Random().nextInt(10,20);
            System.out.println("Base details complete - " + progress + " points");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int totalProgress = instance.getBaseConstuctionCount() + progress;
            System.out.println("Total progress is " + totalProgress + " points");
            instance.setBaseConstuctionCount(totalProgress);

        }
        }
    }
}
