package labs.example.loops;

public class ForLoop {

    public static void main(String[] args) { // not taking any arguments
        ForLoop loop = new ForLoop(); //instantiating the class like we did with the while loop
        loop.executeForLoop();  // calling the private method for executing for loop.


        int result = loop.sumTwoNumbers(12, 6); // calling the sumTwoNumbers method and setting 'result' equal to the return value of the method.
        --result; // decrementing result we got from the sumTwoNumbers method by 1 using the prefix decrement.
        System.out.println("The decremented value is: " + result); // printing the decremented result
        

        int sumOfCounter = loop.sumLoopCounter(10);// we are creating sumofCounter and setting it = to return of sumLoopCounter. Using 10 as the argument.
        System.out.println("The sum of the loop counter is: " + sumOfCounter);// just printing the sum of the loop counter. 
     
        loop.printMultiplicationTable(5); //calling the printmulttable method and using 5 as the argument to print the table for 5
    } //close main method

    private void executeForLoop() { // this method is private only called in this class.
        int counter = 0; // we are initializing the counter here rather than inside the loop.
        for (; counter < 20; counter++) { // postfix incrementing the counter
            System.out.println("Loop counter value: " + counter); // we get the print out only until 19 because 0 is included and 20 is not since we are using < rather than <=
        } } // close executeForLoop method


    private int sumTwoNumbers(int num1, int num2) { // using the numbers given in the SumTwoNumbers method as parameters
        int sum = num1 + num2; // just adding the two numbers given in the sumTwoNumbers method
        return sum;// 
    } // close sumTwoNumbers method

    private int sumLoopCounter(int max) {// this will sum the loop counter from 1 to the whatever the max is (SET THE MAX TO 10 IN THE MAIN METHOD)
        int sum = 0;
        int a = 1; // initializing outside of the for loop and starting it at 1 since we want to start the sum at 1 rather than 0
        for (;a <= max; a++) { //postfix incrementing a until it is greater than max
            sum += a;// we are adding a to sum each time the loop goes through
        }
        return sum;
    }// close sumLoopCounter method

    private void printMultiplicationTable(int number){
        System.out.println("Multiplication Table for " + number);
        int i = 0; // initializing i outside of the loop and starting it at 0 since we want to include 0 in the multiplication table
        for (; i <= 12; i++) { // we are printing the multi table for the number given in the main method (5) from 0 to 12
            int result = number * i;// we are multiplying the number given in the main method (5) by i which is the loop counter
            System.out.println(number + " x " + i + " = " + result); // printing the multiplication table
        }
    } // close printMultiplicationTable method
} // close ForLoop class
                    
