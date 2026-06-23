package database.daoimpl;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import database.entities.Seller;
import database.dao.SellerDAO;

public class SellerDAOImpl implements SellerDAO {
  private Connection conn;

  public SellerDAOImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public List<Seller> findAll() {
    List<Seller> sellers = new ArrayList<>();

    DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl(conn);

    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM seller")) {

      while (rs.next()) {
        Seller seller = new Seller();
        seller.setId(rs.getInt("id"));
        seller.setName(rs.getString("name"));
        seller.setEmail(rs.getString("email"));
        seller.setBirthDate(rs.getDate("birth_date").toLocalDate());
        seller.setBaseSalary(rs.getDouble("base_salary"));
        seller.setDepartment(departmentDAOImpl.findById(rs.getInt("department_id")));

        sellers.add(seller);
      }
    } catch (SQLException e) {
      System.err.println("Erro ao listar vendedor");
      e.printStackTrace();
    }

    return sellers;
  }

  @Override
  public Seller findById(Integer id) {
    String sql = "SELECT * FROM seller WHERE id = ?";
    Seller seller = new Seller();
    DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl(conn);
    seller.setId(id);

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        seller.setName(rs.getString("name"));
        seller.setEmail(rs.getString("email"));
        seller.setBirthDate(rs.getDate("birth_date").toLocalDate());
        seller.setBaseSalary(rs.getDouble("base_salary"));
        seller.setDepartment(departmentDAOImpl.findById(rs.getInt("department_id")));
      }
    } catch (SQLException e) {
      System.out.println("Erro ao tentar pesquisar vendedor por id");
      e.printStackTrace();
    }

    return seller;
  }

  @Override
  public void insert(Seller seller) {

    try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO seller (name, email, birth_date, base_salary, department_id) VALUES (?, ?, ?, ?, ?)")) {
      pstmt.setString(1, seller.getName());
      pstmt.setString(2, seller.getEmail());
      pstmt.setDate(3, Date.valueOf(seller.getBirthDate()));
      pstmt.setDouble(4, seller.getBaseSalary());
      pstmt.setInt(5, seller.getDepartment().getId());

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Vendedor inserido com sucesso!");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao inserir vendedor");
      e.printStackTrace();
    }

  }

  @Override
  public void update(Seller seller) {
    String sql = "UPDATE seller SET name = ?, email = ?, birth_date = ?, base_salary = ?, department_id = ? WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, seller.getName());
      pstmt.setString(2, seller.getEmail());
      pstmt.setDate(3, Date.valueOf(seller.getBirthDate()));
      pstmt.setDouble(4, seller.getBaseSalary());
      pstmt.setInt(5, seller.getDepartment().getId());
      pstmt.setInt(6, seller.getId());

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Update de departamento foi um sucesso!");
      }

    } catch (SQLException e) {
      System.err.println("Erro ao fazer update de vendedor");
      e.printStackTrace();
    }
  }

  @Override
  public void deleteById(Integer id) {
    String sql = "DELETE FROM seller WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Vendedor deletado com sucesso");
      }

    } catch (SQLException e) {
      System.err.println("Erro ao deletar vendedor");
      e.printStackTrace();
    }
  }
}
