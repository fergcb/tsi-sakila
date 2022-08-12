package uk.fergcb.sakila.resources.filmreview;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmReviewController {

    private final FilmReviewService filmReviewService;

    public FilmReviewController(FilmReviewService filmReviewService) {
        this.filmReviewService = filmReviewService;
    }

    @GetMapping("/films/{filmId}/reviews")
    public FilmReviewCollection getFilmReviews(@PathVariable Integer filmId) {
        return new FilmReviewCollection(filmReviewService.readReviewsForFilm(filmId), filmId);
    }

    @GetMapping("/reviews")
    public FilmReviewCollection getAllReviews() {
        return new FilmReviewCollection(filmReviewService.readReviews());
    }

    @GetMapping("/films/{filmId}/reviews/{reviewId}")
    public FilmReview getReviewOfFilm(@PathVariable Integer filmId, @PathVariable Integer reviewId) {
        return filmReviewService.readFilmReviewById(reviewId);
    }

    @GetMapping("/{reviewId}")
    public FilmReview getReview(@PathVariable Integer reviewId) {
        return filmReviewService.readFilmReviewById(reviewId);
    }

}
