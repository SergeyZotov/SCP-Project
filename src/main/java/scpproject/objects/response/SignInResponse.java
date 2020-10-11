package scpproject.objects.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Класс, описывающий ответ сервера на попытку залогиниться
 */
@Data
@Builder
public class SignInResponse {

  protected List<String> errors;
  protected String message;
  private Integer status;
}
