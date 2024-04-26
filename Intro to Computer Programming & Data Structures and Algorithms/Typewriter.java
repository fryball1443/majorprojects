import java.util.Scanner;
import java.io.*;

public class Typewriter {
  public static void main(String[] args) throws IOException {
     //
     //stores path for files; in [user home folder]/java directory, should work on any operating system
      String mainPath = "~/java";
      mainPath = userHome(mainPath);
      String writePath  = mainPath + "/typewriter.txt";

    
     //we will be using the scanner class
      Scanner input = new Scanner(System.in);
     
     //creates a loop so that the you keep typing until you type "exit"(not case sensitive)
      String text;
      int cont = 0;
      
      FileWriter fwriter = new FileWriter(writePath, true);
      PrintWriter file  = new PrintWriter(fwriter);
      file.print("\n\n\n\n");

      System.out.printf ("\n  Type a line of text to store in the file then press enter\n     (type exit to save and quit the file)\n"); 

      while (cont == 0) {        
        text = input.nextLine(); 
        if (keyWordScanner(text, "exit")) {
          cont++;
        } else {
          file.println(text);
        }
      }
      file.close();
         
  }
 
 //finds the user home folder and replaces ~ in path to the path of the user home folder
  public static String userHome(String path) {
    path = System.getProperty("user.home") + path.substring(1);
    File dir = new File(path);
    if (!dir.exists()) {
      dir.mkdir();
    }
    return path;
  }
 
 //scans for the keyword in the text. if the text contains the keyword, returns true 
  public static boolean keyWordScanner(String text, String keyword) {
    text = text.toLowerCase();
    if (text.contains(keyword)) {
      return true;
    } else {
      return false;
    }
  }
  
}
