package com.example.shop.comment;



import com.example.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment")
    public String postComment(@RequestParam String comment, Authentication auth, @RequestParam Long parent) {
        commentService.postComment(comment, auth, parent);
        return "redirect:/detail/"+parent;
    }
}
