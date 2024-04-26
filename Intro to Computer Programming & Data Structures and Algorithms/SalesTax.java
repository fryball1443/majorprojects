public class SalesTax
{
   public static void main(String[] args)
   {
      double amount = 100.50;
      double taxPercent = 7;
      double tax = taxPercent / 100;
      double taxAmount = tax * amount;
      double total = (1 + tax) * amount;

      System.out.println("Subtotal: $" + amount + "\n" + "Tax: " + 
                           "$" + taxAmount +
                           //taxPercent + "%" +  
                           "\n" + "Total: $" + total);
      
   }
}

//Do you know how I can make it so it rounds the number to the nearest 100th?