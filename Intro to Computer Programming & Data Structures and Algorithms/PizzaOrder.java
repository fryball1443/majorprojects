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
     System.out.println("What size pizza would you like?");
     System.out.print  (" Small           $"); System.out.printf("%7.2f\n", SMALL);
     System.out.print  (" Medium          $"); System.out.printf("%7.2f\n", MEDIUM);
     System.out.print  (" Large           $"); System.out.printf("%7.2f\n", LARGE);  
                      
     text = input.nextLine(); size  = text.charAt(0); 
     System.out.println("Deep-dish or hand-tossed?");
     text = input.nextLine(); crust = text.charAt(0); 
     System.out.println("Answer yes or no to each topping:");
     System.out.print  (" pepperoni?     "); text = input.nextLine(); pepperoni   = text.charAt(0); 
     System.out.print  (" sausage?       "); text = input.nextLine(); sausage     = text.charAt(0); 
     System.out.print  (" onion?         "); text = input.nextLine(); onion       = text.charAt(0); 
     System.out.print  (" mushroom?      "); text = input.nextLine(); mushroom    = text.charAt(0);      
     System.out.print  (" extra cheese?  "); text = input.nextLine(); extraCheese = text.charAt(0);      
     
     
    //print the responses
     System.out.println();
     if ((crust == 'h')) {
       System.out.println(" Hand-tossed");
     } else if (crust == 'd') {
       System.out.println(" Deep-dish");
     }
    //print the size and cost of that size
     if          (size == 's') {
       meatCost  = 1.00;
       vegCost   = 0.50;
       exChzCost = 0.75;
       
       System.out.print  (" Small           $"); System.out.printf("%7.2f\n", SMALL);
       subtotal += SMALL;
        
     } else if  (size == 'm') {
       meatCost  = 2.00;
       vegCost   = 0.75;
       exChzCost = 1.00;
       
       System.out.print  (" Medium          $"); System.out.printf("%7.2f\n", MEDIUM);
       subtotal += MEDIUM;
       
     } else if  (size == 'l') {
       meatCost  = 3.00;
       vegCost   = 1.00;
       exChzCost = 1.25;
       
       System.out.print  (" Large           $"); System.out.printf("%7.2f\n", LARGE);  
       subtotal += LARGE;
       
     }
     
    //print toppings and additional cost of the toppings
     if   (pepperoni == 'y') {
       subtotal += meatCost;
       System.out.print(" Pepperoni     + $"); System.out.printf("%7.2f\n", meatCost);
     }
     if     (sausage == 'y') {
       subtotal += meatCost;
       System.out.print(" Sausage       + $"); System.out.printf("%7.2f\n", meatCost);
     }
     if       (onion == 'y') {
       subtotal += vegCost;
       System.out.print(" Onion         + $"); System.out.printf("%7.2f\n", vegCost);
     }
     if    (mushroom == 'y') {
       subtotal += vegCost;
       System.out.print(" Mushroom      + $"); System.out.printf("%7.2f\n", vegCost);
     }
     if (extraCheese == 'y') {
       subtotal += exChzCost;
       System.out.print(" Extra Cheese  + $"); System.out.printf("%7.2f\n", exChzCost);
     }
     
     
    //output subtotal and tax
     tax   = (SALES_TAX / 100) * subtotal;
     total = (1 + (SALES_TAX / 100)) * subtotal;

     System.out.print  ("\n Subtotal:       $"); System.out.printf("%7.2f\n", subtotal);
     System.out.print    ("      Tax:       $"); System.out.printf("%7.2f\n", tax);
     System.out.print    ("    Total:       $"); System.out.printf("%7.2f\n\n", total);
    //input payment amount and output change
     System.out.println  ("Enter the payment amount:");
     System.out.print    ("  Payment:       $"); payment = input.nextInt();
     if (payment < total) {
       System.out.print  ("Input additional payment.\n" + 
                          "  Missing:       $"); total = total - payment; System.out.printf("%7.2f\n", total);
       System.out.print  ("  Payment:       $"); payment = input.nextInt();
     }
     System.out.print    ("   Change:       $"); System.out.printf("%7.2f\n", (payment - total));
     
     
     
  }
}