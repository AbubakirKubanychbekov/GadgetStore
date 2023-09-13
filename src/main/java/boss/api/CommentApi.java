package boss.api;

import boss.dto.request.CommentRequest;
import boss.dto.response.CommentResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Tag(name = "CommentApi")
public class CommentApi {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponse> findAllComments(){
        return commentService.findAllComments();
    }

    @PostMapping("/{productId}")
    public CommentResponse save(@RequestBody CommentRequest commentRequest,
                                @PathVariable Long productId){
        return commentService.save(commentRequest,productId);
    }

    @GetMapping("/{id}")
    public CommentResponse findById(@PathVariable Long id){
        return commentService.findCommentById(id);
    }


//    @PutMapping("/{comment}")
//    public SimpleResponse updateComment(@RequestParam(name = "comment",required = false) String comment,
//                                        @RequestBody CommentRequest commentRequest){
//        return commentService.updateComment(comment,commentRequest);
//    }


    @PutMapping("/{id}")
    public SimpleResponse updateComment(@PathVariable Long id,
                                        @RequestBody CommentRequest commentRequest){
        return commentService.update(id,commentRequest);

    }


    @DeleteMapping("/{id}")
    public SimpleResponse deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

}
