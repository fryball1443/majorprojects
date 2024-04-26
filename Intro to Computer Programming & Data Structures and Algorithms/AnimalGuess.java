import java.util.Scanner;

public class AnimalGuess {
  public static void main(String[] args) {
   //we will be using the scanner class
    Scanner keyboard = new Scanner(System.in);

    //declare variables
    String name = "DOG";
    int tries = 0;
    int success = 0;

    // Close the Scanner object
    keyboard.close();
   
   //ask the user to guess favorite animal. it will automatically change everything to uppercase, so guess is not case sensitive
    while (success == 0) {  
      System.out.printf  ("  Guess my favorite animal (not case sensitive, say the exact name)\n  You have attempted %d times so far\n", tries);
      String guess = keyboard.next();
      guess = guess.toUpperCase();
      tries++;
      
      if (name.equals(guess)) {
        System.out.printf("  Correct, it took you %d attempts", tries);
        success++;
      } 
      
    }
  }
}