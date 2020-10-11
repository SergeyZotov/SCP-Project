package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для некорректного поля в теле запроса
 */
public class WrongBodyKeyException extends BaseException {

  public WrongBodyKeyException(String message) {
    super(message);
  }

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Некорректные данные в реквесте");
    return SignInResponse.builder()
        .errors(errors)
        .message("Введено несуществующее поле" + getMessage())
        .status(400)
        .build();
  }
}
