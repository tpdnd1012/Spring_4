package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.service.ReplyService;
import spring.web.dto.ReplyDto;

@Controller // 해당 클래스는 mvc모델에서 controller 사용
@RequiredArgsConstructor
public class ReplyController {

    // 서비스 연결
    public final ReplyService replyService;

    @RequestMapping("/replywrite")
    public String replywrite(ReplyDto replyDto, RedirectAttributes re) {
                             // input에 name 과 dto 의 필드명과 동일하면 자동 주입

        replyService.Replysave( replyDto );

        re.addAttribute("id", replyDto.getPostid());

        re.addAttribute("count", -1);

        return "redirect:/postview";

    }

    @GetMapping("/replydelete")
    public String replydelete(RedirectAttributes re, @RequestParam("id") Long id, @RequestParam("postid") Long postid) {
        // URL 요청할때 매개변수 전달

        replyService.replydelete(id);

        re.addAttribute("id", postid);

        return "redirect:/postview";

    }

}
