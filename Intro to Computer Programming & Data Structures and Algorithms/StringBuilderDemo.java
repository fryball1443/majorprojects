import java.util.*;

public class StringBuilderDemo {
   public static void main(String[] args) {
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
      
      
   }
}