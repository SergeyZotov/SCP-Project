package scpproject.exceptions;

import java.util.List;
import scpproject.objects.response.SignInResponse;

/**
 * Базовый класс для всех исключений проекта
 */
public abstract class BaseException extends RuntimeException {

  protected List<String> errors;

  public BaseException(String message) {
    super(message);
  }

  public BaseException() {
    super();
  }

  /**
   * Метод, генерирующий объект {@link SignInResponse} для ответа сервера
   *
   * @param currentErrors текущие ошибки на момент вызова метода
   * @return ответ сервера в виде SignInResponse
   */
  public abstract SignInResponse generateResponse(List<String> currentErrors);
}
