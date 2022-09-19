package com.thread;

import com.thread.robot.FirstRobot;
import com.thread.robot.LastRobot;
import com.thread.robot.RobotFour;
import com.thread.robot.SecondAndThirdRobot;

public class Main {


    public static void main(String[] args) {
        FirstRobot firstRobot = new FirstRobot();
        SecondAndThirdRobot secondRobot = new SecondAndThirdRobot();
        SecondAndThirdRobot thirdRobot = new SecondAndThirdRobot();
        RobotFour robotFour = new RobotFour();
        LastRobot lastRobot = new LastRobot();
        firstRobot.start();
        secondRobot.start();
        thirdRobot.start();
        robotFour.start();
        lastRobot.start();
    }
}
