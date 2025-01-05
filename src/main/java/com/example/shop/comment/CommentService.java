package com.example.shop.comment;

import com.example.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void postComment(String comment, Authentication auth, Long parent) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        var data = new Comment();
        data.setContent(comment);
        data.setUsername(user.getUsername());
        data.setParentId(parent);
        commentRepository.save(data);
    }
}
