package labs.example.mammal;

public class Main {
    public static void main(String[] args) throws Exception {
        Mammal myMammal = new Mammal();
        // Moved the setter values to the Main file as the setters were fixed within the Mammal.java file.
        // generic Mammal settings.  
        myMammal.setName("Bob");
        myMammal.sethairColor("Brown");
        myMammal.seteyeColor("Green");
        myMammal.setbodyTemp("90");
        myMammal.setweight("70");
        myMammal.setnumberoflegs("4");
    // ------- Commenting out the sit call to test the walk.
    //try {
    //    myMammal.sit();}
    //catch (Exception e) {
    //    e.printStackTrace();}
    //Exception is thrown from the Mammal.java file and caught in Main.java. When exception is thrown we are naming it "e".
    //printStackTrace gives us a list of eror information which is more helpful than the println.
    // ---- Commenting out the get details for the walk test. 
    //myMammal.getMammalDetails();    
    //trying out the go for a walk from Mammal.java
    // commenting out the go for a walk method.\
    //---- myMammal.goforawalk();
    //---- }


    // creating specifically a dog mammal. 
    Dog aDog = new Dog();
    aDog.setName ("Largo");
    aDog.sethairColor ("Brown and White");
    aDog.seteyeColor ("Brown");
    aDog.setbodyTemp ("100");
    aDog.setweight ("60");
    aDog.setnumberoflegs ("4");
}
}