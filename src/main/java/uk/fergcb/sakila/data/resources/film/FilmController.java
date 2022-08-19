package uk.fergcb.sakila.data.resources.film;

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
    public ResponseEntity<Film> createFilm(@RequestBody FilmDTO filmDTO) {
        Integer id = filmService.createFilm(filmDTO);
        Film film = filmService.readFilm(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }

    @GetMapping
    public @ResponseBody FilmCollection getFilms() {
        return filmService.readFilms();
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable Integer id) {
        return filmService.readFilm(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Film> updateFilmById(@PathVariable Integer id, @RequestBody FilmDTO filmDTO) {
        filmService.updateFilm(id, filmDTO);
        Film film = filmService.readFilm(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(film);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Film> deleteFilmById(@PathVariable Integer id) {
        filmService.deleteFilm(id);
        Film film = filmService.readFilm(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(film);
    }
}
