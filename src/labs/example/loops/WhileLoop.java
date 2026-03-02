package labs.example.loops;
public class WhileLoop{
    public static void main(String[] args) { // not taking any arguments
        WhileLoop loop = new WhileLoop(); //instantiating the class
        loop.executeWhileLoop();}   // calling the private method. This method is private only called in this class.
    
    private void executeWhileLoop() { 
        int counter = 1;       // counter starting at 1
        int iterations = 0;    // tracks number of loop runs, we start at 0 because we haven't run the loop yet

        while (counter <= 10) {  // loop runs 10 times
            counter++;           // increment counter should start at 1 since we are using a while loop and will postfix add after the loop runs.
            iterations++;        // increment iteration count
        }

        System.out.println("The loop executed " + iterations + " times. The loop is now complete.");}
                    }       

    