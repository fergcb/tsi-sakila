package uk.fergcb.sakila.data.resources.actor;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.PagedCollection;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ActorCollection extends PagedCollection<Actor> {

    public ActorCollection(Page<Actor> page) {
        super("actors", page);
    }

    @Override
    protected Link getPreviousLink() {
        return linkTo(methodOn(ActorController.class).getActors(null, page.getNumber() - 1, page.getSize())).withRel("previous").expand();
    }

    @Override
    protected Link getNextLink() {
        return linkTo(methodOn(ActorController.class).getActors(null, page.getNumber() + 1, page.getSize())).withRel("next").expand();
    }

    @Override
    protected Collection<Link> getCollectionLinks() {
        return List.of(
                linkTo(methodOn(ActorController.class).getActors(null, page.getNumber(), page.getSize())).withSelfRel().expand(),
                linkTo(methodOn(ActorController.class).getActorById(null)).withRel("find")
        );
    }
}
