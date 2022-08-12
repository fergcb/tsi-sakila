package uk.fergcb.sakila.resources.filmreview;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.hateoas.HateoasCollection;
import uk.fergcb.sakila.resources.film.Film;
import uk.fergcb.sakila.resources.film.FilmController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FilmReviewCollection extends HateoasCollection<FilmReview> {

    protected final Integer filmId;

    public FilmReviewCollection(Iterable<FilmReview> resources, Integer filmId) {
        super("reviews", resources);
        this.filmId = filmId;
    }

    public FilmReviewCollection(Iterable<FilmReview> resources) {
        this(resources, null);
    }

    @Override
    protected Collection<Link> getLinks() {
        if (filmId != null) {
            return List.of(
                    linkTo(methodOn(FilmReviewController.class).getFilmReviews(filmId)).withSelfRel().expand(),
                    linkTo(methodOn(FilmReviewController.class).getReviewOfFilm(filmId, null)).withRel("find")
            );
        } else {
            return List.of(
                    linkTo(methodOn(FilmReviewController.class).getReview(null)).withRel("find"),
                    linkTo(methodOn(FilmReviewController.class).getAllReviews()).withSelfRel().expand()
            );
        }
    }
}
