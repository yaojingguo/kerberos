import javax.security.auth.login.LoginContext;
import javax.security.auth.Subject;

public class Login {
  public static void main(String[] args) throws Exception {
    LoginContext lc = new LoginContext("unix");
    Subject sub = lc.getSubject();
    lc.login();
    System.out.println("Subject: " + sub);  
  }
}
