package uk.fergcb.sakila.data;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.fergcb.sakila.data.resources.actor.ActorController;
import uk.fergcb.sakila.data.resources.film.FilmController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OptionsController {

    private static class Options extends RepresentationModel<Options> {}

    @RequestMapping(
        method = {
            RequestMethod.OPTIONS,
            RequestMethod.GET
        }
    )
    public Options getOptions() {
        Options options = new Options();

        options.add(linkTo(methodOn(ActorController.class).getActors(null, null, null)).withRel("actors").expand());
        options.add(linkTo(methodOn(FilmController.class).getFilms(null, null)).withRel("films").expand());

        return options;
    }
}
