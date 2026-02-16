package labs.example.physics;

public class Main {
 public static void main (String[] args)                                        {
    Physics physics = new Physics();
    //getting Distance 
        double distance = physics.getDistance(50, 2);
        System.out.println("Distance is " + distance + " miles");
    //getting Velocity
        double velocity = physics.getVelocity(100, 2);
        System.out.println("Velocity is " + velocity + " mph");
    //getting Momentum
        double momentum = physics.getMomentum(60, 2);
        System.out.println("Momentum is " + momentum + " kg m/s");
    //getting Force
        double force = physics.getForce(40, 2);
        System.out.println("Force is " + force + " kg m/s^2");
    //getting Work
        double work = physics.getWork(70, 2);
        System.out.println("Work is " + work + " Joules");
    //getting Kinetic Energy
        double kineticEnergy = physics.getKineticEnergy(30, 2);
        System.out.println("Kinetic Energy is " + kineticEnergy + " Joules");
    //getting Potential Energy
        double potentialEnergy = physics.getPotentialEnergy(30, 2);
        System.out.println("Potential Energy is " + potentialEnergy + " Joules");
                                                                                }
                    }