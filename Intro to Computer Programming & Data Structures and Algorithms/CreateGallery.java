import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateGallery {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "username", "password")) {
            // Create the GALLERY database
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE GALLERY");

            // Use the GALLERY database
            stmt.executeUpdate("USE GALLERY");

            // Create the PAINTER table
            stmt.executeUpdate("CREATE TABLE PAINTER (P_ID INT PRIMARY KEY, P_FirstName VARCHAR(255), P_LastName VARCHAR(255), P_Phone VARCHAR(255), P_Email VARCHAR(255))");

            // Insert sample data into the PAINTER table
            stmt.executeUpdate("INSERT INTO PAINTER VALUES (1, 'Pablo', 'Picasso', '123-456-7890', 'picasso@gmail.com')");
            stmt.executeUpdate("INSERT INTO PAINTER VALUES (2, 'Vincent', 'van Gogh', '123-456-7891', 'vangogh@gmail.com')");
            stmt.executeUpdate("INSERT INTO PAINTER VALUES (3, 'Leonardo', 'da Vinci', '123-456-7892', 'davinci@gmail.com')");
            stmt.executeUpdate("INSERT INTO PAINTER VALUES (4, 'Rembrandt', 'van Rijn', '123-456-7893', 'rembrandt@gmail.com')");
            stmt.executeUpdate("INSERT INTO PAINTER VALUES (5, 'Frida', 'Kahlo', '123-456-7894', 'frida@gmail.com')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
