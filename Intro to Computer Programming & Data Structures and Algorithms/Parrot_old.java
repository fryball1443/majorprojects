import java.util.Scanner;

public class Parrot_old {
   public static void main(String[] args) {
     
     Scanner input = new Scanner(System.in);
     
     
     String text;
     //get users age
     
      int age;
      System.out.println("Enter your age");
      age = input.nextInt();
     
     //get users name
      String firstName;
      //String lastName;
      //int age;
      
      
      System.out.print("Enter your full name: ");
       //for first name:
      firstName = input.next();
       //for last name
      //lastName = input.next();
      //System.out.println("Enter your age");
      //age = input.nextInt();
      
     System.out.println("First Name: " + firstName + 
                        //"/n Last Name: " + lastName + 
                        ", your age is " + age);
     
     /*
       //how to get a character
       char choice;
       String text;
       System.out.println("Continue? y/n");
       text = input.nextLine();
       choice = text.charAt(0);
       System.out.println("you decided to continue: " + choice);
     */
     
   }
}