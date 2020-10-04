package srcproject.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Класс-команда для логина(входа)
 */
public class SigninCommand extends Command {

    public SigninCommand(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void execute() throws SQLException, IOException, ServletException {
        Connection connection = getConnection();
        String sql = "SELECT login, password, status_id FROM public.user WHERE login = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, req.getHeader("login"));
            statement.setString(2, req.getHeader("password"));

            try (ResultSet set = statement.executeQuery(); PrintWriter writer = resp.getWriter();) {

                if (set.next()) {
                    writer.println(set.getString(1));
                    writer.println(set.getString(2));
                } else {
                    writer.println("User is not in database!");
                }
            }
        } catch (SQLException | IOException ignored) {

        }
    }
}
