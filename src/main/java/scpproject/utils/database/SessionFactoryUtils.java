package scpproject.utils.database;

import static java.util.Objects.nonNull;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import scpproject.models.Achievement;
import scpproject.models.Role;
import scpproject.models.Status;
import scpproject.models.User;
import scpproject.models.UserAchievement;

/**
 * Класс для работы с подключением к БД
 */
@UtilityClass
public class SessionFactoryUtils {

  private static SessionFactory sessionFactory;

  public SessionFactory getSessionFactory() {
    try {
      if (!nonNull(sessionFactory)) {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserAchievement.class);
        configuration.addAnnotatedClass(Status.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Achievement.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return sessionFactory;
  }
}
