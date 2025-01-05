package com.example.shop.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@ToString
@Setter
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String username;
    @Column(length = 1000)
    private String content;

    private Long parentId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
