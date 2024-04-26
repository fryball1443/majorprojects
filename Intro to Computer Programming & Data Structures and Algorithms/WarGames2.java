import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class WarGames {
  public static void main(String[] args) throws IOException {
     //
     //stores path for files; in [user home folder]/java directory, should work on any operating system
      String mainPath      = "~/java";
      String highScorePath = "~/java/HighScore.txt";
      String logPath       = "~/java/GameLog.txt";
      String boundPath     = "~/java/Bound.txt";
      //final int length = String.valueOf(upperbound).length();
      //System.out.print(length);
      
      
      mainPath      = System.getProperty("user.home") + mainPath.substring(1);
      highScorePath = System.getProperty("user.home") + highScorePath.substring(1);
      logPath       = System.getProperty("user.home") + logPath.substring(1);
      boundPath     = System.getProperty("user.home") + boundPath.substring(1);
      
     //checks if directory and high score file exist. if not, the file will be created
      File dir = new File(mainPath);
      if (!dir.exists()) {
        dir.mkdir();
      }     
      //checks if high score file exists. if not, the file will be created
      //String highScorePath = "~/java/HighScore.txt";
      //highScorePath = System.getProperty("user.home") + highScorePath.substring(1);
      
      File readFile = new File(highScorePath);
      if (!readFile.exists()) {
        FileWriter fwriter = new FileWriter(highScorePath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(99999999);
        file.close();
      }
      
      File boundFile = new File(boundPath);
      if (!boundFile.exists()) {
        FileWriter fwriter = new FileWriter(boundPath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(100);
        file.close();
      }
      
      //System.out.print (new Scanner(boundFile)).nextInt();
      int upperbound = (new Scanner(boundFile)).nextInt();
      final int length = String.valueOf(upperbound).length();
      /*
      File f = new File(highScorePath);
      if (!f.exists()) {
        FileWriter fwriter = new FileWriter(highScorePath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(99999999);
        file.close();
      }
      
     //scans file for high score integer
      File readFile         = new File(highScorePath);*/
      
      Scanner highScoreFile = new Scanner(readFile);
      int highScore         = highScoreFile.nextInt();
      
    
     //we will be using the scanner class
      Scanner input = new Scanner(System.in);
      Scanner
     
     //creates a loop so that the game can be played over and over again
      String text;
      char play = 'y';
      System.out.printf ("\n\033[1;34m  SHALL WE PLAY A GAME?   \033[0;30m"); text = input.next(); text = text.toLowerCase(); play = text.charAt(0);
      //System.out.printf ("\n\033[1;34m  ENTER NUMBER OF PLAYERS   \033[0;30m"); int pl = input.nextInt();
      if (play == 'n') {
        System.exit(0);
      }
      
      //FileWriter fwriter = new FileWriter(logPath, true);
      //PrintWriter score  = new PrintWriter(fwriter);
      while (play == 'y') {
          System.out.printf ("\n\033[1;34m  ENTER NUMBER OF PLAYERS   \033[0;30m"); int pl = input.nextInt();

        
         //declare variables
          int tries      = 0;
          int success    = 0;
          
          
         //random number generator
          int num   = (new Random()).nextInt(upperbound);
          int guess = (new Random()).nextInt(upperbound);
          
         //file located in [user home folder]/java directory, should work on any operating system
          //logPath = "~/java/GameLog.txt";
          //logPath = System.getProperty("user.home") + logPath.substring(1);
          FileWriter fwriter = new FileWriter(logPath, true);
          PrintWriter score  = new PrintWriter(fwriter);
          score.printf ("\n\n\n\n\n\n\nNew Game");
         //ask the user to guess the random number
                    
          while (success == 0) {  
            if (highScore < 99999999) {
              if (pl == 1) {
                System.out.printf("\n      High Score: %d", highScore);
              }
            }
            //int num   = (new Random()).nextInt(upperbound);
            //int guess = (new Random()).nextInt(upperbound);
            
            if (pl == 2) {
              System.out.printf  ("\n  Input the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              num         = input.nextInt();
              for (int i = 999; i > 0; i--) {
                System.out.printf("\n");
              }   
              System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              guess       = input.nextInt();
            } else if (pl == 1) {
              //Random rand = new Random();
              //num         = rand.nextInt(upperbound);
              System.out.printf("\n%d", num);
              System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              guess       = input.nextInt();
            } else if (pl == 0) {
              Random rand = new Random();
              num         = rand.nextInt(upperbound);
              rand        = new Random();
              guess       = rand.nextInt(upperbound);
              final String word = ("%" + length + "d %" + length + "d     ");
              System.out.printf(word, num, guess);
              //System.out.printf("%d  %d     \n", length, length, num, guess );
            } else {
              
            }
            /*
            System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
            System.out.println (num);
            int guess = input.nextInt();
            */
            tries++;
            
            if  (num == guess) {
              System.out.printf("\n  Correct, it took you %d attempts", tries);
              success++;
            }
            
            score.printf ("\n    Attempt  %d \n    Guess: %d \n    Correct number: %d \n    High Score: %d\n", tries, guess, num, highScore);
          }  
         //replaces stored high score with new high score
          if (tries < highScore) {
            FileWriter fwriterhs = new FileWriter(highScorePath);
            PrintWriter file = new PrintWriter(fwriterhs);
            file.print(tries);
            file.close();
            highScore = tries;
            System.out.printf ("\n  Congratulations! You have beat your high score!");
          }
          score.close();
          highScoreFile.close();
              

                    //asks if user would like to play again (not case sensitive)
            System.out.printf ("\n  Would you like to play again?   "); text = input.next(); text = text.toLowerCase(); play = text.charAt(0);
      }
      //System.out.printf("\n  Thank you for playing! \n  Game log located at \n\n%s", logPath);
      
      //*/
      
      if (text.equals("reset")) {
        (new File(highScorePath)).delete();
        (new File(logPath)).delete();
        (new File(boundPath)).delete();
        for (int i = 99; i > 0; i--) {
          System.out.printf("\n");
        }
        System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nRESET COMPLETE...");
        text = input.next(); text.toLowerCase();
        if (text == "uninstall") {
          (new File(mainPath)).delete();
          final String os = System.getProperty("os.name");
          if (os.contains("Windows")) {
              Runtime.getRuntime().exec("cls");
          } else {
              Runtime.getRuntime().exec("clear");
          }
          System.exit(0);
        } else {
        }
      } else if (text.equals("bound")) {
        double boundNum    = 2147483648.0;
        while (boundNum    > 2147483647) {
          String boundLine = input.next();
          boundNum         = Double.parseDouble(boundLine);
        }
        System.out.print(boundNum);
        upperbound         = (int)boundNum;
        FileWriter fwriter = new FileWriter(boundPath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(upperbound);
        file.close();
        main(new String[]{});
      } else {
        System.out.printf("\n  Thank you for playing! \n  Game log located at \n\n%s\n", logPath);
      }
  }
  public static void warGames(int num, int guess) {
  }
}
