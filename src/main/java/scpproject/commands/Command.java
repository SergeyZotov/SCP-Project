package scpproject.commands;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scpproject.utils.adapters.ExceptionAdapter;

/**
 * Общий класс для всех команд
 */
public abstract class Command {

  protected GensonBuilder builder;
  protected Genson genson;
  protected HttpServletRequest req;
  protected HttpServletResponse resp;
  protected static final transient ExceptionAdapter adapter = new ExceptionAdapter();

  Command(HttpServletRequest req, HttpServletResponse resp) {
    builder = new GensonBuilder();
    builder.failOnMissingProperty(true);
    builder.setSkipNull(true);
    genson = builder.create();
    this.req = req;
    this.resp = resp;
  }

  /**
   * Метод, выполняющий команду
   */
  public abstract void execute();
}
