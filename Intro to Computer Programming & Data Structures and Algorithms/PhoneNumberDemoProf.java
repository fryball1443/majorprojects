import java.util.*;

public class PhoneNumberDemoProf {
   public static void main(String[] args) {
      StringBuilder pnumber=new StringBuilder();
      PhoneNumber p1 = new PhoneNumber();
      System.out.print(p1.pnumberFormat(pnumber));
      
     
   }
}

class PhoneNumber {
   public String pnumberFormat(StringBuilder pnumber) {
      Scanner input = new Scanner(System.in);
      String temp;// = "0";
      do {
      //while (temp.length() != 10) {
         System.out.print("Enter phone number: ");
         temp = input.nextLine();
         boolean num = false;
         for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) >= '0' && temp.charAt(i) <= '9') {
               num = true;
            } else {
               num = false;
            }
         }
         if (num == true) {
            if (temp.length() != 10) {
               System.out.printf(" error...\n The number you inputted is in the incorrect format.\n There should be 10 digits in a phone number.\n You have inputted %d digits\n\n", temp.length());
               //System.out.printf("index:%d\n", i);
            }
         } else {
            System.out.println(" error...\n Incorrect format.\n Please type only numbers.\n\n");
         }
      } while (temp.length() != 10);
      
      pnumber.append(temp);
      pnumber.insert (0, "(");
      pnumber.insert (4, ") ");
      pnumber.insert (9, "-");
      //(=0,)=4,8 add
      return pnumber.toString();
   }
}
 /*/
      boolean correct = false;
      String number;
      
      while ((number >= 1000000000) && (number <= 9999999999)) {
         String number;
         Scanner sc = new Scanner(System.in);
         System.out.print("Enter phone number: ");
         number = sc.nextLine();

      }
      
     
      String newCity = "";
      StringBuilder city=new StringBuilder("Franklin");
      // String
      // city ="new yorki"
      city.append(" is a ");
      city.append (200);
      city.append(" year old city");
      System.out.println(city);
      //converting stringbuklder to string newcity=city.toString();
      //its an error newcity.append("and");
      System.out.println(newCity);
      // System.out.println(city.length());
      
     //replace(start,end,str);
      System.out.println(city);
      city.replace(14,18,"178");
      System.out.println(city);
      
      //delete, deleteCharAt, and setCharAt
      int bindex, eindex;
      
      city.delete(20,25);
      System.out.println(city);
     //*/
