import java.security.*;

public class Callee {
  private static void withDoPrivileged() {
    AccessController.doPrivileged(new PrivilegedAction() {
      @Override
      public Object run() {
        withoutDoPrivileged();
        return null;
      }
    });
  }

  private static void withoutDoPrivileged() {
    System.out.println("Your java.home property: "
        + System.getProperty("java.home"));
  }

  public static void main(String[] args) {
    withDoPrivileged();
    withoutDoPrivileged();
  }
}
