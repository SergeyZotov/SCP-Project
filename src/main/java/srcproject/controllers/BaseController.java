package srcproject.controllers;

import srcproject.commands.Command;
import srcproject.commands.SigninCommand;
import srcproject.commands.SignupCommand;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Базовый контроллер с общей для всех контроллеров логикой
 */
public abstract class BaseController extends HttpServlet {

    /**
     * Метод, возвращающий команду в зависимости от переданной команды в заголовке запроса
     *
     * @param req запрос
     * @param resp ответ
     * @return объект типа {@link Command}
     *
     */
    protected Command getCommand(HttpServletRequest req, HttpServletResponse resp) {
        return switch (req.getHeader("command")) {
            case "login" -> new SigninCommand(req, resp);
            case "signup" -> new SignupCommand(req, resp);
            default -> throw new UnsupportedOperationException("Неподдерживаемая команда");
        };
    }

    @Override
    public void destroy() {
        super.destroy();
        // TODO закрывать подключения к БД
    }
}
