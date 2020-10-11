package scpproject.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scpproject.commands.Command;

/**
 * Контроллер для работы с логином (и авторизацией)
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginController extends BaseController {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    Command command = getCommand(req, resp);
    command.execute();
  }
}
