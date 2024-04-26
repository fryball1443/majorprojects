import java.io.*;
import java.util.*;

class PasswordVerifier {
   private String password;
   public PasswordVerifier(String a){
      password = a;
   }
   public boolean verify(){
      boolean upperCase,lowerCase,num;
      upperCase = false;
      lowerCase = false;
      num = false;
      if (password.length() < 6)
         return false;
      for (int i=0; i<password.length(); i++) {
         if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
            lowerCase = true;
         }
         if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
            upperCase = true;
         }
         if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
            num = true;
         }
      }
      
      if (upperCase && lowerCase && num) {
         return true;
      } else {
         return false;
      }
   }
}

