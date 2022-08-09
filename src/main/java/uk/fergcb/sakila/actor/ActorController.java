package uk.fergcb.sakila.actor;

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
    public @ResponseBody Iterable<Actor> getActors(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return actorService.readActorsByName(name);
        }
        return actorService.readActors();
    }

    @PostMapping
    public ResponseEntity<Void> createActor(@RequestBody ActorDTO actorDTO) {
        actorService.createActor(actorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getActorById(@PathVariable int id) {
        return actorService.readActor(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateActorById(@PathVariable int id, @RequestBody ActorDTO actorDTO) {
        actorService.updateActor(id, actorDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActorById(@PathVariable int id) {
        actorService.deleteActor(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
