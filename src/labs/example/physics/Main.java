package labs.example.physics;

public class Main {
 public static void main (String[] args)                                         {
    Physics physics = new Physics();
    //getting Distance 
        double distance = physics.getDistance(50, 2);//Velocity and Time within the ()
        System.out.println("Distance is " + distance + " miles");
    //getting Velocity
        double velocity = physics.getVelocity(100, 2);//distance and time
        System.out.println("Velocity is " + velocity + " mph");
    //getting Momentum
        double momentum = physics.getMomentum(60, 2);//mass and velocity
        System.out.println("Momentum is " + momentum + " kg m/s");
    //getting Force
        double force = physics.getForce(40, 2);//mass and acceleration
        System.out.println("Force is " + force + " kg m/s^2");
    //getting Work
        double work = physics.getWork(70, 2);//force and distance
        System.out.println("Work is " + work + " Joules");
    //getting Kinetic Energy
        double kineticEnergy = physics.getKineticEnergy(30, 2);//mass and velocity
        System.out.println("Kinetic Energy is " + kineticEnergy + " Joules");
    //getting Potential Energy
        double potentialEnergy = physics.getPotentialEnergy(30, 2);//mass and height
        System.out.println("Potential Energy is " + potentialEnergy + " Joules");
        public void trackangle(){
        int lengthone = (3);//first portion of the angle add your value here
        int lengthtwo = (4);//second portion of the angle add your value here 
        double thetaRadians = Math.atan2(lengthone, lengthtwo);
        double thetaDegrees = Math.toDegrees(thetaRadians);
        if (thetaDegrees < 36.87 | | thetaDegrees > 37.00) 
             {physics.logInvalidAngleInfo(thetaDegrees);}
        else { physics.logValidAngleInfo(thetaDegrees);}}
    //
                                                                                }}