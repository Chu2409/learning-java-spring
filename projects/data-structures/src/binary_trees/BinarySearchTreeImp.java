package binary_trees;

public class BinarySearchTreeImp implements BinarySearchTree<Employee> {

  private Employee value;
  private BinarySearchTreeImp left, right;
  private BinarySearchTreeImp parent;

  @Override
  public boolean isEmpty() {
    return this.value == null;
  }

  @Override
  public boolean isLeaf() {
    return this.value != null && this.left == null && this.right == null;
  }

  private void insertImpl(Employee emp, BinarySearchTreeImp parent) {
    if (this.isEmpty()) {
      this.value = emp;
      this.parent = parent;
      return;
    }

    if (emp.compareTo(this.value) < 0) {
      if (this.left == null)
        this.left = new BinarySearchTreeImp();

      this.left.insertImpl(emp, this);
    } else if (emp.compareTo(this.value) > 0) {
      if (this.right == null)
        this.right = new BinarySearchTreeImp();

      right.insertImpl(emp, this);
    }
  }

  @Override
  public void insert(Employee data) {
    this.insertImpl(data, null);
  }

  @Override
  public boolean exists(int id) {
    if (this.isEmpty())
      return false;

    if (id == this.value.getId())
      return true;

    if (id < this.value.getId() && this.left != null)
      return this.left.exists(id);

    if (id > this.value.getId() && this.right != null)
      return this.right.exists(id);

    return false;
  }

  @Override
  public Employee search(int id) {
    if (this.isEmpty())
      return null;

    if (id == this.value.getId())
      return this.value;

    if (id < this.value.getId() && this.left != null)
      return this.left.search(id);

    if (id > this.value.getId() && this.right != null)
      return this.right.search(id);

    return null;
  }

  private BinarySearchTreeImp min() {
    if (this.left == null)
      return this;

    return this.left.min();
  }

  private void deleteImpl(int id) {
    if (this.left != null && this.right != null) {
      BinarySearchTreeImp minRight = this.right.min();
      this.value = minRight.value;
      right.delete(minRight.value.getId());
    } else if (this.left != null || this.right != null) {
      BinarySearchTreeImp child = this.left != null ? this.left : this.right;
      this.value = child.value;
      this.left = child.left;
      this.right = child.right;
    } else {
      if (this.parent != null) {
        if (this == this.parent.left)
          this.parent.left = null;

        if (this == this.parent.right)
          this.parent.right = null;

        this.parent = null;
      }
      this.value = null;
    }
  }

  @Override
  public void delete(int id) {
    if (this.isEmpty())
      return;

    if (id == this.value.getId()) {
      this.deleteImpl(id);
    } else if (id < this.value.getId() && this.left != null) {
      this.left.delete(id);
    } else if (id > this.value.getId() && this.right != null) {
      this.right.delete(id);
    }
  }

  @Override
  public void printPreOrder() {
    if (this.isEmpty())
      return;

    System.out.println(this.value);

    if (this.left != null)
      this.left.printPreOrder();

    if (this.right != null)
      this.right.printPreOrder();
  }

  @Override
  public void printInOrder() {
    if (this.isEmpty())
      return;

    if (this.left != null)
      this.left.printInOrder();

    System.out.println(this.value);

    if (this.right != null)
      this.right.printInOrder();
  }

  @Override
  public void printPostOrder() {
    if (this.isEmpty())
      return;

    if (this.left != null)
      this.left.printPostOrder();

    if (this.right != null)
      this.right.printPostOrder();

    System.out.println(this.value);

  }

}
