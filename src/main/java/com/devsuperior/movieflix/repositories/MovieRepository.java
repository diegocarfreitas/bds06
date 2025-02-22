package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT obj FROM Movie obj " +
            "WHERE (:genre IS NULL OR obj.genre = :genre) " +
            "ORDER BY obj.title ASC")
    Page<Movie> find(Genre genre, Pageable pageable);

    @Query("SELECT DISTINCT obj FROM Movie obj " +
            "WHERE (:genre IS NULL OR obj.genre = :genre) " +
            "ORDER BY obj.title ASC")
    List<Movie> findMovies(Genre genre, Pageable pageable);

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj IN :movies")
    List<Movie> findMoviesWithGenres(List<Movie> movies);

    @Query("SELECT obj FROM Movie obj INNER JOIN obj.reviews WHERE obj.id = :id")
    Optional<Movie> findMovieWithGenreAndReviews(Long id);
}
