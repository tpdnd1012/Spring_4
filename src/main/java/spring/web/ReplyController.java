package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import spring.service.ReplyService;
import spring.web.dto.ReplyDto;

@Controller // 해당 클래스는 mvc모델에서 controller 사용
@RequiredArgsConstructor
public class ReplyController {

    // 서비스 연결
    public final ReplyService replyService;

    @PostMapping("/replywrite")
    public String replywrite(ReplyDto replyDto) {

        // input에 name 과 dto 의 필드명과 동일하면 자동 주입
        replyService.Replysave( replyDto );

        return "index";

    }

}
