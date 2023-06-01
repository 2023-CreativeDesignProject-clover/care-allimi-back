package kr.ac.kumoh.allimi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FacilityDTO {

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Add {
    @NotNull(message = "name이 널이어서는 안됩니다")
    private String name;

    private String address;
    private String tel;
    private String fm_name;
  }
  @Getter
  @Builder
  @AllArgsConstructor
  public static class Edit {
    @NotNull(message = "시설 id가 널이어서는 안됩니다")
    private Long facility_id;
    private String name;
    private String address;
    private String tel;
    private String fm_name;
  }
  @Getter
  @Builder
  public static class Info {
    private String name;
    private String address;
    private String tel;
    private String fm_name;
  }
}
