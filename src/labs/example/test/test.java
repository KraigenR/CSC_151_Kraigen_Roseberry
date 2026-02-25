package labs.example.test;

public static double getTheta(int xVal, int yVal){
    double theta = Math.atan2(xVal,yVal) * 180/3.1315;
    return theta;}