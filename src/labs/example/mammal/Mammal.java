package labs.example.mammal;

public class Mammal {
    protected String name;
    protected String hairColor;
    protected String eyeColor;
    protected String bodyTemp;
    protected String weight;
    protected String numberoflegs;

    public void setName(String name){
            this.name = name;
            return;}
    public void sethairColor(String hairColor){
            this.hairColor = hairColor;
            return;}
    public void seteyeColor(String eyeColor){
            this.eyeColor = eyeColor;
            return;}
    public void setbodyTemp(String bodyTemp){
            this.bodyTemp = bodyTemp;
            return;}
    public void setweight(String weight){
            this.weight = weight;
            return;}
    public void setnumberoflegs(String numberoflegs){
            this.numberoflegs = numberoflegs;
            return;}
//methods
    public void eat(){
        System.out.println("The " + this.name + "is now eating...");
        return;}
    public void sleep() throws Exception{
        int sleepTime = 500;
        System.out.println("The " + this.name + " is sleeping and will wake up in " + sleepTime/1000);
        return;}
    public void run(){
        System.out.println("The " + this.name + " is now running...");
        return;}
    public void scratch(){
        System.out.println("The " + this.name + " is now scratching with it's " + this.numberoflegs + "legs...");
        return;}
    public void drink(){
        System.out.println("The " + this.name + " is now drinking water...");
        return;}
    public void jump(){
        System.out.println("The " + this.name + " is now jumping on it's " + this.numberoflegs + "legs.");
        return;}
    public void look(){
        System.out.println("The " + this.name + " is now looking with it's" + this.eyeColor + "eyes.");
        return;}
    public void weighs(){
        System.out.println("The " + this.name + " weighs " + this.weight + " pounds.");
        return;}
    public void begs(){
        System.out.println("The " + this.name + " looks up at you with it's " + this.eyeColor + " eyes begging for food.");
        return;}
    // adding bark method which can only be used by dog.
    //public void bark(){
        //System.out.println(this.name + " Barks. woof...woof");
        //return;}
    // Week 3 Lab additions
    public void sit() throws Exception{
        int sitTime = 1; //changed time from 15000 to 1 just to shorten testing time.
        System.out.println ("The " + this.name + " is now sitting for " + sitTime/1000 + " seconds.");
        Thread.sleep(sitTime);
        System.out.println (this.name + " is no longer sitting.");
    stand();}
    public void stand(){ 
        System.out.println (this.name + " stands up.");
        return;}
    public void getMammalDetails(){
        System.out.println("The Mammal is named " + this.name + " the mammal's hair color is " + this.hairColor + " the mammal's eye color is " + this.eyeColor + ". The body tempature is " + this.bodyTemp + " and the mammal weighs " + this.weight + " with " +this.numberoflegs + " number of legs.");
        return;}
    // Week 4 Lab
    public int walk(int miles){
        System.out.println(this.name + " walks " + miles + " miles.");
        return miles;}
    public double goforawalk(){ //needed to change the void to double to be able to return the distance of the walk.
        int south = walk(3); //south
        int east = walk(4);  //east
        double distance = Math.sqrt(Math.pow(south, 3) + Math.pow(east, 4));
        System.out.println("The distance from the start to " + this.name + "'s location is " + distance + " miles.");
        double thetaRadians = Math.atan2(south, east);
        double thetaDegrees = Math.toDegrees(thetaRadians);
        System.out.println("The angle of the walk is: " + thetaDegrees + " degrees.");
        return thetaDegrees;} //was missing the return statment which is why I changed it from void to double.
}