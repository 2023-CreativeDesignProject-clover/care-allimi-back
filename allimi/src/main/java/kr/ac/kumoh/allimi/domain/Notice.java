package kr.ac.kumoh.allimi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", referencedColumnName = "user_id")
    private NHResident target;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="nc_id")
    private NoticeContent content;

    public static Notice newNotice(Facility facility, User user, NHResident target, NoticeContent content) {
        Notice ntc = new Notice(null, facility, user, target, content);

        return ntc;
    }

    public void editNotice(NHResident target, String contents, String subContents) {
        this.target = target;
        this.content.editNoticeContent(contents, subContents);
    }
}
