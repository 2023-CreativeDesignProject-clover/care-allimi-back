package kr.ac.kumoh.allimi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class ScheduleDTO {
  @Getter
  public static class Write {
    @NotNull(message = "writer_id가 널이어서는 안됩니다")
    private Long writer_id; //nhr

    @NotNull(message = "date가 널이어서는 안됩니다")
    private LocalDate date;

    @NotNull(message = "texts가 널이어서는 안됩니다")
    private String texts;
  }

  @Getter
  @Builder
  public static class List {
    @NotNull(message = "schedule_id가 널이어서는 안됩니다")
    private Long schedule_id;

    private LocalDate date;
    private String texts;
  }

  @Getter
  public static class Edit {
    @NotNull(message = "schedule_id가 널이어서는 안됩니다")
    private Long schedule_id;

    @NotNull(message = "date가 널이어서는 안됩니다")
    private LocalDate date;

    @NotNull(message = "texts가 널이어서는 안됩니다")
    private String texts;
  }

  @Getter
  public static class Delete {
    @NotNull(message = "schedule_id가 널이어서는 안됩니다")
    private Long schedule_id;

    @NotNull(message = "nhr_id가 널이어서는 안됩니다")
    private Long nhr_id;
  }


}
