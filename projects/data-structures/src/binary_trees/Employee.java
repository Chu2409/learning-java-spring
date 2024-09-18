package binary_trees;

public class Employee implements Comparable<Employee> {
  private int id;
  private String name;

  public Employee(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public int compareTo(Employee emp) {
    if (id == emp.id)
      return 0;
    if (id < emp.id)
      return -1;
    return 1;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + "]";
  }

}
