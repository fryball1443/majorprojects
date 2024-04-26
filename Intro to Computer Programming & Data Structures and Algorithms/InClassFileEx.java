import java.util.Scanner;
import java.io.*;

public class InClassFileEx {
  public static void main(String[] args) throws IOException {
    //reads file then writes to another file
    //in test
    println("fuck you");
     File readFile = new File("/Users/fryball/test/input.txt");
     Scanner input = new Scanner(readFile);
     PrintWriter output = new PrintWriter("/Users/fryball/test/output.txt");
     
     
     String beingRead = "";
     while (input.hasNext()) {
       beingRead = input.nextLine();
       output.println(beingRead);
     }
     
     input.close();
     output.close();
     System.out.println(beingRead);
  }
}