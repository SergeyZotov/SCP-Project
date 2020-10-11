package scpproject.exceptions.database;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для случая, когда пользователь не найден в БД
 */
public class NoSuchUserException extends DatabaseException {

  public NoSuchUserException() {
    super();
  }

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Незарегистрированный пользователь");
    return SignInResponse.builder()
        .errors(errors)
        .message("Проверьте корректность почты и пароля и повторите попытку")
        .status(200) // 204 код затирает всё тело ответа
        .build();
  }
}
