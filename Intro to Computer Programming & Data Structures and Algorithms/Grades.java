import java.util.Scanner;

//this program asks what grade the student made in the class
// and displays their final grade

public class Grades {
  public static void main(String[] args) {
    //constants
     final double ASSIGNMENT_WEIGHT = 50;
     final double       EXAM_WEIGHT = 100;
     
    //scanner class will be used
     Scanner input = new Scanner(System.in);
    
    //variables
     double normalGrades = 0;
     double examGrades = 0;
     double totalGrade = 0;
    
    //user inputs grades for each assignments
     System.out.println("What grade did you get in each assignment?");
     System.out.print(" 1: "); normalGrades += input.nextInt();
     System.out.print(" 2: "); normalGrades += input.nextInt();
     System.out.print(" 3: "); normalGrades += input.nextInt();
     System.out.print(" 4: "); normalGrades += input.nextInt();
     System.out.print(" 5: "); normalGrades += input.nextInt();
     System.out.print(" 6: "); normalGrades += input.nextInt();
     System.out.print(" 7: "); normalGrades += input.nextInt();
     System.out.print(" 8: "); normalGrades += input.nextInt();
     System.out.print(" 9: "); normalGrades += input.nextInt();
     System.out.print("10: "); normalGrades += input.nextInt();
     System.out.print("11: "); normalGrades += input.nextInt();
     System.out.print("12: "); normalGrades += input.nextInt();
     System.out.println();
     
    //user inputs grades for each exam
     System.out.println("What grade did you get on each exam?");
     System.out.print(" 1: "); examGrades += input.nextInt();
     System.out.print(" 2: "); examGrades += input.nextInt();
     System.out.print(" 3: "); examGrades += input.nextInt();
     System.out.print(" 4: "); examGrades += input.nextInt();
     System.out.println();
     
    //system calculates the total grade using the weighted number
    // for each point worth
     totalGrade = (normalGrades * (ASSIGNMENT_WEIGHT / 100)) + 
                  (examGrades * (EXAM_WEIGHT / 100));
     System.out.print("Total points earned: " + totalGrade + "\n" +
                      "Final Grade:         " + (totalGrade / 10) + "%");     

  }
}