package com.sparta.springtodoapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키 자동생성

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments; // 할일과 매핑(할일 : 댓글 -> 1:N 매핑)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자와 매핑(유저 : 할일 -> 1:N 매핑)

    @Column(nullable = false)
    private String title; // 할일 제목(필수입력)

    @Column(nullable = false)
    private String description; // 할일 내용(필수입력)

    @CreatedDate
    @Column(updatable = false) // 수정에 영향을 받지않는다
    private LocalDateTime createdAt; // 생성일자(자동생성)

    // 할일 등록 생성자
    public Todo(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
