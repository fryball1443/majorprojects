public class BeerSongFor {
  public static void main(String[] args) {
    //prints the 99 bottles of beer on the wall song
     for (int i = 99; i > 0; i--) {
       String plural = "s";
       System.out.printf("%d bottle%s of beer on the wall\n%d bottle%s of beer\n" +
                         "Take one down, pass it around\n", i, plural, i, plural);
       
       
      //if no beer is left, prints no more bottles. if one beer is left, removes the s from bottle
       if (i == 0) {
         System.out.printf("No more bottles of beer on the wall");
       } else {
         if (i == 1) {
           plural = "";
       }
         
       System.out.printf("%d bottle%s of beer on the wall\n\n", (i - 1), plural);
       }
     }
  }
}