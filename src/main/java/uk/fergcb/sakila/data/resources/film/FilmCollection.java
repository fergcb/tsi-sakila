package uk.fergcb.sakila.data.resources.film;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasCollection;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FilmCollection extends HateoasCollection<Film> {

    public FilmCollection(Iterable<Film> resources) {
        super("films", resources);
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(FilmController.class).getFilms()).withSelfRel().expand(),
                linkTo(methodOn(FilmController.class).getFilmById(null)).withRel("find")
        );
    }
}
