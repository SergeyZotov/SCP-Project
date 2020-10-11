package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для пустого тела запроса
 */
public class EmptyBodyException extends WrongCredentialsException {

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Пустое тело запроса");
    return SignInResponse.builder()
        .errors(errors)
        .message("Введите email и пароль")
        .status(400)
        .build();
  }
}
