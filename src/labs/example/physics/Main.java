package labs.example.physics;

public class Main {
 public static void main (String[] args)                                        {
    physics physics = new physics();
    //getting Distance 
        double distance = physics.getDistance(60, 2);
        System.out.println("Distance: " + distance + " miles");
    //getting Velocity
        double velocity = physics.getVelocity(120, 2);
        System.out.println("Velocity: " + velocity + " mph");
    //getting Momentum
        double momentum = physics.getMomentum(10, 5);
        System.out.println("Momentum: " + momentum + " kg m/s");
    //getting Force
        double force = physics.getForce(20, 9.8);
        System.out.println("Force: " + force + " kg m/s^2");
    //getting Work
        double work = physics.getWork(50, 10);
        System.out.println("Work: " + work + " Joules");
    //getting Kinetic Energy
        double kineticEnergy = physics.getKineticEnergy(15, 3);
        System.out.println("Kinetic Energy: " + kineticEnergy + " Joules");
    //getting Potential Energy
        double potentialEnergy = physics.getPotentialEnergy(10, 20);
        System.out.println("Potential Energy: " + potentialEnergy + " Joules");
                                                                                }
                    }
