
public class NiosIIjava {
  public static void main(String[] args) {
    int kidneys = 3;
    kidneys = myMethod();
    System.out.println("\n" + kidneys + "\n");
  }

  public static int myMethod() {
    int r2 = 1, r3 = 2, r5 = 0;
    if (r2 + r3 > 10)
        r5 = 1;
    else if (r2 * r3 == 2)
        r5 = 2;
    else
        r5 = 3;
    return r5;
  }
}