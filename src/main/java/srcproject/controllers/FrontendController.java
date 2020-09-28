package srcproject.controllers;

import srcproject.commands.Command;
import srcproject.commands.UpdateCommand;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Паттерн FrontController, работает с паттерном Команда
 */
public class FrontendController extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Command command = getCommand(req);
        command.execute();
    }

    private Command getCommand(HttpServletRequest req) {
        if ("update".equals(req.getHeader("command"))) {
            return new UpdateCommand();
        } else {
            throw new UnsupportedOperationException("Неподдерживаемая команда");
        }
    }
}
