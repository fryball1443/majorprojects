import java.util.Scanner;
import java.io.*;
import java.util.Random;

//before running this code, you will need to make a folder named "java" in your user home directory( /Users/USER/java or C:\Users\USER\java )
public class NumberGameTier1 {
  public static void main(String[] args) throws IOException {

   //we will be using the scanner class
    Scanner input = new Scanner(System.in);
   
   //creates a loop so that the game can be played over and over again
    String text;
    char play = 'y';
    String path = "";
    while (play == 'y') {
        
       //declare variables
        int tries      = 0;
        int success    = 0;
        int upperbound = 100;
       //random number generator
        Random rand    = new Random();
        int num        = rand.nextInt(upperbound);
        
       //creates log file located in [user home folder]/java directory, should work on any operating system
        path = "~/java/gamescore.txt";
        path = System.getProperty("user.home") + path.substring(1);
        FileWriter fwriter = new FileWriter(path, true);
        PrintWriter file   = new PrintWriter(fwriter);
        file.printf ("\n\n\n    New Game", num, tries, tries);
       
       //ask the user to guess the random number
        while (success == 0) {  
          System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
          int guess = input.nextInt();
          tries++;
          
          if  (num == guess) {
            System.out.printf("  Correct, it took you %d attempts", tries);
            success++;
          }
          
            file.printf ("\n Attempt  %d \n Guess: %d \n Correct number: %d", tries, guess, num);
          
          
        }
        file.close();
        
      //asks if user would like to play again (not case sensitive)
       System.out.printf ("\n  Would you like to play again?   "); text = input.next(); text = text.toLowerCase(); play = text.charAt(0);
     }
   System.out.printf("\n  Thank you for playing! \n  Game log located at \n\n%s", path);
  }
}