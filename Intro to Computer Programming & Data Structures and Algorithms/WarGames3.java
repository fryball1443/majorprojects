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
      int max = 500004;
      double sleep = 100;
      warGames();
      
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
      File readFile = new File(highScorePath);
      if (!readFile.exists()) {
        FileWriter fwriter = new FileWriter(highScorePath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(max);
        file.close();
      }
      
      File boundFile = new File(boundPath);
      if (!boundFile.exists()) {
        FileWriter fwriter = new FileWriter(boundPath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(99999);
        file.close();
      }
      
      
      int upperbound = (new Scanner(boundFile)).nextInt();
      final int length = String.valueOf(upperbound).length();
      Scanner highScoreFile = new Scanner(readFile);
      int highScore         = highScoreFile.nextInt();
      
    
     //we will be using the scanner class
      Scanner input = new Scanner(System.in);
     
     //creates a loop so that the game can be played over and over again
      String text;
      char play = 'y';
      timer(2000);
      System.out.printf ("\n\033[1;34m  SHALL WE PLAY A GAME?   "); text = input.next(); text = text.toLowerCase(); play = text.charAt(0);
      if (play == 'n') {
        System.exit(0);
      }
      
      while (play == 'y') {
          timer(1000);
          System.out.printf ("\n\033[1;34m  ENTER NUMBER OF PLAYERS   "); int pl = input.nextInt();
          timer(500);
          sleep = 100;
        
         //declare variables
          int tries      = 0;
          int success    = 0;
          
          
         //random number generator
          int num   = (new Random()).nextInt(upperbound);
          int guess = (new Random()).nextInt(upperbound);
          
         //file located in [user home folder]/java directory, should work on any operating system
          FileWriter fwriter = new FileWriter(logPath, true);
          PrintWriter score  = new PrintWriter(fwriter);
          score.printf ("\n\n\n\n\n\n\nNew Game");
         //ask the user to guess the random number
                    
          while (success == 0) {  
            if (highScore < max) {
              if (pl == 1) {
                System.out.printf("\n      High Score: %d", highScore);
              }
            }
            
            if (pl == 2) {
              System.out.printf  ("\n  Input the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              num         = input.nextInt();
              for (int i = 999; i > 0; i--) {
                System.out.printf("\n");
              }   
              System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              guess       = input.nextInt();
            } else if (pl == 1) {
              System.out.printf("\n%d", num);
              System.out.printf  ("\n  Guess the random number between 0 and %d\n  You have attempted %d times so far\n", upperbound, tries);
              guess       = input.nextInt();
            } else if (pl == 0) {
              Random rand = new Random();
              num         = rand.nextInt(upperbound);
              rand        = new Random();
              guess       = rand.nextInt(upperbound);
              
              sleep = (sleep - (tries / 100));
              timer(sleep);
              
              /*
              if (tries <= 50) {
                timer(100);
              } else if (tries <= 100) {
                timer(75);
              } else if (tries <= 150) {
                timer(50);
              } else if (tries <= 175) {
                timer(40);
              } else if (tries <= 200) {
                timer(30);
              } else if (tries <= 213) {
                timer(25);
              } else if (tries <= 225) {
                timer(20);
              } else if (tries <= 235) {
                timer(15);
              } else if (tries <= 240) {
                timer(13);
              } else if (tries <= 245) {
                timer(10);
              } else if (tries <= 250) {
                timer(7);
              } else if (tries <= 255) {
                timer(6);
              } else if (tries <= 258) {
                timer(5);
              } else if (tries <= 260) {
                timer(4);
              } else if (tries <= 262) {
                timer(3);
              } else if (tries <= 264) {
                timer(2);
              } else if (tries <= 265) {
                timer(1);
              } else if (tries <= 266) {
                timer(0.5);
              } else if (tries <= 267) {
                timer(0.4);
              } else if (tries <= 268) {
                timer(0.3);
              } else if (tries <= 269) {
                timer(0.2);
              } else if (tries <= 270) {
                timer(0.1);
              }
              /*
              } else if (tries <= 150) {
                timer(75);
              } else if (tries <= 150) {
                timer(75);
              } else if (tries <= 150) {
                timer(75);
              } else if (tries <= 150) {
                timer(75);

              /*
              if (upperbound < 100001) {
                 timer(1);
                if (upperbound < 1001) {
                   timer(10);
                  if (upperbound < 101) {
                     timer(100);
                  }
                }  
              }
              */
              System.out.printf("%7d%7d", num, guess);
            } else {
              
            }
            tries++;
            
            if  (num == guess) {
              System.out.printf("\n  Correct, it took you %d attempts", tries);
              success++;
            }
            
            if (tries >= max) {
              timer(1000);
              warGames();
              timer(3000);
              System.out.printf("\n\033[1;34m  A STRANGE GAME.\n"); timer(2000);
              System.out.printf("  THE ONLY WINNING MOVE IS\n"); timer(1000);
              System.out.printf("  NOT TO PLAY.\n"); timer(4000);
              System.out.printf("\n  HOW ABOUT A NICE GAME OF CHESS?   ");
              success = 1;
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
            if (tries < max) {
              System.out.printf ("\n  Would you like to play again?   "); 
            }

            text = input.next(); text = text.toLowerCase(); play = text.charAt(0);
      }
      
      //*/
      
      if (text.equals("reset")) {
        (new File(highScorePath)).delete();
        (new File(logPath)).delete();
        (new File(boundPath)).delete();
        warGames();
        timer(1000);
        System.out.printf("RESET COMPLETE...");
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
        upperbound         = (int)boundNum;
        FileWriter fwriter = new FileWriter(boundPath);
        PrintWriter file   = new PrintWriter(fwriter);
        file.print(upperbound);
        file.close();
        main(new String[]{});
      } else {
        warGames();
        System.out.printf("\n  Thank you for playing! \n  Game log located at \n\n%s\n", logPath);
      }
  }
  public static void warGames() {
    for (int i = 99; i > 0; i--) {
      System.out.printf("\n");
      timer(10);
    }
  }
  public static void timer(double delay) {
    //int delay = 2000; // number of milliseconds to sleep
    long start = System.currentTimeMillis();
    while(start >= System.currentTimeMillis() - delay); // do nothing
    //System.out.println("Time Slept: " + Long.toString(System.currentTimeMillis() - start));
  }
}
