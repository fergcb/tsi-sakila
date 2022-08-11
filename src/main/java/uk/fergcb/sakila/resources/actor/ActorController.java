package uk.fergcb.sakila.resources.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ActorCollection getActors(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return actorService.readActorsByName(name);
        }
        return actorService.readActors();
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody ActorDTO actorDTO) {
        Integer id = actorService.createActor(actorDTO);
        Actor actor = actorService.readActor(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(actor);
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getActorById(@PathVariable Integer id) {
        return actorService.readActor(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Actor> updateActorById(@PathVariable Integer id, @RequestBody ActorDTO actorDTO) {
        actorService.updateActor(id, actorDTO);
        Actor actor = actorService.readActor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> deleteActorById(@PathVariable Integer id) {
        actorService.deleteActor(id);
        Actor actor = actorService.readActor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(actor);
    }
}
