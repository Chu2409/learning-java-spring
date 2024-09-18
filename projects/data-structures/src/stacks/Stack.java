package stacks;

public interface Stack {
  void push(URL url);

  void pop();

  URL peek();

  boolean isEmpty();

  int size();
}
