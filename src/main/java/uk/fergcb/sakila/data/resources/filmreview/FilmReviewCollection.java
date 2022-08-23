package uk.fergcb.sakila.data.resources.filmreview;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasCollection;
import uk.fergcb.sakila.data.hateoas.PagedCollection;
import uk.fergcb.sakila.data.resources.actor.ActorController;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FilmReviewCollection extends PagedCollection<FilmReview> {

    protected final Integer filmId;

    public FilmReviewCollection(Page<FilmReview> page, Integer filmId) {
        super("reviews", page);
        this.filmId = filmId;
    }

    public FilmReviewCollection(Page<FilmReview> page) {
        this(page, null);
    }

    @Override
    protected Link getPreviousLink() {
        return linkTo(methodOn(FilmReviewController.class).getFilmReviews(null, page.getNumber() - 1, page.getSize())).withRel("previous").expand();
    }

    @Override
    protected Link getNextLink() {
        return linkTo(methodOn(FilmReviewController.class).getFilmReviews(null, page.getNumber() + 1, page.getSize())).withRel("next").expand();
    }

    @Override
    protected Collection<Link> getCollectionLinks() {
        if (filmId != null) {
            return List.of(
                    linkTo(methodOn(FilmReviewController.class).getFilmReviews(filmId, page.getNumber(), page.getSize())).withSelfRel().expand(),
                    linkTo(methodOn(FilmReviewController.class).getReviewOfFilm(filmId, null)).withRel("find")
            );
        } else {
            return List.of(
                    linkTo(methodOn(FilmReviewController.class).getReview(null)).withRel("find"),
                    linkTo(methodOn(FilmReviewController.class).getAllReviews(null, null)).withSelfRel().expand()
            );
        }
    }
}
