package linked_lists;

public class List {
  private Node head = null;
  private int size = 0;

  private class Node {
    public Book book;
    public Node next = null;

    public Node(Book book) {
      this.book = book;
    }
  }

  public void addFirst(Book book) {
    Node node = new Node(book);
    node.next = head;
    head = node;
    size++;
  }

  public void addLast(Book book) {
    Node node = new Node(book);

    if (head == null) {
      head = node;
    } else {
      Node current = head;

      while (current.next != null) {
        current = current.next;
      }

      current.next = node;
    }

    size++;
  }

  public void addAt(int n, Book book) {
    Node node = new Node(book);

    if (head == null) {
      head = node;
    } else {
      Node current = head;
      int counter = 0;

      while (current.next != null && counter < n) {
        current = current.next;
        counter++;
      }

      node.next = current.next;
      current.next = node;
    }

    size++;
  }

  public Book get(int n) {
    if (head == null)
      return null;

    Node current = head;
    int counter = 0;

    while (current.next != null && counter < n) {
      current = current.next;
      counter++;
    }

    if (counter != n)
      return null;

    return current.book;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void removeFirst() {
    if (isEmpty())
      return;

    Node temp = head;
    head = head.next;

    temp.next = null;
    size--;

  }

  public void removeLast() {
    if (!isEmpty())
      return;

    if (head.next == null) {
      head = null;
    } else {
      Node current = head;
      while (current.next.next != null) {
        current = current.next;
      }

      current.next = null;
    }

    size--;
  }

  public void removeAt(int n) {
    if (isEmpty() || n > size || n < 0)
      return;

    if (n == 0) {
      removeFirst();
      return;
    }

    Node current = head;
    int counter = 0;
    while (counter < (n - 1)) {
      current = current.next;
      counter++;
    }
    Node temp = current.next;

    current.next = temp.next;
    temp.next = null;

    size--;
  }
}
