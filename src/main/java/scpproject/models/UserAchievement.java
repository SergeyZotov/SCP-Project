package scpproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Класс, описывающий таблицу user_achievement
 */
@Entity
@Data
@Table(name = "user_achievement", schema = "public")
public class UserAchievement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "achievement_id")
  private Integer achievementId;
}
