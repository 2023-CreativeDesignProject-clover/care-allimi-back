package kr.ac.kumoh.allimi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class InvitationDTO {
  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Send {//facility_id, phone_num, userRole
    @NotNull(message = "user_id가 널이어서는 안됩니다")
    private Long user_id;

    @NotNull(message = "facility_id가 널이어서는 안됩니다")
    private Long facility_id;

    private String user_role;
  }

  @Getter
  public class Approve {//facility_id, phone_num, userRole
    @NotNull(message = "user_id가 널이어서는 안됩니다")
    private Long user_id;

    @NotNull(message = "invite_id가 널이어서는 안됩니다")
    private Long invite_id;
  }
}
