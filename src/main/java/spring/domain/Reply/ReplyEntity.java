package spring.domain.Reply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BasTime;

import javax.persistence.*;

@Getter // get 메소드 자동생성
@NoArgsConstructor // 빈생성자 자동생성
@Entity // 해당 클래스는 엔티티로 사용 가능
public class ReplyEntity extends BasTime {

    @Id // 기본키 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동번호 부여
    Long id;

    @Column // 필드
    String replycontents;

    @Column // 필드
    String replyname;

    @Column // 필드
    Long postid;

    // 생성자
    @Builder // 롬북제공 : 생성자 사용시 안정성 보장
    public ReplyEntity(Long id, String replycontents, String replyname, Long postid) {
        this.id = id;
        this.replycontents = replycontents;
        this.replyname = replyname;
        this.postid = postid;
    }

    // 댓글 내용 업데이트
    public void update( String replycontents ){
        this.replycontents = replycontents;
    }



}
