package stacks;

public class Test {
  public static void main(String[] args) {
    URL google = new URL("https://www.google.com");
    URL facebook = new URL("https://www.facebook.com");

    Stack stack = new StackImp();
    printStack(stack);

    stack.push(google);
    printStack(stack);

    stack.push(facebook);
    printStack(stack);

    while (!stack.isEmpty()) {
      stack.pop();
      printStack(stack);
    }

  }

  private static void printStack(Stack stack) {
    if (stack.isEmpty()) {
      System.out.println("Stack is empty");
      return;
    }

    System.out.println("Stack size: " + stack.size() + ", top: " + stack.peek().getUrl());
  }
}
