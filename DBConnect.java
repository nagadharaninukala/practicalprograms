import java.sql.*;
public class DBConnect{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "dharani@naga93";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM emp");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}