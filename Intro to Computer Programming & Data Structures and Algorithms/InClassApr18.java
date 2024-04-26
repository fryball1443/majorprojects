import java.util.Random;

public class InClassApr18 {
  public static void main(String[] args) {
    //int[] numbers;
    //numbers = new int[15];
    int upperbound = 100;
    int lowerbound = 20;
    int noggin = 0;
    while (noggin == 0) {
      int freddy = randomGen(lowerbound, upperbound);
      System.out.println(freddy);
      if ((freddy <= lowerbound) || (freddy >= upperbound)) {
        break;
      }
    }
    
    System.out.println("");
    int distance = 5;
    int shotLength = randomGen(1,10);
    System.out.println(shotLength);
    distance = distance - shotLength;
    distance = (Math.abs(distance));
    System.out.println(distance);
  }
 //random number generator, 
 //lower bound is the minimum number that can be generated.
 //upperbound is the maximum number generated
  public static int randomGen(int lowerbound, int bound) {
    bound += 1;
    bound = bound - lowerbound;
    Random rand = new Random();
    int number  = rand.nextInt(bound);
    number = number + lowerbound;
    return number;
  }
}
