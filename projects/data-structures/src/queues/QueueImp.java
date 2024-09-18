package queues;

public class QueueImp implements Queue {

  private class Node {
    public Work work;
    public Node next;

    public Node(Work work) {
      this.work = work;
    }
  }

  private Node head, tail;

  @Override
  public void queue(Work work) {
    Node node = new Node(work);
    if (head == null) {
      head = node;
    } else {
      tail.next = node;
    }
    tail = node;
  }

  @Override
  public void delete() {
    if (head == null)
      return;

    Node temp = head;
    head = head.next;
    temp.next = null;

    if (head == null)
      tail = null;
  }

  @Override
  public Work peek() {
    if (head == null)
      return null;

    return head.work;
  }

}
