package srcproject.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@WebServlet("/cool")
public class App extends HttpServlet {

    public String getGreeting() {
        return "cool";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Locale.setDefault(new Locale("ru"));
        PrintWriter out = resp.getWriter();
        out.println(this.getGreeting());
        out.close();
    }
}
