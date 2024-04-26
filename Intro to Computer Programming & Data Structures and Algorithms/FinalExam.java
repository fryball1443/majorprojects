
public class FinalExam extends GradeActivity {
   private int numQuestions;
   private double pointsEach;
   private int numMissed;
   public FinalExam(int questions, int missed) {
      double numericScore;
      numQuestions = questions;
      numMissed    = missed;
      
      pointsEach = 100.0 / questions;
      numericScore = 100.0 - (missed * pointsEach);
      
      setScore(numericScore); //calling superclass method
   }
   
   public double getPointsEach() {
      
      return numMissed;
   }
}