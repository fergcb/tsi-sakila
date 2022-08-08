package uk.fergcb.sakila.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private final ActorService actorService;

    public ActorController (ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public @ResponseBody Iterable<Actor> getActors(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return actorService.readActorsByName(name);
        }
        return actorService.readActors();
    }

    @PostMapping
    public void createActor(@RequestBody ActorDTO actorDTO) {
        actorService.createActor(actorDTO);
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getActorById(@PathVariable int id) {
        return actorService.readActor(id);
    }

    @PatchMapping("/{id}")
    public void updateActorById(@PathVariable int id, @RequestBody ActorDTO actorDTO) {
        actorService.updateActor(id, actorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteActorById(@PathVariable int id) {
        actorService.deleteActor(id);
    }
}
