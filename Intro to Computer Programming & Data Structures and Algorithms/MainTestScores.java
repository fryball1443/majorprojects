public class MainTestScores
{
    public static void main(String[] args)
    {
        double[] scores = {90, 80, 70, 60, 30.24};
        TestScores testScores = new TestScores(scores);

        try
        {
            System.out.println("Average score: " + testScores.getAverageScore());
        }
        catch (InvalidTestScore ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}