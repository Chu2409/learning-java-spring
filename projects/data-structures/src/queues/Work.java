package queues;

public class Work {
  private String title;
  private String author;
  private String printed;

  public Work(String title, String author, String printed) {
    this.title = title;
    this.author = author;
    this.printed = printed;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPrinted() {
    return printed;
  }

  public void setPrinted(String printed) {
    this.printed = printed;
  }

}
