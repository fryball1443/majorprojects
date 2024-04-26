import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase2 {
    public static void main(String[] args) {
        // the name of the database to create
        String dbName = "GALLERY";

        // the name of the table to create
        String tableName = "PAINTER";

        // the columns of the table
        String[] columns = {
            "P_ID INTEGER PRIMARY KEY",
            "P_FirstName TEXT NOT NULL",
            "P_LastName TEXT NOT NULL",
            "P_Phone TEXT NOT NULL",
            "P_Email TEXT NOT NULL"
        };

        // sample data to insert into the table
        String[][] data = {
            {"1", "John", "Smith", "555-555-1212", "jsmith@email.com"},
            {"2", "Jane", "Doe", "555-555-1213", "jdoe@email.com"},
            {"3", "Bill", "Johnson", "555-555-1214", "bjohnson@email.com"},
            {"4", "Sue", "Williams", "555-555-1215", "swilliams@email.com"},
            {"5", "Mary", "Jones", "555-555-1216", "mjones@email.com"}
        };

        try {
            // create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");

            // create a statement object to execute SQL commands
            Statement statement = conn.createStatement();

            // create the table
            String createTableSql = "CREATE TABLE " + tableName + " (";
            for (int i = 0; i < columns.length; i++) {
                createTableSql += columns[i];
                if (i < columns.length - 1) {
                    createTableSql += ", ";
                }
            }
            createTableSql += ")";
            statement.executeUpdate(createTableSql);

            // insert the sample data
            for (int i = 0; i < data.length; i++) {
                String insertRowSql = "INSERT INTO " + tableName + " VALUES (";
                for (int j = 0; j < data[i].length; j++) {
                    insertRowSql += "'" + data[i][j] + "'";
                    if (j < data[i].length - 1) {
                        insertRowSql += ", ";
                    }
                }
                insertRowSql += ")";
                statement.executeUpdate(insertRowSql);
            }

            // close the statement and connection objects
            statement.close();
            conn.close();

            System.out.println("Database and table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
