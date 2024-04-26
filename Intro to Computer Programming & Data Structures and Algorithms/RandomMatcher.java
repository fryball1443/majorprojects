import java.util.Scanner;
import java.util.Random;

public class RandomMatcher {
  public static void main(String[] args) {
     //declare upper bound for random generators
      int upperbound = 10000;
      upperbound += 1;
     
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

         //declare variables
          int tries      = 0;
          int success    = 0;
          
          
         //random number generator
          int num   = randomGen(upperbound);
          int guess;
          
                    
          while (success == 0) { 
            guess = randomGen(upperbound);
            //System.out.printf("%7d%7d \n", num, guess);
            
            tries++;
            
            if  (num == guess) {
              System.out.printf("\n  Correct, it took you %d attempts", tries);
              success++;
            }
          }  
        
          System.out.printf ("\n  Would you like to play again?   "); 
          text = input.next(); 
          play = lowerCase(text);
            
      }
      
  }
  //random number generator
  public static int randomGen(int bound) {
    Random rand = new Random();
    int number  = rand.nextInt(bound);
    return number;
  }
  //for y/n, makes it lowrr case
  public static char lowerCase(String text) { 
    text = text.toLowerCase(); 
    char lowerCaseLetter = text.charAt(0);
    return lowerCaseLetter;
  }
}
