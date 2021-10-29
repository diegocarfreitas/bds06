package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieWithGenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<MovieDTO> findAllPaged(Long genreId, Pageable pageable) {
        Genre genre = genreId == 0 ? null : genreRepository.getOne(genreId);
        Page<Movie> pages = repository.find(genre, pageable);
        return pages.map(page -> new MovieDTO(page));
    }

    @Transactional(readOnly = true)
    public MovieWithGenreDTO findById(Long id) {
        Optional<Movie> optional = repository.findById(id);
        Movie movie = optional.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
        return new MovieWithGenreDTO(movie, movie.getGenre());
    }

    @Transactional(readOnly = true)
    public MovieWithGenreDTO findMovieWithGenreAndReviews(Long id) {
        Optional<Movie> optional = repository.findById(id);
        Movie movie = optional.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
        return new MovieWithGenreDTO(movie, movie.getGenre());
    }
}
