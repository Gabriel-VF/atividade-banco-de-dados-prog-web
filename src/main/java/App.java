import database.*;

import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
      Connection connection = ConnectionFactory.getInstance().getConnection();
      connection.close();
    }
}
