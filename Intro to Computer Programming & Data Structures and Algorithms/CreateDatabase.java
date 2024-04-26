public class CreateDatabase {
   
   public static void main(String args[]) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("com.mysql.jdbc.Driver");
         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
         System.out.println("Connected database successfully...");
         
         // Create database 
         stmt = c.createStatement();
         String sql = "CREATE DATABASE GALLERY";
         stmt.executeUpdate(sql);
         System.out.println("Database created successfully...");
         
         // Create table in the database 
         sql = "CREATE TABLE PAINTER " +
               "(P_ID INTEGER PRIMARY KEY, " +
               " P_FirstName VARCHAR(50), " + 
               " P_LastName VARCHAR(50), " + 
               " P_Phone VARCHAR(20), " + 
               " P_Email VARCHAR(50))"; 
         stmt.executeUpdate(sql);
         System.out.println("Table created successfully...");
         
         // Insert into table
         sql = "INSERT INTO PAINTER (P_ID, P_FirstName, P_LastName, P_Phone, P_Email) VALUES (1, 'John', 'Smith', '123-456-7890', 'john@smith.com')";
         sql += ", (2, 'Jane', 'Doe', '098-765-4321', 'jane@doe.com')";
         sql += ", (3, 'Adam', 'Brown', '111-222-3333', 'adam@brown.com')";
         sql += ", (4, 'Jack', 'Smith', '444-555-6666', 'jack@smith.com')";
         sql += ", (5, 'Andrew', 'Williams', '777-888-9999', 'andrew@williams.com')";
         stmt.executeUpdate(sql);
         System.out.println("Records inserted successfully...");
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }
}