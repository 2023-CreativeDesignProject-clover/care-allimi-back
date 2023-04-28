package kr.ac.kumoh.allimi.domain.func;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.ac.kumoh.allimi.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Notice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notice_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @NotNull
  @JoinColumn(name = "nhr_id", referencedColumnName = "resident_id")
  @ManyToOne
  private NHResident nhResident;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "facility_id")
  private Facility facility;

  @Column(name = "create_date")
  private LocalDateTime createDate = LocalDateTime.now();

  @Lob
  @Column(length = 5000)
  private String title;

  @Lob
  @Column(length = 100000)
  private String contents;

  @Lob
  @Column(name = "sub_contents", length = 100000)
  private String subContents;

  @OneToMany(mappedBy = "imageId")
  @Column(name = "image_id")
  private List<Image> images = new ArrayList<>();

  public static Notice newNotice(@NotNull User user, @NotNull NHResident target, @NotNull Facility facility,
                                 String title, String contents, String subContents, List<Image> images) {
    Notice ntc = Notice.builder()
            .user(user)
            .nhResident(target)
            .facility(facility)
            .title(title)
            .contents(contents)
            .subContents(subContents)
            .images(images)
            .build();

      return ntc;
  }

  public void setNhResident(NHResident nhResident) {
  this.nhResident = nhResident;
  }

  public void addImages(List<Image> images) {
      this.images = images;
  }

  //    public void editNotice(NHResident target, String contents, String subContents, String[] imageUrls) {
  //        setNhResident(target);
  //        this.contents = contents;
  //        this.subContents = subContents;
  //
  //        this.image = null;
  //        for(String url: imageUrls) {
  //            Image i = Image.newNoticeImage(this, url);
  //            this.image.add(i);
  //        }
  //    }
}
