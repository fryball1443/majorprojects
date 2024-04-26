import java.util.Arrays;

public class TestScores
{
    private double[] scores;

    public TestScores(double[] scores)
    {
        this.scores = scores;
    }

    public double getAverageScore() throws InvalidTestScore
    {
        for (double score : scores)
        {
            if (score < 0 || score > 100)
            {
                throw new InvalidTestScore("Test scores must be between 0 and 100");
            }
        }

        return Arrays.stream(scores).average().getAsDouble();
    }
}
