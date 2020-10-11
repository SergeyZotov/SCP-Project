package scpproject.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * "Стартовый" контроллер для вывода текста
 */
@WebServlet(name = "app", urlPatterns = {"/cool", "", "/"})
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

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if ("".equals(req.getContextPath().replaceAll(PROJECT_API, ""))) {
      String path = "/login";
      ServletContext servletContext = getServletContext();
      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
      requestDispatcher.forward(req, resp);
    }
  }
}
