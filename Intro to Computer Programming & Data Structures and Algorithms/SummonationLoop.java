public class SummonationLoop {
  public static void main(String[] args) {
    int total = 0;
    for (int i = 1; i <= 5; i++) {
      int current = i;
      current = current * current;
      total = total + current;

      
      //print
        System.out.print(String.format("0x%08X", total)+ "  ");
        String binaryTotal = (Integer.toBinaryString(total));
        int binaryInt = (Integer.parseInt(binaryTotal));
      
        System.out.printf("0b%08d" , binaryInt);
      System.out.print("  " + total + "\n");
    }
  }
}

public class gae {
  public static void main(String[] args) {
    int total = 0;
    for (int i = 1; i <= 5; i++) {
      int current = i;
      current = current * current;
      total = total + current;

      
      //print
        System.out.print(String.format("0x%08X", total)+ "  ");
        String binaryTotal = (Integer.toBinaryString(total));
        int binaryInt = (Integer.parseInt(binaryTotal));
      
        System.out.printf("0b%08d" , binaryInt);
      System.out.print("  " + total + "\n");
    }
  }
}