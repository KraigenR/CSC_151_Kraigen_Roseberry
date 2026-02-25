package labs.example.physics;

public class Main {
 public static void main (String[] args)                                         {
    physics physics = new physics();
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

       
       // Go back and rewrite this using the 
        //caculating the angle of a triangle
        int lengthone = (3);//first portion of the angle add your value here
        int lengthtwo = (4);//second portion of the angle add your value here
        //-----double thetaRadians = Math.atan2(lengthone, lengthtwo); // this finds the Radians first
        //-----double angleDegrees = Math.toDegrees(thetaRadians); // this then uses the internal math.pi to convert the radians to degrees. 
        //-----public static double getTheta(int lengthone, int lengthtwo){
        //-----double angleDegrees = Math.atan2(lengthone,lengthtwo) * 180/3.14152; //this returns the radians then uses the 180/3.14152 to get degrees.
        //-----return theta;}
        double angleDegrees = physics.calculateAngle(lengthone, lengthtwo); //we're now manually converting radians to degrees when using the caculateAngle method inside physcis.java
        if (angleDegrees > 37.00 || angleDegrees < 36.87) {
        physics.logInvalidAngleInfo(angleDegrees);} 
        else 
        {physics.logValidAngleInfo(angleDegrees);}

        // Part 2 of lab
        double lightSpeed = physics.getLightSpeedInMPH(); //pulling from physics.java where Lightspeed is set
        double timeToEarth = physics.getTimeFromSunToEarthInHours(); //physics.java sets value for time from sun
        double calculatedDistance = physics.getDistance(lightSpeed, timeToEarth);
        System.out.println("Calculated distance from the Earth to the Sun " + calculatedDistance + " miles");
        double knownDistance = physics.getKnownDistanceToEarth();
        if (calculatedDistance == knownDistance) {
        System.out.println("The calculated distance matches the known distance.");}
        else {physics.logEarthToSunInvalidDistance(calculatedDistance);}
        

    }
}