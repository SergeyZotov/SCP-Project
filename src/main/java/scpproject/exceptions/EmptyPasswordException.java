package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для пустого поля password
 */
public class EmptyPasswordException extends WrongCredentialsException {

  public EmptyPasswordException() {
    super();
  }

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Пустое поле password");
    return SignInResponse.builder()
        .errors(errors)
        .message("Введите пароль")
        .status(400)
        .build();
  }
}
