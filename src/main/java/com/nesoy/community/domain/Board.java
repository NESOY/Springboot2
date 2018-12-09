package com.nesoy.community.domain;

import com.nesoy.community.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable{

    @Id
    @Column
    // 기본 키가 자동으로 할당되도록 설정하는 Annotation, 키 생성을 데이터베이스에 위임하는 Identity 전략
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    // Enum 타입 Mapping Annotation :: Java Enum -> Database value로 변환(String)으로 저장
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    // 1:1 관계 Annotation :: 실제로 User 객체가 저장되는 것이 아니라 User의 PK user_idx값이 저장
    // fetch 전략
    // Lazy -> User 객체를 조회하는 시점이 아닌 객체가 실제로 사용될때 조회
    // Eager -> Board 도메인 조회할 때 즉시 관련 User 객체를 함께 조회
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }
}
