package labs.example.physics;

import java.lang.Math;

public class Physics {
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
}