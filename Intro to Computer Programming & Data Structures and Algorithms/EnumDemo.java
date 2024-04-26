//enum - enumarated data type.
public class EnumDemo
{
   enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }
   
   enum Q1 { JAN, FEB, MARCH }
     
   public static void main(String[] args)
   {
      Day workDay=Day.THURSDAY;
            System.out.println(workDay);  
      
      if(workDay==Day.THURSDAY)
      {
         System.out.println("Its almost friday ");
       }
       else
       {
         System.out.println("Still not close to friday ");
       }
      System.out.println("The ordinal value for Friday is "+ Day.FRIDAY.ordinal());
      
      
   }
}