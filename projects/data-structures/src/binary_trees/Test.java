package binary_trees;

import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    Employee e1 = new Employee(20, "John");
    Employee e2 = new Employee(44, "Jane");
    Employee e3 = new Employee(18, "Doe");
    Employee e4 = new Employee(33, "Smith");
    Employee e5 = new Employee(64, "Eva");
    Employee e6 = new Employee(55, "Adam");

    BinarySearchTreeImp bst = new BinarySearchTreeImp();
    Arrays.asList(e1, e2, e3, e4, e5, e6).forEach(bst::insert);

    bst.printPreOrder();
  }

}
