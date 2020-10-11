package scpproject.utils.database;

import static java.util.Objects.nonNull;

import javax.persistence.Query;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import scpproject.models.User;

/**
 * Класс для работы с PostgreSQL через Hibernate
 */
@UtilityClass
public class DbHelper {

  private final SessionFactory sessionFactory = SessionFactoryUtils.getSessionFactory();

  public User selectFromUser(@NonNull String sql, String email, String password, String nickname,
      Integer roleId, Integer statusId) {
    Query query = sessionFactory.openSession().createQuery(sql);
    if (nonNull(email)) {
      query.setParameter("mail", email);
    }
    if (nonNull(password)) {
      query.setParameter("pass", password);
    }
    if (nonNull(nickname)) {
      query.setParameter("nick", nickname);
    }
    if (nonNull(roleId)) {
      query.setParameter("role", roleId);
    }
    if (nonNull(statusId)) {
      query.setParameter("status", statusId);
    }
    return (User) query.getSingleResult();
  }
}
