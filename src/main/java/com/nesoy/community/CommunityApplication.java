package com.nesoy.community;

import com.nesoy.community.domain.Board;
import com.nesoy.community.domain.enums.BoardType;
import com.nesoy.community.domain.User;
import com.nesoy.community.repository.BoardRepository;
import com.nesoy.community.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

	@Bean
	// Application 구동 후 CommandLineRunner로 테스트용 데이터를 DB에 넣어보겠습니다.
	// Application 구동 후 특정 코드를 실행시키고 싶을 때 직접 구현하는 인터페이스.
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
		return (args -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build()
			);

			IntStream.rangeClosed(1, 200).forEach(index ->{
				boardRepository.save(Board.builder()
						.title("게시글"+index)
						.subTitle("순서"+index)
						.content("콘텐츠")
						.boardType(BoardType.free)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.user(user)
						.build()
				);
			});
		});
	}
}
