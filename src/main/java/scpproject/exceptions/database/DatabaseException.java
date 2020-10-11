package scpproject.exceptions.database;

import scpproject.exceptions.BaseException;

/**
 * Базовое исключение при работе с БД
 */
public abstract class DatabaseException extends BaseException {

  public DatabaseException() {
    super();
  }
}
