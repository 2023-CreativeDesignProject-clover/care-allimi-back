package kr.ac.kumoh.allimi.dto;

import jakarta.validation.constraints.NotNull;
import kr.ac.kumoh.allimi.domain.UserRole;
import lombok.Getter;

public class NHResidentDTO {

  @Getter
  public class Add {
    @NotNull(message = "user_id가 널이어서는 안됩니다")
    private Long user_id;

    @NotNull(message = "facility_id가 널이어서는 안됩니다")
    private Long facility_id;

    private String resident_name;

    private String birth;

    @NotNull(message = "역할이 널이어서는 안됩니다")
    private UserRole user_role;

    private String health_info;
  }

  @Getter
  public static class Edit {
    @NotNull(message = "resident_id가 널이어서는 안됩니다")
    private Long resident_id;

    private String resident_name;
    private String birth;
    private String health_info;
  }

  @Getter
  public class UF {
    @NotNull(message = "resdient_id가 널이어서는 안됩니다")
    private Long resdient_id;

    @NotNull(message = "worker_id가 널이어서는 안됩니다")
    private Long worker_id;

    @NotNull(message = "facility_id가 널이어서는 안됩니다")
    private Long facility_id;
  }
}
