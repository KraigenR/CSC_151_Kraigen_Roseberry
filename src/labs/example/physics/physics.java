package labs.example.physics;

import java.lang.Math;

public class physics {
    //setting up the gravrity class
    final static double GRAVITY = 9.81;
    // adding the methods and equations
     public double getDistance(double velocity, double time) {
        return velocity * time;}
    public double getVelocity(double distance, double time) {
        return distance / time;}
    public double getMomentum(double mass, double velocity) {
        return mass * velocity;}
    public double getForce(double mass, double acceleration) {
        return mass * acceleration;}
    public double getWork(double force, double distance) {
        return force * distance;}
    public double getKineticEnergy(double mass, double velocity) {
        return 0.5 * mass * Math.pow(velocity, 2);}
    public double getPotentialEnergy(double mass, double height) {
        return mass * GRAVITY * height;}
    public double calculateAngle(int lengthone, int lengthtwo) { //using calcuate angle to find the angle using the two lengths. 
        double thetaRadians = Math.atan2(lengthone, lengthtwo);
        return Math.toDegrees(thetaRadians);}    
    public void logInvalidAngleInfo(double angleDegrees){
        System.out.println(" logging the angle " + angleDegrees + " degrees. This is not a right angle.");}
    public void logValidAngleInfo(double angleDegrees){
        System.out.println(" logging the angle " + angleDegrees + " degrees. This is a valid 3-4-5 triangle.");}
                    }