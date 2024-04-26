public class MonthProgram {
  public static void main(String[] args) {
    String month = "January";
    Month jan = new Month(month);
    System.out.printf("%s has %d days\n", month, jan.numberDays());
  }
} 