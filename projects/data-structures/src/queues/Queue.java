package queues;

public interface Queue {
  void queue(Work work);

  void delete();

  Work peek();
}
