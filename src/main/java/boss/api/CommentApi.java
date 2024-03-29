package boss.api;

import boss.dto.request.CommentRequest;
import boss.dto.response.CommentResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Tag(name = "CommentApi")
public class CommentApi {

    private final CommentService commentService;

    @PermitAll
    @GetMapping
    @Operation(summary = "Get All",description = "Get All comments info")
    public List<CommentResponse> findAllComments() {
        return commentService.findAllComments();
    }

    @PermitAll
    @PostMapping("/{productId}")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public CommentResponse save(@RequestBody CommentRequest commentRequest,
                                @PathVariable Long productId) {
        return commentService.save(commentRequest, productId);
    }

    @PermitAll
    @GetMapping("/{id}")
    @Operation(summary = "Get comment by ID",description = "To get by ID fill ID!")
    public CommentResponse findById(@PathVariable Long id) {
        return commentService.findCommentById(id);
    }


//    @PutMapping("/{comment}")
//    public SimpleResponse updateComment(@RequestParam(name = "comment",required = false) String comment,
//                                        @RequestBody CommentRequest commentRequest){
//        return commentService.updateComment(comment,commentRequest);
//    }


    @Secured({"ADMIN","USER"})
    @PutMapping("/{id}")
    @Operation(summary = "Update",description = "To update fill all the fields!")
    public SimpleResponse updateComment(@PathVariable Long id,
                                        @RequestBody CommentRequest commentRequest) {
        return commentService.update(id, commentRequest);

    }


    @Secured({"ADMIN","USER"})
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted",description = "To deleted fill all the fields!")
    public SimpleResponse deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

}
