package com.model.phone;

public class OperationSystem {
    private String destination;
    private int version;

    public String getDestination() {
        return destination;
    }

    public OperationSystem(String destination, int version) {
        this.destination = destination;
        this.version = version;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    @Override
    public String toString() {
        return "OperationSystem{" +
                "destination='" + destination + '\'' +
                ", version=" + version +
                '}';
    }
}