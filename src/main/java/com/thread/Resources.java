package com.thread;

public class Resources {

    private volatile int fuelCount;
    private volatile int baseConstuctionCount;
    private volatile int microschemaProgressCount;
    private volatile int finalDetailCount;
    private boolean completeWork = false;

    private static volatile Resources instance;

    public static Resources getInstance(){
        if (instance == null){
            synchronized (Resources.class){
                if (instance == null){
                    instance = new Resources();
                }
            }
        }
        return instance;
    }

    public Resources() {
    }

    public synchronized int getFuelCount() {
        return fuelCount;
    }

    public synchronized int getBaseConstuctionCount() {
        return baseConstuctionCount;
    }

    public synchronized int getMicroschemaProgressCount() {
        return microschemaProgressCount;
    }

    public synchronized int getFinalDetailCount() {
        return finalDetailCount;
    }

    public synchronized boolean isCompleteWork() {
        return completeWork;
    }

    public synchronized void setFuelCount(int fuelCount) {
        this.fuelCount = fuelCount;
    }

    public synchronized void setBaseConstuctionCount(int baseConstuctionCount) {
        this.baseConstuctionCount = baseConstuctionCount;
    }

    public synchronized void setMicroschemaProgressCount(int microschemaProgressCount) {
        this.microschemaProgressCount = microschemaProgressCount;
    }

    public synchronized void setFinalDetailCount(int finalDetailCount) {
        this.finalDetailCount = finalDetailCount;
    }

    public synchronized void setCompleteWork(boolean completeWork) {
        this.completeWork = completeWork;
    }
}
