package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private final String databaseName = "loja_jdbc";
  private static String user = "";
  private static String password = "";
  private final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private final String URL_PREFIX = "jdbc:mysql://localhost:3306/";
  private final String URL = URL_PREFIX + databaseName;
  private static Connection connection;
  private static ConnectionFactory instance;

  private ConnectionFactory() {
    try {
      Class.forName(DRIVER);
      connection = DriverManager.getConnection(URL, user, password);
      System.out.println("Sucesso ao se conectar com banco de dados.");
    } catch (SQLException e) {
      System.err.printf("Erro ao se conectar com banco de dados \"%s\": %s\n", databaseName, e.getMessage());
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.printf("Erro ao se conectar com banco de dados: driver \"%s\" faltando\n", DRIVER);
      e.printStackTrace();
    }
  }

  public static ConnectionFactory getInstance() {
    if (instance == null) {
      synchronized (ConnectionFactory.class) {
        if (instance == null) {
          instance = new ConnectionFactory();
          return instance;
        }
      }
    }
    return instance;
  }

  public Connection getConnection() {
    return connection;
  }

  public static void setUser(String user) {
    ConnectionFactory.user = user;
  }

  public static void setPassword(String password) {
    ConnectionFactory.password = password;
  }

  public void closeConnection() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("Conexão com banco de dados fechada");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao fechar conexão!");
      e.printStackTrace();
    }
  }
}
