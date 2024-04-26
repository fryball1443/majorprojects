import javax.swing.JOptionPane;
import java.util.Scanner;

public class FinalExamDemo {
   public static void main(String[] args) {
      String input; 
      int questions;
      int missed;
      
      input = JOptionPane.showInputDialog( "How many questions are on the final exam?");
      questions = Integer.parseInt (input);
      
      input = JOptionPane.showInputDialog( "How many questions did you miss on the final exam?");
      missed = Integer.parseInt (input);
      
     //created an object and calling parameterized constructor
      FinalExam exam = new FinalExam(questions, missed);
      JOptionPane.showMessageDialog(null, " Each question counts as " + exam.getPointsEach() + 
                                        "\n The exam score is       " + exam.getScore() +
                                        "\n The exam grade is       " + exam.getGrade());
   
   }  
}