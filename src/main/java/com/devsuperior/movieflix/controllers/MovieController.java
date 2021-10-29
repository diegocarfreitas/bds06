package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieWithGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAllPaged(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> pagesDTO = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok(pagesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieWithGenreDTO> findById(@PathVariable Long id) {
        MovieWithGenreDTO movieWithGenreDTO = service.findById(id);
        return ResponseEntity.ok(movieWithGenreDTO);
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsMovie(@PathVariable Long movieId) {
        List<ReviewDTO> reviewsDTO = reviewService.findReviewsMovie(movieId);
        return ResponseEntity.ok(reviewsDTO);
    }
}
