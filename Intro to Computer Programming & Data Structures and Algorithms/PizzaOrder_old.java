import java.util.Scanner;

//this program asks what size, crust, and toppings the user wants

public class PizzaOrder {
  public static void main(String[] args) {
    //constants
     final double SALES_TAX =  9.25;
     final double SMALL     = 10.00;
     final double MEDIUM    = 13.00;
     final double LARGE     = 16.00; 
      
    
    //scanner class will be used
     Scanner input = new Scanner(System.in);
     
    //variables
     String text;
     //double sizeMult  = 1;
     char size        = 's';
     char crust       = 'h';
     char pepperoni   = 'n';
     char sausage     = 'n';
     char onion       = 'n';
     char mushroom    = 'n';
     char extraCheese = 'n';
     double meatCost  = 1.00;
     double vegCost   = 0.50;
     double exChzCost = 0.75;
     double subtotal  = 0.00;
     double total     = 0.00;
     double payment   = 0.00;
     double tax;
     
     
    
    //ask what size, crust, and toppings the user wants
     System.out.println("What size pizza would you like?" + "\n" + "small - $" + SMALL +
                        "\n" + "medium - $" + MEDIUM + "\n" + "large - $" + LARGE);
     text = input.nextLine(); size  = text.charAt(0); 
     System.out.println("Deep-dish or hand-tossed?");
     text = input.nextLine(); crust = text.charAt(0); 
     System.out.println("  Answer yes or no to each topping:");
     System.out.print("pepperoni?     "); text = input.nextLine(); pepperoni   = text.charAt(0); 
     System.out.print("sausage?       "); text = input.nextLine(); sausage     = text.charAt(0); 
     System.out.print("onion?         "); text = input.nextLine(); onion       = text.charAt(0); 
     System.out.print("mushroom?      "); text = input.nextLine(); mushroom    = text.charAt(0);      
     System.out.print("extra cheese?  "); text = input.nextLine(); extraCheese = text.charAt(0);      
     
     
    //print the responses
     if ((crust == 'h')) {
       System.out.println("Hand-tossed");
     } else if (crust == 'd') {
       System.out.println("Deep-dish");
     }
     
     if          (size == 's') {
       System.out.println  ("Small           $" + SMALL);
       subtotal += SMALL;
       if   (pepperoni == 'y') {
         subtotal += meatCost;
         System.out.println("Pepperoni     + $" + meatCost);
       }
       if     (sausage == 'y') {
         subtotal += meatCost;
         System.out.println("Sausage       + $" + meatCost);
       }
       if       (onion == 'y') {
         subtotal += vegCost;
         System.out.println("Onion         + $" + vegCost);
       }
       if    (mushroom == 'y') {
         subtotal += vegCost;
         System.out.println("Mushroom      + $" + vegCost);
       }
       if (extraCheese == 'y') {
         subtotal += exChzCost;
         System.out.println("Extra Cheese  + $" + exChzCost);
       }
         
     } else if  (size == 'm') {
       meatCost  = 2.00;
       vegCost   = 0.75;
       exChzCost = 1.00;
       
       
       System.out.println  ("Medium          $" + MEDIUM);
       subtotal += MEDIUM;
       
       if   (pepperoni == 'y') {
         subtotal += meatCost;
         System.out.println("Pepperoni     + $" + meatCost);
       }
       if     (sausage == 'y') {
         subtotal += meatCost;
         System.out.println("Sausage       + $" + meatCost);
       }
       if       (onion == 'y') {
         subtotal += vegCost;
         System.out.println("Onion         + $" + vegCost);
       }
       if    (mushroom == 'y') {
         subtotal += vegCost;
         System.out.println("Mushroom      + $" + vegCost);
       }
       if (extraCheese == 'y') {
         subtotal += exChzCost;
         System.out.println("Extra Cheese  + $" + exChzCost);
       }
       
       
       
     } else if  (size == 'l') {
       meatCost  = 3.00;
       vegCost   = 1.00;
       exChzCost = 1.25;
       
       System.out.println  ("Large           $" + LARGE);
       subtotal += LARGE;
       
       if   (pepperoni == 'y') {
         subtotal += meatCost;
         System.out.println("Pepperoni     + $" + meatCost);
       }
       if     (sausage == 'y') {
         subtotal += meatCost;
         System.out.println("Sausage       + $" + meatCost);
       }
       if       (onion == 'y') {
         subtotal += vegCost;
         System.out.println("Onion         + $" + vegCost);
       }
       if    (mushroom == 'y') {
         subtotal += vegCost;
         System.out.println("Mushroom      + $" + vegCost);
       }
       if (extraCheese == 'y') {
         subtotal += exChzCost;
         System.out.println("Extra Cheese  + $" + exChzCost);
       }
     }
     tax   = (SALES_TAX / 100) * subtotal;
     total = (1 + (SALES_TAX / 100)) * subtotal;

     System.out.println  ("\nSubtotal:       $" +  subtotal);
     System.out.println    ("     Tax:       $" + tax);
     System.out.println    ("   Total:       $" + total);
     System.out.println    ("Enter the payment amount:");
     System.out.print      (" Payment:       $"); payment = input.nextInt();
     System.out.print      ("  Change:       $" + (payment - total));
     
     
     
  }
}