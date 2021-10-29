package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

public class MovieWithGenreDTO extends MovieDTO {
    private static final long serialVersionUID = 1L;

    private GenreDTO genre;

    public MovieWithGenreDTO() {
    }

    public MovieWithGenreDTO(Movie entity) {
        super(entity);
    }

    public MovieWithGenreDTO(Movie entity, Genre genre) {
        this(entity);
        this.genre = new GenreDTO(genre);
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }
}
