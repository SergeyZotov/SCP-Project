package scpproject.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Класс для фильтрации запросов и ответов во время логина
 */
@WebFilter(urlPatterns = {"/login", "/*"})
public class LoginFilter extends BaseFilter {

  @Override
  public void init(FilterConfig filterConfig) {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    super.doFilter(request, response, chain);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }
}
