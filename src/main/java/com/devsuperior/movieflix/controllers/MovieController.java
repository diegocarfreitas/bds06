package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAllPaged(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> pagesDTO = service.findAllPaged(genreId, pageable);
        return ResponseEntity.ok(pagesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO moviesDTO = service.findById(id);
        return ResponseEntity.ok(moviesDTO);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<MovieDTO> findMovieWithGenreAndReviews(@PathVariable Long id) {
        MovieDTO moviesDTO = service.findMovieWithGenreAndReviews(id);
        return ResponseEntity.ok(moviesDTO);
    }
}
