

import java.util.Scanner;

//this program asks what size, crust, and toppings the user wants

public class Feb16ClassIF {
  public static void main(String[] args) {
    //constants
    
    //scanner class will be used
     Scanner input = new Scanner(System.in);
     
    //variables
     int points;
     char type;
     String text;
     String grade = "Child";
    
    //ask questions
     System.out.println("What grade did you get");               points  = input.nextInt();
                                                                  input.nextLine();
     System.out.println("What is your Sex?(male/female)"); text = input.nextLine(); 
                                                           type  = text.charAt(0);
     
    if (points <= 59) {
      grade = "F";
    } else if (points <= 69) {
      grade = "D";
    } else if (points <= 79) {
      grade = "C";
    } else if (points <= 83) {
      grade = "B-";
    } else if (points <= 87) {
      grade = "B";
    } else if (points <= 89) {
      grade = "B+";
    } else if (points <= 93) {
      grade = "A-";
    } else if (points <= 97) {
      grade = "A";
    } else if (points <= 100) {
      grade = "A+"; 
    } else {
      grade = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
      int num = 999999;
         while(num > 0)
            {
               System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               num--;
            }
    }
    
     
    System.out.println(grade);
    
    */
    
    System.out.println("What type of food do you like?" + "\n" + "mexican" + "\n" + "italian"); text = input.nextLine(); 
 
    if (type == 'm') {
      System.out.print("Sour");
    } else if ((grade == "Minor") && (type == 'f')) {
      System.out.print("Sweet");
    } else if ((grade == "Adult") && (type == 'm')) {
      System.out.print("From Mars");
    } else if ((grade == "Adult") && (type == 'f')) {
      System.out.print("From Venus");
    } else {
      System.out.print("you dont exist");
    }
    
    */
  }
}