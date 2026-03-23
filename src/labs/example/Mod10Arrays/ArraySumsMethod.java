package labs.example.Mod10Arrays;

public class ArraySumsMethod {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5}; // Initializing three arrays to sum
        int[] arr2 = {5,9,9,2,9,10,9,8,7,6,5,4,3,2,1,10,9,8,7,6,5,4,3,2,1};
        int[] arr3 = {2,4,6,8,10,1,3,5,7,9,2,4,6,8,10,1,3,5,7,9,2,4,6,8,10};

        int sumArr1 = sumArray(arr1); // Using the sumArray method to calculate the sum of each array instead of writing out the for loop for each one
        int sumArr2 = sumArray(arr2); 
        int sumArr3 = sumArray(arr3);

        System.out.println("Sum of array 1: " + sumArr1); // Printing the sums of each array
        System.out.println("Sum of array 2: " + sumArr2);
        System.out.println("Sum of array 3: " + sumArr3);

        // Find the largest sum
        int maxSum = sumArr1; // Starting with sumArr1 as the maxSum and then comparing it to the other sums to find the largest one
        String maxArray = "arr1"; // Keeping track of which array has the largest sum so we can print that out at the end

        if (sumArr2 > maxSum) { // Comparing sumArr2 to maxSum and if it's greater we set maxSum equal to sumArr2 and maxArray equal to arr2
            maxSum = sumArr2; // Updating maxSum to the new largest sum
            maxArray = "arr2"; // Updating maxArray to the name of the array that has the new largest sum only if our check finds that sumArr2 is greater than the current maxSum
        } // Closing if statement for sumArr2 check
        if (sumArr3 > maxSum) { // Same as above but for sumArr3
            maxSum = sumArr3;
            maxArray = "arr3";
        } // Closing if statement for sumArr3 check

        System.out.println("The array with the largest sum is " + maxArray + " with a sum of " + maxSum); // Printing out which array has the largest sum and the value of that sum
    } // closing main method

    // Method to sum the elements of an array
    public static int sumArray(int[] arr) { 
        int sum = 0; // Initializing sum to 0 because we will be adding to it as we loop through the array
        for (int i = 0; i < arr.length; i++) { // Looping through the array using arr.length as the condition so we go through the whole array
            sum += arr[i]; // Adding the value of each index in the array to sum. This is the same as sum = sum + arr[i] but using the += operator to make it more concise
        }
        return sum; // Returning the final sum after we have looped through the entire array
    } // closing sumArray method
} // closing ArraySumsMethod class