import java.sql.*;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/DBProject";
        String username = "root";
        String password = "root";
        Connection con;
        try {
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}
