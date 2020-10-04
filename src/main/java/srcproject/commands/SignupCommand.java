package srcproject.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс-команда для регистрации нового пользователя
 */
public class SignupCommand extends Command {

    public SignupCommand(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void execute() throws Exception {
        if (!isUserInDatabase()) {
            Connection connection = getConnection();
            String sql = """
                    INSERT INTO public.user (login, password, nickname, role_id)
                    values (?, ?, ?, 2, 1)
                    """;
            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, req.getHeader("login"));
                statement.setString(2, req.getHeader("password"));
                statement.setString(3, req.getHeader("nick"));

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
        } else {
            resp.setStatus(418);
            resp.getWriter().print("User is already in database. Please, use your login and password to sign in.");
        }
    }

    /**
     * TODO incorrect expression
     * Метод, проверяющий наличие пользователя в БД
     *
     * @return true, если пользователь есть в БД, иначе false
     * @throws IOException  IOException
     * @throws SQLException SQKException
     */
    private boolean isUserInDatabase() throws IOException, SQLException {
        Connection connection = getConnection();
        String sql = """
                SELECT login, password 
                FROM public.user 
                WHERE login = ? AND password = ?
                """;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, req.getHeader("login"));
            statement.setString(2, req.getHeader("password"));

            try (ResultSet set = statement.executeQuery()) {
                return set.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
