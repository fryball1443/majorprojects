import java.io.*;
import java.util.Scanner;  
public class GeometryMethods {

  //main method
   public static void main(String[] args) throws IOException{
   int input;
   double ans;
      System.out.printf("\n\n  What equation would you like to solve?  (enter number)\n");
      System.out.printf   ("   (1) area of circle \n   (2) area of rectangle \n   (3) area of triangle\n   (4) circumference of a circle\n   (0) exit\n");
      Scanner keyboard = new Scanner(System.in);
      input = keyboard.nextInt();
  
  //determines which equation method to call
      if (input == 1) {
        System.out.printf("\n  Please enter the radius  ");  int r = keyboard.nextInt();
        ans = AreaOfCircle(r);
        System.out.printf("\n  The area of your circle is %.7f", ans);
      }
       
      if (input == 2) {
        System.out.printf("\n  Please enter the length  ");  int l = keyboard.nextInt();
        System.out.printf("\n  Please enter the width  ");   int w = keyboard.nextInt();
        ans = AreaOfRect(l, w);
        System.out.printf("\n  The area of your rectangle is %.7f", ans);
      }
     
      if (input == 3) {
        System.out.printf("\n  Please enter the height  ");  int h = keyboard.nextInt();
        System.out.printf("\n  Please enter the base  ");    int b = keyboard.nextInt();
        ans = AreaOfTriangle(h, b);
        System.out.printf("\n  The area of the triangle is %.7f", ans);
      }
    
      if (input == 4) {
        System.out.printf("\n  Please enter the radius  ");  int r = keyboard.nextInt();
        ans = Circumference(r);
        System.out.printf("\n  The circumference is %.7f", ans);
      }
      
      if (input == 0) {
        System.exit(0);
      }
     
      timer(3000);
      
     //restarts program
      main(new String[]{});
   }
  
  
  //equation 1
   public static double AreaOfCircle   (int radius) throws IOException{
     double area;
     area = 3.1415 * (radius ^ 2);
     return area;
   }
 
  //equation2
   public static double AreaOfRect     (int length, int width) throws IOException {
     double area;
     area =  length * width;
     return area;
   }
  
  //area of triangle
   public static double AreaOfTriangle (int height, int base) throws IOException{
     double area;
     area =  (base * height) /2 ;
     return area; 
   }
     
  //circumference of circle
   public static double Circumference  (int radius) throws IOException{
     double circ;
     circ = 2 * radius * 3.1415;
     return circ; 
   }


 //waits an amount of time to restart program        
  public static void timer(double delay) {
    //int delay = 2000; // number of milliseconds to sleep
    long start = System.currentTimeMillis();
    while(start >= System.currentTimeMillis() - delay); // do nothing
    //System.out.println("Time Slept: " + Long.toString(System.currentTimeMillis() - start));
  }
}