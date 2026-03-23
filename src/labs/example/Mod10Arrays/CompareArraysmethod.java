package labs.example.Mod10Arrays;

public class CompareArraysmethod {

    public static void main(String[] args) {
        int[] firstArray = {1, 2, 3, 4, 5}; // Establishing two arrays to compare
        int[] secondArray = {1, 2, 3, 4, 5};

        // Check length
        if (areSameLength(firstArray, secondArray)) { // Using the method to check if the arrays are the same length
            System.out.println("Yes, these arrays are the same length.");
        } else {
            System.out.println("No, these arrays are not the same length.");
        } // closing if statement for length check

        // Check values
        if (areEqual(firstArray, secondArray)) { // Using the method to check if the arrays contain the same values
            System.out.println("Yes, these arrays contain the same values.");
        } else {
            System.out.println("No, these arrays do not contain the same values.");
        } // closing if statement for value check
    } // closing main method

    // Method to check if arrays are the same length
    public static boolean areSameLength(int[] arr1, int[] arr2) { // Simply comparing the length of the two arrays and returning us true or false
        return arr1.length == arr2.length;
    } // closing areSameLength method

    // Method to check if arrays contain the same values
    public static boolean areEqual(int[] arr1, int[] arr2) { // First we see if arrays are the same legth, then we loop through the arrays and compare each value. If any value is different we return a false. If all values are equal we return true
        if (!areSameLength(arr1, arr2)) return false; // if the arrays are not the same length we can automatically return false because they can't be the same if they don't have the same number of values
        for (int i = 0; i < arr1.length; i++) { // incrementing through the arrays and comparing each value at the same index. We can use either arr1.length or arr2.length because we already know they are the same length from the previous check
            if (arr1[i] != arr2[i]) return false; // if any value is different we can immediately return false because the arrays are not the same. We don't need to continue checking if we already know they are different
        } // closing for loop 
        return true;
    } // closing areEqual method
} // closing CompareArraysmethod class