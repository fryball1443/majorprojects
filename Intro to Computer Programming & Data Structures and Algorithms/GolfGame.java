import java.util.Random;
import java.util.Scanner;

public class GolfGame {
  public static void main(String[] args) {
    //declare variables
     int[] hole   = new int[9];
     int club     = 1; 
     
     

     Scanner input = new Scanner(System.in);
     
    //loops everything until all holes are done
     for (int i = 0; i < 9; i++) {
       int distance = 300;
       //System.out.printf("index:%d\n", i);
       int shots = 0;
       int penalty = 0;
       
      //loop for each hole, until distance to hole equals 0
       while (distance != 0) {
         int tooFar = 0;
         int oldDist = distance;
         System.out.printf("\nHole:    Shots made:    Penalties:\n");
         System.out.printf("%4d     %10d     %9d  \n  Distance: %3d yards\n  Select Club(1,2,3...)\n\n", (i + 1), shots, penalty, oldDist); 
         System.out.print("      Club      Yards\n" +
                          "     ------   ---------\n" +
                          " [1] Putter      1-10\n" +
                          " [2] Wedge      10-50\n" +
                          " [3] 7 iron     20-100\n" +
                          " [4] 5 iron     30-150\n" +
                          " [5] 3 wood     40-250\n" +
                          " [6] Driver    100-300\n");
         club = input.nextInt();
         
         int shotLength = 0;
         /*distance = 5;
         if (club == 1) {
           if (distance <= 5) {
             shotLength = distance;
           } else {
              //distance = 5;
              shotLength = randomGen(10) + 1;
              //System.out.println(shotLength);
              //distance = distance - shotLength;
              //distance = (Math.abs(distance));
              //System.out.println(distance + "\n");
           }
         } else if (club == 2) {
           shotLength = randomGen(10) + 1;
         }*/
         
         if (club == 1) {
           if (distance <= 5) {
             shotLength = distance;
           } else {
              shotLength = randomGen(1,10);
           }
         } else if (club == 2) {
           shotLength = randomGen(10,50);
         } else if (club == 3) {
           shotLength = randomGen(20,100);
         } else if (club == 4) {
           shotLength = randomGen(30,150);
         } else if (club == 5) {
           shotLength = randomGen(40,250);
         } else if (club == 6) {
           shotLength = randomGen(100,300);
         } else {
           shotLength = 0;
           shots--;
         }
         
         
         distance = distance - shotLength;
         if (distance < 0) {
           tooFar = 1;
         }/*( else {
           System.out.printf("\nShot %d\n  Shot distance: %d\n  Distance from hole %d: %d\n", shots, shotLength, i, distance);
         }*/
         distance = (Math.abs(distance));
         
         shots++;
         //System.out.printf("\nShot %d\n  Shot distance: %d\n  Distance from hole %d: %d\n", shots, shotLength, (i + 1), distance);
         System.out.printf("\nShot %d\n  Shot distance: %d yards\n  Previous distance from hole: %d yards\n  New distance from hole: %d yards\n", shots, shotLength, oldDist, distance);
         if (tooFar == 1) {
           System.out.printf("  You shot too far. Penalty added of 1 shot\n");
           penalty++;
         }
         timer(1000);
         
         
         
       }//end of while loop
       //shots = penalty + shots;
       hole[i] = shots + penalty;
       System.out.printf("\nResults for Hole %d:\n  Shots made:%5d\n  Penalties: %5d\n  Score:     %5d\n\n", (i + 1), shots, penalty, hole[i]);
       //System.out.printf("%4d     %10d     %9d  \n  Distance: %3d yards\n  Select Club(1,2,3...)\n\n", (i + 1), shots, penalty, oldDist); 

       //System.out.printf("  Shots for hole %d: %d\n", (i + 1), shots);
       //hole[i] = shots;
     }//end of for loop
     
     System.out.println ("Results.....\n" +
                         "    Hole    Score\n" +
                         "   ------  -------");
     for (int i = 0; i < 9; i++) {
       System.out.printf("     %d     %4d \n", (i + 1), hole[i]);
     }
     //System.out.print(hole);
     
  }
  
 //random number generator, 
 //lower bound is the minimum number that can be generated.
 //upperbound is the maximum number generated
  public static int randomGen(int lowerbound, int upperbound) {
    upperbound += 1;
    upperbound = upperbound - lowerbound;
    Random rand = new Random();
    int number  = rand.nextInt(upperbound);
    number = number + lowerbound;
    return number;
  }
  
 //timer
  public static void timer(double delay) {
    //int delay = 2000; // number of milliseconds to sleep
    long start = System.currentTimeMillis();
    while(start >= System.currentTimeMillis() - delay); // do nothing
    //System.out.println("Time Slept: " + Long.toString(System.currentTimeMillis() - start));
  }

  /*
 //random number generator
  public static int randomGen(int bound) {
    Random rand = new Random();
    int number  = rand.nextInt(bound);
    return number;
  }*/
}