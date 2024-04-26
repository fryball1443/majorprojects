import java.util.Scanner;
import java.io.*;

public class thingy {
   public static void main(String[] args) {
   String output = nLetter((Letter1((lincolnLetter("","")),"")));
   System.out.printf("hello....\n%s", output);
   


     
     
     
   }
   
   public static String nLetter(String letter) {
     String myLetter = "howdy there how are you????????";
     letter = (letter + "\n" + myLetter);
     return letter;
   }
   public static String Letter1(String str, String newLine)
   {
     String Letter1="";
     Letter1=str+newLine+"this is jdog\n";
     return Letter1;
   }
   public static String lincolnLetter(String Lincoln, String newLine){
     String lincolnLetter = Lincoln + newLine +
      "good luck. \n";
      return lincolnLetter;
   }
}