package uk.fergcb.sakila.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private final ActorRepository actorRepository;

    public ActorController (ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping("/")
    public @ResponseBody Iterable<Actor> list() {
        return actorRepository.findAll();
    }
}
