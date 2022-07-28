package uk.fergcb.sakila.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private final ActorRepository actorRepository;

    public ActorController (ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Actor> getActors(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return actorRepository.findByFullNameContainingIgnoreCase(name);
        }
        return actorRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Actor createActor(@RequestBody ActorDTO actorDTO) {
        Actor actor = new Actor(actorDTO);
        return actorRepository.save(actor);
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getActorById(@PathVariable int id) {
        return actorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that id."));
    }

    @PatchMapping("/{id}")
    public @ResponseBody Actor updateActorById(@PathVariable int id, @RequestBody ActorDTO actorDTO) {
        Actor actor = actorRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that id."));
        actor.updateFromDTO(actorDTO);
        return actorRepository.save(actor);
    }

    @DeleteMapping("/{id}")
    public void deleteActorById(@PathVariable int id) {
        actorRepository.deleteById(id);
    }
}
