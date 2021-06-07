package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.domain.post.PostEntity;
import spring.service.PostService;
import spring.service.ReplyService;
import spring.web.dto.PostDto;
import spring.web.dto.ReplyDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ReplyService replyService;

    // 1. 게시판 페이지 요청
    @GetMapping("/postlist")
    public String postlist(Model model , @PageableDefault Pageable pageable , HttpServletRequest request ){  // 모델 : 프론트에게 데이터 전달


        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        Page<PostEntity> postEntities = postService.postlist( pageable , keyword , search  );

        model.addAttribute( "postDtos" , postEntities );

        return "postlist";

//        List<PostDto> postDtos = postService.postlist();
//        model.addAttribute( "postDtos" , postDtos); // 모델 이름 , 모델에 넣을 변수/객체
//        return "postlist";

    }

    // 2. 게시물 등록 페이지 요청
    @GetMapping("/postwrite")
    public String postwrite() {

        return "postwrite"; // 타임리프 HTML 요청

    }

    // 3. 게시물 등록 처리
    @PostMapping("/postwrite")
    public String postwrite_c( PostDto postDto ) {

        postService.postsave( postDto );

        return "redirect:/postlist"; // URL 요청

    }

    // 4. 게시물 상세페이지 요청
    @RequestMapping(value = "/postview", method = RequestMethod.GET)
    public String postview(@RequestParam("id") Long id, Model model ) {
                            // 경로(url) 상에 변수 가져오기
        // 조회수 처리
        postService.countup(id);

        // 해당 게시물 출력
        PostDto postDto = postService.postget( id );
        model.addAttribute( "postDto" , postDto);

        // 댓글 출력하기
        List<ReplyDto> replyDtos =  replyService.replyDtoList(postDto.getId());
        model.addAttribute("replyDtos" , replyDtos);
        // model : html에 데이터 전달 ( 모델명 , 데이터 )

        return "postview";

    }

    // 5. 게시물 수정 페이지 요청
    @GetMapping("/postupdate/{id}")
    public String postupdate(@PathVariable Long id , Model model ) {
                                // 경로(url) 상에 변수 가져오기

        PostDto postDto =  postService.postget(id);
        model.addAttribute("postDto" , postDto);

        return "postupdate";

    }

    // 6. 게시물 수정 처리
    @PostMapping("/postupdate")
    public String postupdate_c( PostDto updatedto ) {

        postService.postupdate(updatedto);
        return "redirect:/postlist"; // URL 요청

    }

    // 7. 게시물 삭제 처리
    @GetMapping("/postdelete/{id}")
    public String postdelete( @PathVariable Long id ) {

        postService.postdelete(id);

        return "redirect:/postlist"; // URL 요청

    }

    // 8. 검색 처리
    @PostMapping("/postsearch")
    public String postsearch_c(HttpServletRequest request , Model model , @PageableDefault Pageable pageable) {
                                 // jsp 와 다르게 request 객체 만들기

        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");

        //검색이 없으면
        if( search.equals("") ) return "redirect:/postlist";
        //        System.out.println(keyword); // request 확인
        //        System.out.println(search); // request 확인

        Page<PostEntity> postEntities = postService.postlist( pageable , keyword , search );

        model.addAttribute( "postDtos" , postEntities );

        return "postlist";

    }

}
