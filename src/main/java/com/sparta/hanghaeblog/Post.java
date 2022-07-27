package com.sparta.hanghaeblog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String passward;

    @Column(nullable = false)
    private String content;

    public Post(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.name = postRequestDto.getName();
        this.passward = postRequestDto.getPassward();
        this.content = postRequestDto.getContent();
    }

    public void update(PostRequestDto dto){
        this.title = dto.getTitle();
        this.name = dto.getName();
        this.passward = dto.getPassward();
        this.content = dto.getContent();
    }
}
