package scpproject.exceptions;

/**
 * Общее исключение для неверных учетных данных
 */
public abstract class WrongCredentialsException extends BaseException {

  public WrongCredentialsException() {
    super();
  }
}
