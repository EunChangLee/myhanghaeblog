package com.sparta.hanghaeblog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    //전체 게시글 목록 조회
    @GetMapping("/api/posts")
    public String[][] readAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        String[][] post = new String[postList.size()][3];
        for(int i=0; i<postList.size(); i++){
            post[i][0] = postList.get(i).getTitle();
            post[i][1] = postList.get(i).getName();
            post[i][2] = postList.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
        }
        return post;
    }

    //게시글 쓰기
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto){
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    //게시글 조회
    @GetMapping("/api/posts/{id}")
    public Post readPost(@PathVariable Long id){
        return postRepository.findById(id).get();
    }

    //게시글 비밀번호 확인
//    @GetMapping("/api/posts{id}")
//    public String checkPassward(@PathVariable Long id, @RequestParam("pw") int passward){
//        Post post = postRepository.findById(id).get();
//        int pw = post.getPassward();
//        if(pw != passward){
//            return "비밀번호가 틀렸습니다.";
//        }
//
//        return "비밀번호가 일치합니다.";
//    }

    //게시글 비밀번호 확인
    @PostMapping("/api/posts/{id}")
    public String checkPassward(@PathVariable Long id, @RequestBody PostRequestDto dto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String pw = post.getPassward();
        if(!Objects.equals(pw, dto.getPassward())){
            return "비밀번호가 틀렸습니다.";
        }

        return "비밀번호가 일치합니다.";
    }

    //게시글 수정
    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto){
        return postService.update(id, dto);
    }


    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }
}
