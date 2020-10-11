package scpproject.exceptions;

import java.util.LinkedList;
import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Исключение для некорректного запроса
 */
public class IncorrectRequestException extends BaseException {

  @Override
  public SignInResponse generateResponse(List<String> currentErrors) {
    errors = new LinkedList<>(currentErrors);
    errors.add("Некорректные данные в реквесте");
    return SignInResponse.builder()
        .errors(errors)
        .message("Проверьте правильность заполненных полей")
        .status(400)
        .build();
  }
}
