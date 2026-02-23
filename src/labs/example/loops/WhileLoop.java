package labs.example.loops;
public class WhileLoop {

    public static void main(String[] args)  {
        WhileLoop loop = new WhileLoop();
        loop.executeWhileLoop();
                                            }

    private void executeWhileLoop(){
        int counter = 1; // starting value of counter 
        int iterations = 0; //how many iterations the loop has gone through
        while (counter <= 10){ 
            counter++;
            iterations++;
                             }
                                    
System.out.println("The loop executed " + iterations + " times.");
System.out.println("The loop is now complete.");
                                   }
                        }
            


