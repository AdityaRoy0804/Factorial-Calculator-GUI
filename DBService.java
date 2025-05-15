import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private static final String URL = "jdbc:mysql://localhost:3306/factorial_app";
    private static final String USER = "root";
    private static final String PASSWORD = "Aditya@2004";  // change to your MySQL password

    public DBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(int input, long iterative, long recursive) {
        String sql = "INSERT INTO history (input_number, iterative_result, recursive_result) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, input);
            ps.setLong(2, iterative);
            ps.setLong(3, recursive);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        String sql = "SELECT input_number, iterative_result, recursive_result, created_at FROM history ORDER BY id DESC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String row = String.format("Input: %d | Iterative: %d | Recursive: %d | Time: %s",
                        rs.getInt("input_number"),
                        rs.getLong("iterative_result"),
                        rs.getLong("recursive_result"),
                        rs.getTimestamp("created_at").toString());
                history.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}

