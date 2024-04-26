import java.util.Scanner;
import java.util.Random;
import java.io.*;


public class CrapsShooter {
  public static void main(String[] args) throws IOException {
      String mainPath = "~/java";
      mainPath = userHome(mainPath);
      String writePath  = mainPath + "/stats.txt";
      FileWriter fwriter = new FileWriter(writePath, true);
      PrintWriter file  = new PrintWriter(fwriter);
      
     //declare upper bound for random generators
      int upperbound = 6;
      upperbound += 1;
      int min = 1;
      int point;
     
      System.out.printf ("\n  Respond to any question with (y/n)   \n\n\n");
     
     //we will be using the scanner class
      Scanner input = new Scanner(System.in);
     
     //creates a loop so that the game can be played over and over again
      String text;
      char play = 'y';
      System.out.printf ("\n  SHALL WE PLAY A GAME?   "); 
      text = input.next(); 
      play = lowerCase(text);
      while (play == 'y') {
        //replays 100 times
        for (int i = 1; i < 100; i++) {
           //declare variables
            int tries      = 0;
            int success    = 0;
            
            
           //random number generator
            int d1 = randomGen(upperbound, min);
            int d2 = randomGen(upperbound, min);
            System.out.printf("\n\n%7d%7d\n", d1, d2);
            int dice = d1 + d2;
            point = dice;
            System.out.printf("  Point: %d\n\n", point);
            String ans = "";
                      
            while (success == 0) { 
              
              //timer(500);
              d1 = randomGen(upperbound, min);
              d2 = randomGen(upperbound, min);
              dice = d1 + d2;
              System.out.printf("%7d%7d      Total: %d\n", d1, d2, dice);
              //System.out.printf("  %d    %d\n", d1, d2);
              
              tries++;
              
              if (dice == 7) {
                System.out.printf("  Seven\n");
                ans = "Seven";
                success++;
              } else if  (dice == point) {
                System.out.printf("  Point!\n");
                ans = "Point";
                success++;
              }
            }  
            file.printf ("\n\n\n    Come  %d \n    Point: %d \n    Rolls till end of sequence: %d \n    Ended with point or seven: %s\n", i, point, tries, ans);
        }
        
          System.out.printf ("\n  Would you like to play again?   "); 
          text = input.next(); 
          play = lowerCase(text);
            
      }
      file.close();
      
  }
  
 //random number generator
  public static int randomGen(int bound, int min) {
    //System.out.print(bound);
    //System.out.print(min);
    bound = bound - min;
    Random rand = new Random();
    int number  = rand.nextInt(bound) + min;
    //System.out.print(bound);
    //System.out.print(number);
    return number;
    
  }
  
 //for y/n, makes it lower case
  public static char lowerCase(String text) { 
    text = text.toLowerCase(); 
    char lowerCaseLetter = text.charAt(0);
    return lowerCaseLetter;
  }
  
 //finds the user home folder and replaces ~ in path to the path of the user home folder
  public static String userHome(String path) {
    path = System.getProperty("user.home") + path.substring(1);
    File dir = new File(path);
    if (!dir.exists()) {
      dir.mkdir();
    }
    return path;
  }
  
 //creates a timer where it pauses for a few seconds
  public static void timer(double delay) {
    //int delay = 2000; // number of milliseconds to sleep
    long start = System.currentTimeMillis();
    while(start >= System.currentTimeMillis() - delay); // do nothing
    //System.out.println("Time Slept: " + Long.toString(System.currentTimeMillis() - start));
  }
}
