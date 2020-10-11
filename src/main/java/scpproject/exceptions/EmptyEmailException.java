package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для пустого email
 */
public class EmptyEmailException extends WrongCredentialsException {

  public EmptyEmailException() {
    super();
  }

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Пустое поле email");
    return SignInResponse.builder()
        .errors(errors)
        .message("Введите email")
        .status(400)
        .build();
  }
}
