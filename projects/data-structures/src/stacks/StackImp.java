package stacks;

public class StackImp implements Stack {

  private class Node {
    private URL url;
    private Node next = null;

    public Node(URL url) {
      this.url = url;
    }
  }

  private Node head = null;
  private int size = 0;

  @Override
  public void push(URL url) {
    Node node = new Node(url);
    node.next = head;
    head = node;
    size++;
  }

  @Override
  public void pop() {
    if (head == null)
      return;

    Node temp = head;
    head = head.next;
    temp.next = null;
    size--;
  }

  @Override
  public URL peek() {
    if (head == null)
      return null;

    return head.url;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    return size;
  }

}
