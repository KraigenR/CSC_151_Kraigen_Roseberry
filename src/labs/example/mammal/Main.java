package labs.example.mammal;

public class Main {
    public static void main(String[] args) throws Exception {
        Mammal myMammal = new Mammal();
        // Moved the setter values to the Main file as the setters were fixed within the Mammal.java file. 
        myMammal.setName("Bob");
        myMammal.sethairColor("Brown");
        myMammal.seteyeColor("Green");
        myMammal.setbodyTemp("90");
        myMammal.setweight("70");
        myMammal.setnumberoflegs("4");
    try {
        myMammal.sit()}
    catch (Exception e) {
        e.printStackTrace();}
    //Exception is thrown from the Mammal.java file and caught in Main.java. When exception is thrown we are naming it "e".
    //printStackTrace gives us a list of eror information which is more helpful than the println.
    myMammal.getMammalDetails();    
    }
}
