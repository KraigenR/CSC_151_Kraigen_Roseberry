package labs.example.arrays;

public class ArrayOperations {
    public void createNewArray(int value){ //this method creates a new array and uses the parameter as the starting value
        int[] array1 = new int[value]; // Adjust value in Main.java to change array size. we are creating an array of integers called array1 and setting the size to the value passed in the method
        //int a = 0; // we are initializing a to 0 to within the loop to use as the index for the array, however just in case we want to use a outside of the loop we can initialize it here
        for (int a = 0; a < array1.length; a++) { // using a for loop to go through the array and set each value equal to the index value so array1[0] should be 0, array1[1] should be 1. The loop will run until it's less than 10
        array1[a] = value + a; // setting value of each index in the array to be equal to the value in the method + the index value. So if 5 is set in the main method, array1[0] will be 5, array1[1] will be 6, array1[2] will be 7, and so on
                                                }// close for loop
        System.out.println("I created a new array and it now has " + array1.length + " items in it.");// just printing the array length
        System.out.println("The array items and their values are listed below: "); // print statement before our display
        displayArray(array1); 
                                        } // close createNewArray method
private void displayArray(int[] array1){ // method is private as we're only calling it in this class and it takes the array we made above as it's parameter
    for (int i = 0; i < array1.length; i++) { // using a for loop to go through the array and print out the index and value of each item in it. This loop should run until it's less than 10 since that's what we made the array
        System.out.println("Index " + i + ": " + array1[i]); // printing the index and value of each item in the array. Should show index 0: 5, index 1: 6 etc etc if we set the value to 5 in the main method.
                                            } // close for loop
                                        } // close displayArray method

public void sortArray(int[] array1) { // This is method so we can call it in the main method and it takes the array above as a parameter to sort
    int swap; // we are initializing swap here to use as a temporary variable to hold the value of the index we are swapping
    for (int i = 0; i < array1.length - 1; i++) { // Controlling number of passes 
        for (int o = 0; o < array1.length - 1 - i; o++) { // Controlling compairison in each pass. o is the index of the first item being compared, 0 + 1 is the second. We are using length - 1 - i because we want to compare the last item in the array to the second to last item in the first pass, then we want to compare the second to last item to the third to last item in the second pass and so on until we have compared all items in the array
           if (array1[o] > array1[o + 1]) { // we are comparing the value of the first item to the value of the second item and if the first item is greater than the second item we will swap them
        swap = array1[o]; // we are setting swap equal to the value of the first item being compared so we can hold onto that value while we swap the first and second items
        array1[o] = array1[o + 1]; // we are setting the value of the first item being compared equal to the value of the second item being compared so we have swapped the first item to the second item's position
        array1[o + 1] = swap; // we are setting the value of the second item being compared equal to the value of swap which is the original value of the first item being compared so we have swapped the second item to the first item's position
                                          } // close if statement
                                                        } // close inner for loop
                                                } // close outer for loop
System.out.println("The sorted array values are listed below:");
for (int i = 0 ; i < array1.length; i++){
    System.out.println(array1[i]);
                                        } // close for loop which prints the sorted array                 
                                    } // close sortArray method                               
    public void getDaysAndMonths() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; // we are creating an array of strings called months and filling it with the names of the months
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // creating an array of integers called days and filling it with the number of days in each month. 
        System.out.println("Number of days in each month:"); // just printing a statement before we print the months and days
        displayDaysAndMonths(days, months); // calling the displayDaysAndMonths method and using the days and months arrays as the arguments to print the number of days in each month
                                    } // close getDaysAndMonths method
    private void displayDaysAndMonths(int[] days, String[] months) { // this method is private since we're only calling it in this class and it takes the days and months arrays as parameters to print the number of days in each month
    for (int i = 0; i < months.length; i++) { // using a for loop to go through the months array and print out the month and the number of days in that month. We are using months.length as the condition since we want to go through all 12 months
        System.out.println("There are " + days[i] + " days in " + months[i]); // printing the number of days in each month using the same index for both arrays to get correct month and number of days.
                                            } // close for loop
                                        } // close displayDaysAndMonths method
                                } // close ArrayOperations class

