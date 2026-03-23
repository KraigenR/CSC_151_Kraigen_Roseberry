package labs.example.Mod10Arrays;

public class HighestArrayMethod {

    public static void main(String[] args) {
        int[] myArray = { // creating int myArray and filling it with 50 values to find the highest value in the array, just change the values in the array to find the highest value in a different set of numbers
            12, 45, 67, 34, 89, 23, 56, 78, 90, 11,
            22, 33, 44, 55, 66, 77, 88, 99, 100, 101,
            5, 10, 15, 20, 25, 30, 35, 40, 50, 60,
            70, 80, 91, 92, 93, 94, 95, 96, 97, 98,
            1, 2, 3, 4, 6, 7, 8, 9, 13, 14
        }; // close array initialization

        int highest = maxValue(myArray); // using the maxValue method to find the highest value in the array instead of writing out the for loop to find the highest value in the array
        System.out.println("The highest value in the myArray object is: " + highest); // printing the highest value in the array after we have found it using the maxValue method
    } // closing main method

    // Method to find the highest value in an array
    public static int maxValue(int[] arr) {
    int max = arr[0]; // we are initializing max to the first value in the array so we have a starting point for comparison
    for (int i = 0; i < arr.length; i++) { // we are using arr.length as the condition so we go through the whole array
        if (arr[i] > max) { // if the value at the current index is greater than max then we set max equal to that value
            max = arr[i]; 
        }// close if statement
    }// closing for loop
    return max;} // closing maxValue method
} // closing HighestArrayMethod class
