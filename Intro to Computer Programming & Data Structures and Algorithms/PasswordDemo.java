import java.util.*;

public class PasswordDemo{
   public static void main(String[] args){
      boolean correct = false;
      while (correct == false) {
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter password: ");
         String pass = sc.next();
         PasswordVerifier pv = new PasswordVerifier(pass);
         if (pv.verify()) {
            System.out.println("Valid password");
            correct = true;
         } else {
            System.out.println("Invalid password. Try again...");
         }
      }
   }
}