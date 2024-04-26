import java.util.HexFormat;
import java.util.Scanner;

/*/
Based off of 1983 movie WarGames, the user is promoted to play a game. 
The goal of the game is to correctly guess the 6 digit
(1) "Press 1 to start"
Use 7 segment display to display "greetings dr Falken. 
    Shall we play a game" — uses timer to slowly display that; 
press 1 for yes, 0 for no
(2) Print 
"global thermonuclear war: you have [x*6] seconds to disarm the bomb by 
    correctly guessing most of the numbers using binary"

Then have it randomly choose the 6 numbers — ex. 629547
And you have x seconds to use the switches to type in binary the number 1-9 and 1 button to submit

If correct, the 7segment display shows that number. If incorrect it shows -

After x seconds, it expires and moves on to the next one to the left and repeat for every digit
Example:
6-95-7 — success.
-2-5-7 — failure

If 4/6 digits are correct before all expire, print "well done. You have stopped the bomb. Would you like to try again?"
If less than that are correct, print "bomb detonated. You have failed. Would you like to try again?"

Press 1 for yes or 0 for no
If 1, goto (2)
If 0, goto (1)
//*/


public class FinalProjectJava {
  public static void main(String[] args) {
    int button = 0;
    start();
    Scanner inputScanner = new Scanner(System.in); 
    /*
    if (button == 1) {
      play();
    } else {
      start(button);
    }*/

    
  }
  /*
  public static int start(button){
    System.out.print("Press 1 to Start");
    
    button = inputScanner.nextInt();
  }*/
  public static void play(){
    System.out.print("Shall we play a game?");
  }
}