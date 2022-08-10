package uk.fergcb.sakila.actor;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.film.FilmController;
import uk.fergcb.sakila.hateoas.HateoasCollection;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ActorCollection extends HateoasCollection<Actor> {

    public ActorCollection(Iterable<Actor> resources) {
        super("actors", resources);
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(ActorController.class).getActors(null)).withSelfRel().expand(),
                linkTo(methodOn(ActorController.class).getActorById(null)).withRel("find")
        );
    }
}
