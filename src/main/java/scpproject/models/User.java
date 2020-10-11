package scpproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, описывающий таблицу user
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "public")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;

  private String password;

  private String nickname;

  @Column(name = "role_id")
  private Integer roleId;

  @Column(name = "status_id")
  private Integer statusId;

}
