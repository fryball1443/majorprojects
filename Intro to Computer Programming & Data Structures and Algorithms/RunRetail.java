public class RunRetail {
   public static void main(String[] args){
      RetailItem jacket = new RetailItem();
      makeRetail(jacket, "Jacket", 12, -59.95);
      try {
         jacket.verifyPrice(jacket.getPrice());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      try {
         jacket.verifyUnits(jacket.getUnitsOnHand());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      
      RetailItem jeans = new RetailItem();
      makeRetail(jeans, "Designer Jeans", 40, 34.95);
      try {
         jeans.verifyPrice(jeans.getPrice());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      try {
         jeans.verifyUnits(jeans.getUnitsOnHand());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      
      RetailItem shirt = new RetailItem();
      makeRetail(shirt, "Shirt", -20, 24.95);
      try {
         shirt.verifyPrice(shirt.getPrice());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      try {
         shirt.verifyUnits(shirt.getUnitsOnHand());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
      
      
      }
      private static void makeRetail(RetailItem retail, String description, int
      unitsOnHand, double price){
      retail.setDescription(description);
      retail.setUnitsOnHand(unitsOnHand);
      retail.setPrice(price);
      
      System.out.println(retail.getDescription() + " "
      + retail.getUnitsOnHand() + " " + retail.getPrice());
   }
}