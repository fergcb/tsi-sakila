package uk.fergcb.sakila.data.resources.film;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.PagedCollection;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FilmCollection extends PagedCollection<Film> {

    public FilmCollection(Page<Film> page) {
        super("films", page);
    }

    @Override
    protected Link getPreviousLink() {
        return linkTo(methodOn(FilmController.class).getFilms(getPageNumber() - 1, getPageSize())).withRel("previous").expand();
    }

    @Override
    protected Link getNextLink() {
        return linkTo(methodOn(FilmController.class).getFilms(getPageNumber() + 1, getPageSize())).withRel("next").expand();
    }

    @Override
    protected Collection<Link> getCollectionLinks() {
        return List.of(
                linkTo(methodOn(FilmController.class).getFilms(getPageNumber(), getPageSize())).withSelfRel().expand(),
                linkTo(methodOn(FilmController.class).getFilmById(null)).withRel("find")
        );
    }
}
