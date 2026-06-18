package database.entities;

import java.time.LocalDate;

public class Seller {
  private int id;
  private String name;
  private String email;
  private LocalDate birthDate;
  private double baseSalary;
  private Department department;

  public Seller(int id, String name, String email, LocalDate birthDate, double baseSalary, Department department) {
    setId(id);
    setName(name);
    setEmail(email);
    setBirthDate(birthDate);
    setBaseSalary(baseSalary);
    setDepartment(department);
  }

  public Seller() {

  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setBaseSalary(double baseSalary) {
    this.baseSalary = baseSalary;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Department getDepartment() {
    return department;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public double getBaseSalary() {
    return baseSalary;
  }
}
