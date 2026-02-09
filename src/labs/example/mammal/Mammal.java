package labs.example.mammal;

public class Mammal {
    protected String name;
    protected String hairColor;
    protected String eyeColor;
    protected String bodyTemp;
    protected String weight;
    protected String numberoflegs;

    public void setName(String name){
            this.name = "Bob";
            return;}
    public void sethairColor(String hairColor){
            this.hairColor = "Brown";
            return;}
    public void seteyeColor(String eyeColor){
            this.eyeColor = "Green";
            return;}
    public void setbodyTemp(String bodyTemp){
            this.bodyTemp = "90";
            return;}
    public void setweight(String weight){
            this.weight = "100";
            return;}
    public void setnumberoflegs(String numberoflegs){
            this.numberoflegs = "4";
            return;}
//methods
    public void eat(){
        System.out.println("The " + this.name + "is now eating...");
        return;}
    public void sleep() throws Exception{
        int sleepTime = 500;
        System.out.println("The " + this.name + "is sleeping and will wake up in " + sleepTime/1000);
        System.out.println("The " + this.name + " is now eating...");
        return;}
    public void run(){
        System.out.println("The " + this.name + "is now running...");
        return;}
    public void scratch(){
        System.out.println("The " + this.name + "is now scratching with it's " + this.numberoflegs + "legs...");
        return;}
    public void drink(){
        System.out.println("The " + this.name + "is now drinking water...");
        return;}
    public void jump(){
        System.out.println("The " + this.name + "is now jumping on it's " + this.numberoflegs + "legs.");
        return;}
    public void look(){
        System.out.println("The " + this.name + "is now looking with it's" + this.eyeColor + "eyes.");
        return;}
    public void weighs(){
        System.out.println("The " + this.name + "weighs " + this.weight + "pounds.");
        return;}
    public void begs(){
        System.out.println("The " + this.name + "looks up at you with it's " + this.eyeColor + "eyes begging for food.");
        return;}
    // Week 3 Lab additions
        public void sit() throws Exception{
    int sitTime = 15000;
    System.out.println ("The " + this.name + " is now sitting for " + sitTime/1000 + " seconds.");
    Thread.sleep(sitTime);
    System.out.println (this.name + " is no longer sitting.");
    stand();}
    public void stand(){ 
    System.out.println (this.name + " stands up and starts barking.");
    return;}
    public void getMammalDetails(){
        System.out.println("The Mammal is named " + this.name + " the mammal's hair color is " + this.hairColor + " the mamal's eye color is " + this.eyeColor + ". The body tempature is " + this.bodyTemp + " and the mammal weighs " + this.weight + " with " +this.numberoflegs + " number of legs.");
        return;}

        
}