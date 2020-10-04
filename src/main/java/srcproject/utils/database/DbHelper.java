package srcproject.utils.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.experimental.FieldDefaults;
import org.postgresql.ds.PGSimpleDataSource;
import srcproject.utils.data.PropertyUtils;

/**
 * Класс для работы с PostgreSQL
 */
@FieldDefaults(makeFinal = true)
public class DbHelper {

    PropertyUtils utils = new PropertyUtils();

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static String PROPERTIES_PATH = "E:\\PET PROJECT\\SCP-Project\\src\\main\\resources\\properties\\postgresql.properties";
    static Connection connection;

    /**
     * Метод, возвращающий подключение к БД
     *
     * @return подключение к БД
     * @throws SQLException SQLException
     * @throws IOException  IOException
     */
    public Connection getConnection() throws SQLException, IOException {

        if (connection != null) {
            return switch (String.valueOf(connection.isClosed())) {
                case "false" -> connection;
                case "true" -> getConnectionInstance();
                default -> throw new SQLException("");
            };
        } else {
            return getConnectionInstance();
        }
    }

    /**
     * Метод, возвращающий сущность подключения к БД
     *
     * @return подключение к БД
     * @throws IOException  IOException
     * @throws SQLException SQLException
     */
    private Connection getConnectionInstance() throws IOException, SQLException {
        PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
        pgDataSource.setServerName(utils.getValue(PROPERTIES_PATH, "scp.sql.server.name"));
        pgDataSource.setDatabaseName(utils.getValue(PROPERTIES_PATH, "scp.sql.database.name"));
        pgDataSource.setUser(utils.getValue(PROPERTIES_PATH, "scp.sql.user"));
        pgDataSource.setPassword(utils.getValue(PROPERTIES_PATH, "scp.sql.password"));
        pgDataSource.setUrl(utils.getValue(PROPERTIES_PATH, "scp.sql.url"));

        return pgDataSource.getConnection();
    }
}
