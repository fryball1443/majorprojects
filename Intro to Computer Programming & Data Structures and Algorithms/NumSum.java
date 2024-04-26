import java.util.*;

public class NumSum {
   public static void main(String[] args) {
      double sum = 0; 
      String number; 
      Scanner sc = new Scanner(System.in); 
      System.out.println("Enter numbers separated by commas.... ");
      number = sc.nextLine(); 
      StringTokenizer token = new StringTokenizer(number, ",");
     //break string into different numbers(tokens)
      while (token.hasMoreTokens()) {
         sum = Double.parseDouble(token.nextToken())+sum;
        //parse those tokens from string to double to calculate sum
      }
      System.out.print("Sum: "+ sum);
   }
}

