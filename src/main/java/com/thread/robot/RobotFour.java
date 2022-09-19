package com.thread.robot;

import com.thread.Resources;

import java.util.Random;

public class RobotFour extends Thread{

    Resources instance = Resources.getInstance();
    Random random = new Random();




    @Override
    public void run() {
        while (true) {
            while (instance.getMicroschemaProgressCount() < 100 && instance.getBaseConstuctionCount() >= 100) {
                int brokenSchema = random.nextInt(1, 100);
                if (brokenSchema < 30) {
                    instance.setMicroschemaProgressCount(0);
                }
                int creatingMicroschemaProgress = random.nextInt(25, 35);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Robot 4 comlete - " + creatingMicroschemaProgress + " points of microschema");
                int totalProgress = instance.getMicroschemaProgressCount() + creatingMicroschemaProgress;
                instance.setMicroschemaProgressCount(totalProgress);
                System.out.println("Total microschema progress is - " + totalProgress);

                if (totalProgress >= 100){
                    System.out.println("ROBOT 4 FINISHED HIS WORK");
                }
            }
        }
    }
}
