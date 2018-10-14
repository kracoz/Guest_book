import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDBTest {
    public static void main(String[] args) {
        ConnectDBTest m = new ConnectDBTest();
        m.testDatabase();
    }


    private void testDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/guestdb";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM JC_GUEST");
                while (rs.next()) {
                    String str = rs.getString("guest_id") + ":" + rs.getString(2);
                    System.out.println("Guest:" + str);
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}