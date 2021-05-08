package com.anwjrrp33.bimovie.respository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.anwjrrp33.bimovie.entity.Movie;
import com.anwjrrp33.bimovie.entity.Poster;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void testInsert() {
		log.info("testInsert................");

		Movie movie = Movie.builder().title("극한직업").build();
		movie.addPoster(Poster.builder().fname("극한직업포스터1.jpg").build());
		movie.addPoster(Poster.builder().fname("극한직업포스터2.jpg").build());

		movieRepository.save(movie);

		log.info(movie.getMno());
	}

	@Test
	@Transactional
	@Commit
	public void testAddPoster() {
		Movie movie = movieRepository.getOne(1L); // 데이터베이스 존재하는 영화 번호
		movie.addPoster(Poster.builder().fname("극한직업포스터3.jpg").build()); // 새로운 Poster 객체
		movieRepository.save(movie);
	}

	@Test
	@Transactional
	@Commit
	public void testRemovePoster() {
		Movie movie = movieRepository.getOne(1L);
		movie.removePoster(2L);
		movieRepository.save(movie);
	}

	@Test
	public void insertMovies() {
		IntStream.rangeClosed(10, 100).forEach(i -> { // 10부터 100까지 90개
			Movie movie = Movie.builder().title("세계명작" + i).build();

			movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터1.jpg").build());
			movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터2.jpg").build());

			movieRepository.save(movie);
		});
	}

	@Test
	public void testPaging1() {
		// 문제가 발생했다.. limit가 없다.
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<Movie> result = movieRepository.findAll2(pageable);
		result.getContent().forEach(m -> {
			log.info(m.getMno());
			log.info(m.getTitle());
			log.info(m.getPosterList().size());
			log.info("------------------------");
		});
	}

	@Test
	public void testPaging3All() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<Object[]> result = movieRepository.findAll3(pageable);

		result.getContent().forEach(arr -> {
			log.info(Arrays.toString(arr));
			log.info("---------------------");
		});
	}
}