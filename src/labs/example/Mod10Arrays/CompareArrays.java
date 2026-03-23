package labs.example.Mod10Arrays;
public class CompareArrays { 
    public static void main(String[] args) {
        int[] firstArray = {1, 2, 3, 4, 5}; // establishing two arrays to compare
        int[] secondArray = {1, 2, 3, 4, 5};
        boolean sameLength = false; // creating a variable to compare length, starting with false because we will set it to true if we find they are the same length
        boolean sameValues = true; // creating a variable to compare values, starting with true because we will set it to false if we find any differences
        //setting up a check for length
        if (firstArray.length == secondArray.length) { // simple if statement to compare length of arrays
            sameLength = true; // if the length of both are the same then sameLength is true
        } //close length check
        //setting up a check for values
        if (sameLength) { // only running if the sameLength if was true
            for (int a = 0; a < firstArray.length; a++) { // for loop to check each value in the array
                if (firstArray[a] != secondArray[a]) { // if any value is different then were setting sameValues to false and breaking the loop. 
                    sameValues = false;
                    break; // we break the loop because we already know the arrays are not the same so there is no need to continue checking
                }// closing if statement
            }// closing for loop
        } else { 
            sameValues = false; // if the length is not the same then we can automatically set sameValues to false
        }// closing else statement
        if (sameLength) { // simple if statement to print length check results
            System.out.println("Yes, these arrays are the same length.");
        } else {
            System.out.println("No, these arrays are not the same length.");
        }// closing if statement for length check

        if (sameValues) { // simple if statement to print value check results
            System.out.println("Yes, these arrays contain the same values.");
        } else {
            System.out.println("No, these arrays do not contain the same values.");
        }// closing if statement for value check
    } // closing main method
}