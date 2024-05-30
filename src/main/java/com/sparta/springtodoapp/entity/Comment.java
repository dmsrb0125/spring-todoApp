package com.sparta.springtodoapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키 자동생성

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    @JsonBackReference
    private Todo todo; // 할일과 매핑(할일 : 댓글 -> 1:N 매핑)

    @Column(nullable = false)
    private Long userId; // 사용자 ID

    @Column(nullable = false)
    private String content; // 댓글내용

    @CreatedDate
    @Column(updatable = false) // 수정에 영향을 받지않는다
    private LocalDateTime createdAt; // 생성일자(자동생성)

    // 댓글 등록 생성자
    public Comment(Todo todo, String content, Long userId) {
        this.todo = todo;
        this.content = content;
        this.userId = userId;
    }

}