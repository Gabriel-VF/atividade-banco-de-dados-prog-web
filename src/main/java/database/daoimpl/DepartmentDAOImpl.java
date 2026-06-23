package database.daoimpl;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import database.entities.Department;
import database.dao.DepartmentDAO;

public class DepartmentDAOImpl implements DepartmentDAO {
  private Connection conn;

  public DepartmentDAOImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public List<Department> findAll() {
    List<Department> departments = new ArrayList<>();

    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM department")) {

      while (rs.next()) {
        Department department = new Department();
        department.setId(rs.getInt("id"));
        department.setName(rs.getString("name"));

        departments.add(department);
      }
    } catch (SQLException e) {
      System.err.println("Erro ao listar departamentos:");
      e.printStackTrace();
    }

    return departments;
  }

  @Override
  public Department findById(Integer id) {
    String sql = "SELECT * FROM department WHERE id = ?";
    Department department = new Department();
    department.setId(id);

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        department.setName(rs.getString("name"));
      }
    } catch (SQLException e) {
      System.out.println("Erro ao tentar pesquisar departamento por id");
      e.printStackTrace();
    }

    return department;
  }

  @Override
  public void insert(Department department) {

    try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department (name) VALUES (?)")) {
      pstmt.setString(1, department.getName());

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Departamento inserido com sucesso!");
      }
    } catch (SQLException e) {
      System.err.println("Erro ao inserir departamento:");
      e.printStackTrace();
    }

  }

  @Override
  public void update(Department department) {
    String sql = "UPDATE department SET name = ? WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, department.getName());
      pstmt.setInt(2, department.getId());

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Update de departamento foi um sucesso!");
      }

    } catch (SQLException e) {
      System.err.println("Erro ao fazer update de departamento");
      e.printStackTrace();
    }
  }

  @Override
  public void deleteById(Integer id) {
    String sql = "DELETE FROM department WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      int affectedRows = pstmt.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("Departamento deletado com sucesso");
      }

    } catch (SQLException e) {
      System.err.println("Erro ao deletar departamento");
      e.printStackTrace();
    }
  }
}
