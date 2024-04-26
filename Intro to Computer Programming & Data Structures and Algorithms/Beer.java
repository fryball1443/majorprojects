public class Beer
{
   public static void main(String[] args)
   {
   int num = 99;
   while(num > 0)
      {
         System.out.println(num + " bottles of beer on the wall" + "\n" + 
                            num + " bottles of beer" + "\n" + 
                            "Take one down, pass it around");
         num--;
         System.out.println(num + " bottles of beer on the wall" + "\n");
      }
   }
}