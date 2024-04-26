import java.util.Scanner;
public class CreditCardBalance {
    public static void main(String[] args) {
        double balance = 5000;
        double maxBalance = 0;
        double remainingBalance = 0;
        double interestRate = 0.15;
        double monthlyPayment = 500;
        int months = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter balance: ");
        balance = input.nextDouble();
        System.out.print("Enter interest rate: ");
        interestRate = input.nextDouble();
        System.out.print("Enter monthly payment: ");
        monthlyPayment = input.nextDouble();

        while (balance > 0) {
            balance = balance * (1 + interestRate / 12) - monthlyPayment;
            months++;
            if (balance > maxBalance) {
                maxBalance = balance;
            }
            if (balance < 0) {
                remainingBalance = balance;
            }
        }

        System.out.println("Months to pay off: " + months);
        System.out.println("Max balance: " + maxBalance);
        System.out.println("Remaining balance: " + remainingBalance);
        input.close();
    }
}
