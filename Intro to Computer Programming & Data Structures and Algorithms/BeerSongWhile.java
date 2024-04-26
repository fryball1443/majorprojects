public class BeerSongWhile {
  public static void main(String[] args) {
    //declare variables
     int beer = 99;
     String plural = "s"; 
     
    //prints the 99 bottles of beer on the wall song
     while (beer > 0) {
       System.out.printf("%d bottle%s of beer on the wall\n%d bottle%s of beer\n" +
                         "Take one down, pass it around\n", beer, plural, beer, plural);
       beer--;
       
      //if no beer is left, prints no more bottles. if one beer is left, removes the s from bottle
       if (beer == 0) {
         System.out.printf("No more bottles of beer on the wall");
       } else {
         if (beer == 1) {
           plural = "";
       }
         
       System.out.printf("%d bottle%s of beer on the wall\n\n", beer, plural);
       }
     }
  }
}