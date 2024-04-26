import java.util.Scanner;

//this program asks what size, crust, and toppings the user wants

public class IfThenAge {
  public static void main(String[] args) {
    //constants
    
    //scanner class will be used
     Scanner input = new Scanner(System.in);
     
    //variables
     int age;
     char sex;
     String text;
     String ageWord = "Child";
    
    //ask questions
     System.out.println("How old are you?");               age  = input.nextInt();
                                                                  input.nextLine();
     System.out.println("What is your Sex?(male/female)"); text = input.nextLine(); 
                                                           sex  = text.charAt(0);
     
    if (age <= 12) {
      ageWord = "Child";
    }
    
    if (age > 12) {
      if (age <= 17) {
        ageWord = "Teenager";
      }
    }
    
    if (age > 17) {
      if (age <= 54) {
        ageWord = "Adult";
      }
    }
    
    if (age > 54) {
      ageWord = "Senior";
    }
    
    System.out.println(ageWord);
    
    //
    
    if (age < 18) {
      ageWord = "Minor";
    } else {
      ageWord = "Adult";
    }
    
    if ((ageWord == "Minor") && (sex == 'm')) {
      System.out.print("Sour");
    } else if ((ageWord == "Minor") && (sex == 'f')) {
      System.out.print("Sweet");
    } else if ((ageWord == "Adult") && (sex == 'm')) {
      System.out.print("From Mars");
    } else if ((ageWord == "Adult") && (sex == 'f')) {
      System.out.print("From Venus");
    } else {
      System.out.print("you dont exist");
    }
    
    //
  }
}