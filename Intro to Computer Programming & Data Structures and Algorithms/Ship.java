public class Ship {

   String ship;
   int year;
   
  //construtor for name and year
   public Ship(String ship,int year) {
      this.ship=ship;
      this.year=year;
   }
   
  //get methods
   public String getShipName() {
      return ship;
   }
   
   public int getYear() {
      return year;
   }
   
  //Override toString method
   public String toString() {
      return "Ship Name :"+getShipName()+
             "\nBuilt Year :"+getYear();
   }

}

