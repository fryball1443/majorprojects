//
public class NumbersToText {
   public static void main(String[] args) {
    
    //declare String
     String[] numbers = {"zero",
                         "one",
                         "two",
                         "three",
                         "four",
                         "five",
                         "six",
                         "seven", 
                         "eight",
                         "nine"};
    //for loop prints the number and then the word corresponding to that number( [1]: one )
     for (int i = 0; i < 9; i++) {
       System.out.printf("[%d]: %s\n", i, numbers[i]);
     }
   
   }      
}