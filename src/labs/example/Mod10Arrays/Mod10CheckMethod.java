package labs.example.Mod10Arrays;

public class Mod10CheckMethod {

    public static void main(String[] args) {
        String creditCardNumber = "4539578763621486"; // sample Visa card
        if (isValidCreditCard(creditCardNumber)) {
            System.out.println("Credit Card " + creditCardNumber + " is valid.");
        } else {
            System.out.println("Credit Card " + creditCardNumber + " is not valid.");
        }
    }

    // Method to check if a credit card number is valid using Mod-10
    public static boolean isValidCreditCard(String cardNumber) { // This method takes a credit card number as a string and processes it according to the Mod-10 algorithm to determine if it's valid
    int sum = 0; // variable to hold the sum of the digits after processing
    for (int a = cardNumber.length() - 1; a >= 0; a--) { // we are starting at the last index of the credit card number and moving backwards to the first index
        int digit = Character.getNumericValue(cardNumber.charAt(a)); // converting the character at index a to a numeric value so we can do math with it

        if ((cardNumber.length() - 1 - a) % 2 == 1) { // we are doubling every second digit from the right, so we check if the index from the right is odd. We use cardNumber.length() - 1 - a to get the index from the right
            int doubled = digit * 2; // we are doubling the digit
            if (doubled > 9) { // if the doubled value is more than 9 we need to add the two digits together
                digit = (doubled / 10) + (doubled % 10); // we are taking the doubled # diving by 10 to get our first digit then using mod 10 to get the second digit and adding them together to get the final value of digit
            } else {
                digit = doubled; // if the doubled value is not more than 9 then we can just set digit equal to the doubled value
            }
        }

        sum += digit; // we are adding the value of digit to our sum variable which will hold the total sum of all the processed digits
    }
    return sum % 10 == 0; // if the sum of our processed digits mod 10s to equal 0 then the credit card number is valid so we return true, otherwise we return false
} // closing isValidCreditCard method

} //closing Mod10CheckMethod class