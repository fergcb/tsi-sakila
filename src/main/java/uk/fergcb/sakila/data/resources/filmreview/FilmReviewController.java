package uk.fergcb.sakila.data.resources.filmreview;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmReviewController {

    private final FilmReviewService filmReviewService;

    public FilmReviewController(FilmReviewService filmReviewService) {
        this.filmReviewService = filmReviewService;
    }

    @GetMapping("/reviews")
    public FilmReviewCollection getAllReviews(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        final Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 20
        );

        return new FilmReviewCollection(filmReviewService.readReviews(pageable));
    }

    @GetMapping("/reviews/{reviewId}")
    public FilmReview getReview(@PathVariable Integer reviewId) {
        return filmReviewService.readFilmReviewById(reviewId);
    }

    @GetMapping("/films/{filmId}/reviews")
    public FilmReviewCollection getFilmReviews(
            @PathVariable Integer filmId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        final Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 20
        );

        return new FilmReviewCollection(filmReviewService.readReviewsForFilm(filmId, pageable), filmId);
    }

    @GetMapping("/films/{filmId}/reviews/{reviewId}")
    public FilmReview getReviewOfFilm(@PathVariable Integer filmId, @PathVariable Integer reviewId) {
        return filmReviewService.readFilmReviewById(reviewId);
    }

}
