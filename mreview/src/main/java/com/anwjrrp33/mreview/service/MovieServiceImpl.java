package com.anwjrrp33.mreview.service;

import com.anwjrrp33.mreview.dto.MovieDTO;
import com.anwjrrp33.mreview.entity.Movie;
import com.anwjrrp33.mreview.entity.MovieImage;
import com.anwjrrp33.mreview.repository.MovieImageRepository;
import com.anwjrrp33.mreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}
