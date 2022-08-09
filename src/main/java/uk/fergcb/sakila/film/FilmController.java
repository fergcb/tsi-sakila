package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    IFilmService filmService;

    @PostMapping
    public ResponseEntity<Void> createFilm(@RequestBody FilmDTO filmDTO) {
        filmService.createFilm(filmDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public @ResponseBody Iterable<Film> getFilms() {
        return filmService.readFilms();
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable int id) {
        return filmService.readFilm(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFilmById(@PathVariable int id, @RequestBody FilmDTO filmDTO) {
        filmService.updateFilm(id, filmDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmById(@PathVariable int id) {
        filmService.deleteFilm(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
