package srcproject.controllers;

import srcproject.commands.Command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "login", urlPatterns = {"/login", "/authorization"})
public class LoginController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        Command command = getCommand(req, resp);
        try {
            command.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
