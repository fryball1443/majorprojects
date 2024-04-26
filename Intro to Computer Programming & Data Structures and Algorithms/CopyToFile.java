import java.util.Scanner;

public class CopyToFile {
   public static void main(String[] args) {
     //Scanner class will be used
      Scanner input = new Scanner(System.in);
     
     //declare variables
     
     //
      System.out.print("Enter file to copy to: ");
      String outputPath = keyboard.nextLine();
      
     //create file objects
      File inputFile  = new File(inputPath);
      File outputFile = new File(outputPath);
      
     //create input scanner and output printwriter objects
      Scanner input      = new Scanner(inputFile);
      PrintWriter output = new PrintWriter(outputFile);
      
     //Loop the input file
      String line = "";
      while (input.hasNext()) {
        line = input.nextLine();
        output.printf("Line\n");
        
      }
      
     //get line from input file
      
   }      
}