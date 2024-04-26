import java.util.Scanner;

public class Parrot {
   public static void main(String[] args) {
     //Scanner class will be used
      Scanner input = new Scanner(System.in);
     
     //declare variables
      int    age;
      String firstName;
      String lastName;
      String height;
      String color;
     
     //get users age
      System.out.println("Enter your age");
      age = input.nextInt();
     
     //get users name
      System.out.println("Enter your first and last name: ");
      input.nextLine();
      
      //for first name only, gets until next space
       firstName = input.next();
      
      //for last name only, gets until next space
       lastName = input.next();
      
     //get users height
      System.out.println("Enter your height in feet:");
      input.nextLine();
      height = input.nextLine();
      
     //get users favorite color
      System.out.println("What is your favorite color?");
      color = input.nextLine();      
    
     //display the inputted variables
      System.out.println("Enter your height in feet:");
      System.out.println(" ");
      System.out.println("First name:     " + firstName);
      System.out.println("Last name:      " + lastName);
      System.out.println("Age:            " + age + " years old");
      System.out.println("Height:         " + height + "ft");
      System.out.print  ("Favorite Color: " + color);
   input.close();
    
   }
}