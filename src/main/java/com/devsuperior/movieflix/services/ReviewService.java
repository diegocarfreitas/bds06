package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('MEMBER')")
    @Transactional
    public ReviewDTO insertUserAuthenticated(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setText(reviewDTO.getText());
        review.setMovie(new Movie(reviewDTO.getMovieId()));

        UserDTO userDTO = userService.authenticated();
        review.setUser(new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail()));

        review = repository.save(review);
        return new ReviewDTO(review, review.getUser());
    }
}
