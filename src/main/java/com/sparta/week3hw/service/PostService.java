package com.sparta.week3hw.service;

import com.sparta.week3hw.domain.Post;
import com.sparta.week3hw.dto.PostRequestDto;
import com.sparta.week3hw.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않는 아이디입니다.")
        );
        post.update(requestDto);
        return post.getId();
    }
}
