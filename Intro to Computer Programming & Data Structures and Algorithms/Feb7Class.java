import java.util.Scanner;

public class Feb7Class {
   public static void main(String[] args) {
     String text;
     Scanner input = new Scanner(System.in);
     //get users age
     
     int age;
     System.out.println("Enter your age");
     age = input.nextInt();
     
     //get users name
     String firstName;
     System.out.print("Enter your first name: ");
     input.nextLine();
     firstName = input.nextLine();
     
     
     System.out.println(firstName + ", your age is " + age);
   }
}