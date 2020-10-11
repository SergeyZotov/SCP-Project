package scpproject.commands;

import static java.util.Objects.nonNull;

import com.owlike.genson.JsonBindingException;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scpproject.exceptions.BaseException;
import scpproject.exceptions.EmptyBodyException;
import scpproject.exceptions.EmptyEmailException;
import scpproject.exceptions.EmptyPasswordException;
import scpproject.objects.request.SignInRequest;
import scpproject.objects.response.SignInResponse;
import scpproject.utils.database.DbHelper;
import scpproject.utils.parsers.SigninExceptionParser;

/**
 * Класс-команда для логина(входа)
 */
public class SigninCommand extends Command {

  public SigninCommand(HttpServletRequest req, HttpServletResponse resp) {
    super(req, resp);
  }

  @Override
  public void execute() {
    try {
      SignInRequest request;
      SignInResponse response;
      SignInResponse.SignInResponseBuilder builder = SignInResponse.builder();
      try {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        request = genson.deserialize(body, SignInRequest.class);
        if (!nonNull(request) || !nonNull(request.getEmail()) && (!nonNull(request.getPassword()))) {
          throw new EmptyBodyException();
        } else if (!nonNull(request.getEmail())) {
          throw new EmptyEmailException();
        } else if (!nonNull(request.getPassword())) {
          throw new EmptyPasswordException();
        }
        String sql = "FROM User WHERE email = :mail AND password = :pass";
        DbHelper.selectFromUser(sql, request.getEmail(), request.getPassword(), null, null, null);
        builder.message("Успешный вход в систему").build();
        builder.status(200);
        response = builder.build();
      } catch (BaseException e) {
        response = SigninExceptionParser.generateResponseForBaseException(e);
      } catch (JsonBindingException e) {
        response = SigninExceptionParser.generateResponseForBaseException(adapter.adaptJsonBindingException(e));
      } catch (PersistenceException e) {
        response = SigninExceptionParser.generateResponseForBaseException(adapter.adaptPersistenceException(e));
      }
      resp.setStatus(response.getStatus());
      resp.getWriter().print(genson.serialize(response));
    } catch (IOException ignored) {
    }
  }
}
