package srcproject.utils.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static java.util.Objects.nonNull;

/**
 * Класс для работы с настройками (.properties)
 */
public class PropertyUtils {

    Properties properties;
    String path;

    public PropertyUtils() {
        properties = new Properties();
    }

    /**
     * Метод, возвращающий значение характеристики из переданного ключа
     * @param propertiesPath путь к файлу с характеристиками
     * @param propertyKey ключ
     * @return значение ключа
     * @throws IOException IOException
     */
    public String getValue(String propertiesPath, String propertyKey) throws IOException {
        if (!(nonNull(path) && Objects.equals(path, propertiesPath))) {
            properties.load(new BufferedReader(new FileReader(propertiesPath)));
            path = propertiesPath;
        }
        return properties.getProperty(propertyKey);
    }
}
