package scpproject.objects.request;

import java.util.regex.Pattern;
import lombok.Getter;
import scpproject.exceptions.WrongEmailException;

/**
 * Класс, описывающий запрос на сервер во время логина
 */
@Getter
public class SignInRequest {

  private static final Pattern EMAIL = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}"
      + "~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\"
      + "x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4]"
      + "[0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:"
      + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

  private String email;
  private String password;

  public void setEmail(String email) {
    if (EMAIL.matcher(email).matches()) {
      this.email = email;
    } else {
      throw new WrongEmailException();
    }
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
