package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.Reply.ReplyEntity;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class ReplyDto {

    Long id;
    String replycontents;
    String replyname;
    Long postid;

    @Builder
    public ReplyDto(Long id, String replycontents, String replyname, Long postid) {
        this.id = id;
        this.replycontents = replycontents;
        this.replyname = replyname;
        this.postid = postid;
    }

    // Dto ----> entity :  DTO는 DB로 접근불가 : DTO를 entity 변경후 접근
    public ReplyEntity toEntity(){
        return ReplyEntity.builder()
                .id(id)
                .replycontents(replycontents)
                .replyname(replyname)
                .postid(postid).build();
    }
}
