package binary_trees;

@SuppressWarnings("rawtypes")
public interface BinarySearchTree<T extends Comparable> {
  boolean isEmpty();

  boolean isLeaf();

  void insert(T data);

  boolean exists(int id);

  T search(int id);

  void delete(int id);

  void printPreOrder();

  void printInOrder();

  void printPostOrder();
}
