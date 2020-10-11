package scpproject.utils.adapters;

import com.owlike.genson.JsonBindingException;
import javax.persistence.PersistenceException;
import scpproject.exceptions.BaseException;
import scpproject.exceptions.IncorrectRequestException;
import scpproject.exceptions.WrongBodyKeyException;
import scpproject.exceptions.WrongEmailException;
import scpproject.exceptions.WrongPasswordException;
import scpproject.exceptions.database.NoSuchUserException;

/**
 * Адаптер исключений ({@link RuntimeException}) под общее исключение {@link BaseException} на этом проекте
 */
public class ExceptionAdapter {

  private static final RuntimeException DEFAULT =
      new RuntimeException("Неизвестная ошибка. Обратитесь в техническую поддержку.");

  /**
   * Метод, адаптирующий исключение {@link JsonBindingException} под {@link BaseException}
   *
   * @param exception исключение типа JsonBindingException (Genson)
   * @return исключение типа BaseException
   */
  public BaseException adaptJsonBindingException(JsonBindingException exception) {
    return getBaseExceptionFromJsonBindingException(exception);
  }

  /**
   * Метод, адаптирующий исключение {@link PersistenceException} под {@link BaseException}
   *
   * @param exception исключение типа PersistenceException (javax.persistence)
   * @return исключение типа BaseException
   */
  public BaseException adaptPersistenceException(PersistenceException exception) {
    return getBaseExceptionFromPersistenceException(exception);
  }

  /**
   * Метод, адаптирующий исключение {@link JsonBindingException} под {@link BaseException}
   *
   * @param e исключение типа JsonBindingException (Genson)
   * @return исключение типа BaseException
   */
  private BaseException getBaseExceptionFromJsonBindingException(JsonBindingException e) {
    String message = e.getCause().getMessage();
    if (message.contains("Illegal character")
        || message.contains("Incomplete data or malformed json")) {
      return new IncorrectRequestException();
    } else if (message.contains("Could not mutate value of property named 'email'")) {
      return new WrongEmailException();
    } else if (message.contains("Could not mutate value of property named 'password'")) {
      return new WrongPasswordException();
    } else if (message.contains("No matching property in class scpproject.objects.request.SignInRequest for key")) {
      String key = message.substring(message.lastIndexOf("key")).substring(3);
      return new WrongBodyKeyException(key);
    } else {
      throw DEFAULT;
    }
  }

  /**
   * Метод, адаптирующий исключение {@link PersistenceException} под {@link BaseException}
   *
   * @param e исключение типа PersistenceException (javax.persistence)
   * @return исключение типа BaseException
   */
  private BaseException getBaseExceptionFromPersistenceException(PersistenceException e) {
    return switch (e.getClass().getSimpleName()) {
      case "NoResultException" -> new NoSuchUserException();
      default -> throw DEFAULT;
    };
  }
}
