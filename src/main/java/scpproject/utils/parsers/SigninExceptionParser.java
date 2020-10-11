package scpproject.utils.parsers;

import static java.util.Objects.nonNull;

import java.util.LinkedList;
import java.util.List;
import lombok.experimental.UtilityClass;
import scpproject.exceptions.BaseException;
import scpproject.objects.response.SignInResponse;

/**
 * Класс для обработки ошибок при попытках логина
 */
@UtilityClass
public class SigninExceptionParser {

  private List<String> errors;

  /**
   * Метод для обработки базовых исключений
   *
   * @param exception базовое исключение
   * @return объект ответа сервера
   */
  public SignInResponse generateResponseForBaseException(BaseException exception) {
    errors = getCurrentErrors();
    return exception.generateResponse(errors);
  }

  /**
   * Метод, получающий текущий стек трейс вызовов и возвращающий список ошибок
   *
   * @return список ошибок
   */
  private List<String> getCurrentErrors() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    if (!stackTrace[2].getClassName().equals("scpproject.utils.parsers.SigninExceptionParser")) {
      return new LinkedList<>();
    } else {
      return nonNull(errors) ? errors : new LinkedList<>();
    }
  }
}
