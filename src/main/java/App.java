import database.*;

import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
      ConnectionFactory.setUser("");
      ConnectionFactory.setPassword("");
      Connection connection = ConnectionFactory.getInstance().getConnection();
      connection.close();
    }
}
