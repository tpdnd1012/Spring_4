package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.Reply.ReplyEntity;
import spring.domain.Reply.ReplyRepository;
import spring.web.dto.ReplyDto;

import java.util.ArrayList;
import java.util.List;

@Service // 해당 클래스는 mvc모델에서 service 사용
@RequiredArgsConstructor // 초기값이 없는 final 변수의 생성자 자동생성
public class ReplyService {

    // JPA 연결
    public final ReplyRepository replyRepository;
    // final : 수정불가 키워드 [ 상수만들기 ] => 고정값 정의

    // C : 리플 생성
    public void Replysave(ReplyDto replyDto ){

        replyRepository.save( replyDto.toEntity());
        // 엔티티 저장

    }

    // R : 리플 출력
    public List<ReplyDto> replyDtoList() {

        List<ReplyEntity> replyEntities =  replyRepository.findAll();     // 모든 엔티티 호출

        List<ReplyDto> replyDtos = new ArrayList<>();

        for( ReplyEntity entity : replyEntities ){   // 리스트내 모든 엔티티를 하나씩 대입
            // 엔티티 => dto
            ReplyDto replyDto = ReplyDto.builder()
                    .id(entity.getId())
                    .replycontents(entity.getReplycontents())
                    .replyname(entity.getReplyname())
                    .postid(entity.getPostid()).build();

            // dto 리스트 담기
            replyDtos.add( replyDto );
        }
        return replyDtos;
    }

    // U : 리플 업데이트

    // D : 리플 삭제


}
