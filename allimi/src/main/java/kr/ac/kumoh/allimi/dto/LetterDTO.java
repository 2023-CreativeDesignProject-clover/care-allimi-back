package kr.ac.kumoh.allimi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class LetterDTO {

  @Getter
  public static class Edit {
    @NotNull(message = "letter_id가 널이어서는 안됩니다")
    private Long letter_id;

    @NotNull(message = "writer_id가 널이어서는 안됩니다")
    private Long writer_id; //nhr

    private String contents;
  }

  @Getter
  @Builder
  public static class List {
    @NotNull(message = "letter_id가 널이어서는 안됩니다")
    private Long letter_id;

    @NotNull(message = "nhresident_id가 널이어서는 안됩니다")
    private Long nhresident_id;

    private String user_name;
    private String nhr_name;
    private String content;
    private Boolean is_read;
    private LocalDateTime created_date;
  }

  @Getter
  public static class Write {
    @NotNull(message = "nhresident_id가 널이어서는 안됩니다")
    private Long nhresident_id;

    private String contents;
  }



}
