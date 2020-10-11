package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для неверного пароля
 */
public class WrongPasswordException extends WrongCredentialsException {

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Некорректный формат");
    return SignInResponse.builder()
        .errors(errors)
        .message("Некорректный формат пароля")
        .status(400)
        .build();
  }
}
