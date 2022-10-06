package com.sparta.week3hw.controller;

import com.sparta.week3hw.domain.Post;
import com.sparta.week3hw.dto.PostRequestDto;
import com.sparta.week3hw.repository.PostRepository;
import com.sparta.week3hw.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getPostList(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable Long id){
        return postRepository.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        Optional<Post> post = postRepository.findById(id);
        // 패스워드를 비교하여 일치하면 삭제가능, 불일치하면 오류메시지 : 구글 검색을 통해 구현 코드를 알아냄.
        if (post.isPresent()) {
            if (post.get().getPassword().equals(requestDto.getPassword())) {
                postRepository.deleteById(id);
            } else {
                System.out.println("비밀번호가 맞지 않습니다.");
                id = (long)0;
            }
        }
        return id;
    }

    @PutMapping("posts/{id}")
    public  Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        Optional<Post> post = postRepository.findById(id);
        // 패스워드를 비교하여 일치하면 수정가능, 불일치하면 오류메시지 : 구글 검색을 통해 구현 코드를 알아냄.
        if (post.isPresent()) {
            if (post.get().getPassword().equals(requestDto.getPassword())) {
                postService.update(id, requestDto);
            } else {
                System.out.println("비밀번호가 맞지 않습니다.");
                id = (long)0;
            }
        }
        return id;
    }
}
