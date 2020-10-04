package srcproject.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * "Стартовый" контроллер для вывода текста
 */
@WebServlet(name = "app", urlPatterns = "/cool")
public class App extends BaseController {

    public String getGreeting() {
        return "cool";
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        out.println(this.getGreeting());
        out.close();
    }
}
