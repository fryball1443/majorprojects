import java.util.Scanner;

//this program asks how many hours a person gets paid for and how much they make

public class Payday {
  public static void main(String[] args) {
    //constants
    final double HOURLY_PAY = 25.25;
    //scanner class will be used
    Scanner input = new Scanner(System.in);
    //variables
    double hours = 0;
    
    //user inputs hours for each day
    System.out.println("How many hours did you work on each day?  ");
    System.out.print("Sunday:    "); hours += input.nextInt();
    System.out.print("Monday:    "); hours += input.nextInt();
    System.out.print("Tuesday:   "); hours += input.nextInt();
    System.out.print("Wednesday: "); hours += input.nextInt();
    System.out.print("Thursday:  "); hours += input.nextInt();
    System.out.print("Friday:    "); hours += input.nextInt();
    System.out.print("Saturday:  "); hours += input.nextInt();
    
    //output
    System.out.println("Overtime pay: " + ((hours - 40) * (HOURLY_PAY * 1.5)));
    
    /*
    System.out.print("How many hours did you work on Sunday?  ");
    double hourSunday = input.nextInt();
    System.out.print("How many hours did you work on Monday?  ");
    double hourMonday = input.nextInt();
    System.out.print("How many hours did you work on Tuesday?  ");
    double hourTuesday = input.nextInt();
    System.out.print("How many hours did you work on Wednesday?  ");
    double hourWednesday = input.nextInt();
    System.out.print("How many hours did you work on Thursday?  ");
    double hourThursday = input.nextInt();
    System.out.print("How many hours did you work on Friday?  ");
    double hourFriday = input.nextInt();
    System.out.print("How many hours did you work on Saturday?  ");
    double hourSaturday = input.nextInt();
    
    double totalHours = hourSunday + hourMonday + hourTuesday + hourWednesday +
                        hourThursday + hourFriday + hourSaturday;
    System.out.println(totalHours);
    
    
    double otHours = totalHours - 40;
    System.out.println(otHours);
    double percentNormal = otHours / totalHours;
    System.out.println(percentNormal);
    double normalHours = totalHours - otHours;
    System.out.println(normalHours);
    double overtimePay = 1.5 * HOURLY_PAY;
    */
  }
}