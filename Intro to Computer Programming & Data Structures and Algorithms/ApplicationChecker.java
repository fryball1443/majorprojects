import java.util.Scanner;

//this program determines if the canidate should be interviewed based on their degree,
//  experience, and minimum starting salary

public class ApplicationChecker {
  public static void main(String[] args) {
       
    //scanner class will be used
      Scanner input = new Scanner(System.in);
      
        //variables
      String text;
      String firstName;
      String lastName;
      int  eduLvl      =  5;
      char xp          = 'n';
      int  minSalary   =  0;
        
        //ask the name
      System.out.printf("Enter your first and last name:\n");
      firstName = input.next();
      lastName = input.next();
      
      input.close();
     
    //ask the education level
     System.out.printf("%s, what is your highest education level?\n" +
                       "(answer with the corresponding number)\n", firstName);
     System.out.printf("1. High School Diploma\n"); 
     System.out.printf("2. Associate Degree\n");
     System.out.printf("3. Bachelor Degree\n");  
     System.out.printf("4. Graduate Degree\n"); 
                      
     eduLvl = input.nextInt();
     input.nextLine();
     
    //ask the marketing experience
     System.out.printf("Do you have any marketing experience?\n (Answer in lower case)\n");
     text = input.nextLine(); xp = text.charAt(0);
     
    //ask minimum starting salary
     System.out.printf("What would you want your starting salary to be?\n");
     minSalary = input.nextInt();
     
     
    //output what they answered
     System.out.printf("%s %s\nEducation Level: ", firstName, lastName);
     if (eduLvl == 1) {
       System.out.printf("High School Diploma\n");
     } else if (eduLvl == 2) {
       System.out.printf("Associate Degree\n");
     } else if (eduLvl == 3) {
       System.out.printf("Bachelor Degree\n");
     } else if (eduLvl == 4) {
       System.out.printf("Graduate Degree\n"); 
     }
     
     System.out.printf("Marketing Experience: %s\n", text); 
      
     System.out.printf("Minimum Asking Salary: %d\n", minSalary); 
    //output whether or not they will get an interview
     if     (eduLvl    >=  2) {
       if   (xp        == 'y') {
         if (minSalary <= 35000) {
           System.out.printf("Congradulations, %s!! You are eligable for an interview!\n" + 
                             "You will recieve an email in the coming weeks regarding the time and date for your interview", firstName);
         } else {
           System.out.printf("Sorry, %s. You are asking for too high of a starting pay: %d", firstName, minSalary);
         }
       } else {
         System.out.printf("Sorry, %s. In order to get an interview, you need marketing experience.", firstName);
       }
     } else {
       System.out.printf("Sorry, %s. In order to get an interview, you need an Associate Degree or higher.", firstName);
     }
     
  }
}