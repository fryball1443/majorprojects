import java.util.Scanner;

public class CreditCard {
  public static void main(String[] args) {
    int creditLimit = 1000;
    int balance = 0;
    int remainingBalance = 0;
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the current credit limit:");
    creditLimit = input.nextInt();
    System.out.println("Enter the current amount used:");
    balance = input.nextInt();
    remainingBalance = creditLimit - balance;
    if (balance > creditLimit) {
      System.out.println("Credit limit exceeded");
    } else {
      System.out.println("Your remaining credit balance is: " + creditLimit);
    }
    input.close();
  }
}