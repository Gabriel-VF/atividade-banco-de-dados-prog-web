import database.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import database.daoimpl.*;
import database.entities.*;

public class App {
  public static void main(String[] args) throws Exception {
    ConnectionFactory.setUser("");
    ConnectionFactory.setPassword("");
    Connection connection = ConnectionFactory.getConnection();

    DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl(connection);






    //////////////////////////
    // TESTES PARA VENDEDOR //
    //////////////////////////





    // Testando findAll() para departamento
    System.out.println("\n> [Testando findAll() para departamento]");
    ArrayList<Department> departmentList = new ArrayList<>(departmentDAOImpl.findAll());
    for (int i = 0; i < departmentList.size(); i++) {
      System.out.printf("Departamento %s: %s\n", departmentList.get(i).getId(), departmentList.get(i).getName());
    }

    // Testando findById() para departamento
    System.out.println("\n> [Testando findById() para departamento]");
    Department dp1 = new Department();
    dp1 = departmentDAOImpl.findById(1);
    System.out.printf("Departamento %s: %s\n", dp1.getId(), dp1.getName());

    // Testando delete() para departamento não vai funcionar se vendedor é dependência de outro registro
    System.out.println("\n> [Testando delete() para departamento. Não irá funcionar se departamento é dependência de outro registro]");
    departmentDAOImpl.deleteById(13);

    // Testando insert() para departamento
    System.out.println("\n> [Testando insert() para departamento]");
    String dpNovoNome = "Novo departamento de número " + (departmentDAOImpl.findAll().size() + 1);
    System.out.println("\n> [Inserindo departamento: " + dpNovoNome + "]");
    Department dpInsert = new Department(0, dpNovoNome);
    departmentDAOImpl.insert(dpInsert);

    // Testando update() para departamento
    System.out.println("\n> [Testando update() para departamento]");
    System.out.printf("\n> [Fazendo update de departamento com id %s se existe]\n", departmentDAOImpl.findAll().size());
    Department departamentoNovo = new Department(departmentDAOImpl.findAll().size(), "Novo nome");
    departmentDAOImpl.update(departamentoNovo);




    ////////////////////////
    // TESTES PARA SELLER //
    ////////////////////////




    System.out.println("\n\n---------- [INICIANDO TESTES PARA VENDEDOR] ----------\n");
    SellerDAOImpl sellerDAOImpl = new SellerDAOImpl(connection);

    // Testando findAll() para vendedor
    System.out.println("\n> [Testando findAll() para vendedor]");
    ArrayList<Seller> sellerList = new ArrayList<>(sellerDAOImpl.findAll());
    for (int i = 0; i < sellerList.size(); i++) {
      System.out.printf("Vendedor %s: %s\n", sellerList.get(i).getId(), sellerList.get(i).getEmail());
    }

    // Testando findById() para vendedor
    System.out.println("\n> [Testando findById() para vendedor]");
    Seller s1 = new Seller();
    s1 = sellerDAOImpl.findById(1);
    System.out.printf("Vendedor %s: %s\n", s1.getId(), s1.getEmail());

    // Testando delete() para vendedor. Não irá funcionar se vendedor é dependência de outro registro
    System.out.println("\n> [Testando delete() para vendedor. Não irá funcionar se vendedor é dependência de outro registro]");
    sellerDAOImpl.deleteById(10);

    // Testando insert() para departamento
    System.out.println("\n> [Testando insert() para vendedor]");
    String emailInsert = "novo.seller." + (sellerDAOImpl.findAll().size() + 1) + "@gmail.com";
    System.out.println("\n> [Inserindo vendedor de email: " + emailInsert + "]");
    Seller sellerInsert = new Seller(0, "novo seller", emailInsert, LocalDate.now(), 1901.19, departmentDAOImpl.findById(1));
    sellerDAOImpl.insert(sellerInsert);

    // Testando update() para vendedor
    System.out.println("\n> [Testando update() para vendedor]");
    System.out.printf("\n> [Fazendo update de vendedor com id %s se existe]\n", sellerDAOImpl.findAll().size());
    Seller sellerUpdate = new Seller(sellerDAOImpl.findAll().size(), "seller com update", emailInsert, LocalDate.now(), 1635.9, departmentDAOImpl.findById(1));
    sellerDAOImpl.update(sellerUpdate);

    ConnectionFactory.getInstance().closeConnection();

  }
}
