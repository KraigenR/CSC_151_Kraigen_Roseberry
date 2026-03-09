package labs.example.arrays;

public class Main {
    public static void main(String[] args) { 
        ArrayOperations ops = new ArrayOperations(); // we are instantiating the ArrayOperations class so we can call the methods in it
        ops.createNewArray(5); // Adjust this value to change the size of the array, we are calling the createNewArray method from step 1 and using 5 as the argument 
        ops.getDaysAndMonths(); // we are calling the getDaysAndMonths method to print the number of days in each month
        int[] unsortedarray = new int[50]; // creating array called unsortedarray, setting it's size to 50
            for (int i = 0; i < unsortedarray.length; i++) { // using a for loop to process the array and fill with random numbers, running until it's less than 50 since that's the size of the array
                int num; // we are initializing num here to use as the random number we generate in the loop
                boolean exists; // we are initializing exists here to use as a boolean to check if the random number we generated already exists in the array so we don't have any duplicates in the array
            do { // using the do while loop to generate random numbers and check if they are duplicates until we have a unique random number to put in the array
                num = (int)(Math.random() * 100); // Math.random generates a double between 0.0 and 1.0, which we * by 100 to get a number between 0 and 99 then putting int in front to remove decimal
                exists = false; // we are setting exists to false at the beginning of each loop so we can check if the new random number we generated exists in the array
                
                for (int j = 0; j < i; j++) { // for loop to go through the array checking if the  random # is a duplicate. We are using j < i because we only want to check the numbers in the array that have already been filled with random numbers, not the whole array since we haven't filled it yet.
                    if (unsortedarray[j] == num) { // checking if that random # is equal to any of the numbers in the array, if so it will set exists to true and break out of the loop so we can generate a new random number
                        exists = true; // if we find a duplicate we set exists to true so we can generate a new random number in the do while loop
                        break; // breaking the loop since duplicate was found
                                                 } // close if statement
                                            } //close for loop that checks for duplicates
               } // close do statement
            while (exists); // if exists is true we will go back to the beginning of the do while loop and generate a new random number and check for duplicates again until we get a unique random number
            unsortedarray[i] = num; // once a unique number is generated we will the current index to that number and move on to the next index in the array to fill it with another random number
                                                          } //close for loop that fills the array with random numbers
            ops.sortArray(unsortedarray); // calling sortArray method and using the unsortedarray we just filled with random numbers as the argument to sort the array and print the sorted values
                                            } // close main method
} // close Main class                   
