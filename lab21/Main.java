import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {

            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\sql\\users.db");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("login"));
            }
        } catch (ClassNotFoundException cl) {
            System.out.println("Class not found");
        } catch (SQLException sql) {
            System.out.println("SQL error");
        }
    }
}