package srcproject.commands;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import srcproject.utils.database.DbHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Общий класс для всех команд
 */
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class Command {

    HttpServletRequest req;
    HttpServletResponse resp;
    static DbHelper helper = new DbHelper();

    Command(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public abstract void execute() throws Exception;

    protected Connection getConnection() throws IOException, SQLException {
        return helper.getConnection();
    }
}
