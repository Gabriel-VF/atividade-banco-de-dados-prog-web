package database.dao;

import java.util.List;
import database.entities.Seller;

public interface SellerDAO {
  void insert(Seller department);

  void update(Seller department);

  void deleteById(Integer id);

  Seller findById(Integer id);

  List<Seller> findAll();
}
