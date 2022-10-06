package com.sparta.week3hw;

import com.sparta.week3hw.domain.Post;
import com.sparta.week3hw.repository.PostRepository;
import com.sparta.week3hw.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week3hwApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week3hwApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PostRepository postRepository, PostService postService) {
		return (args) -> {
			postRepository.save(new Post("홍길동", "게시판 글 써보자", "1555", "게시판에 글 써지나 테스트"));
			postRepository.save(new Post("전우치", "제목", "1234", "테스트!"));

			System.out.println("데이터 인쇄");
			List<Post> postList = postRepository.findAll();
			for (int i = 0; i < postList.size(); i++) {
				Post post = postList.get(i);
				System.out.println(post.getId());
				System.out.println(post.getUsername());
				System.out.println(post.getTitle());
				System.out.println(post.getPassword());
				System.out.println(post.getContents());

			}

		};

	}
}
