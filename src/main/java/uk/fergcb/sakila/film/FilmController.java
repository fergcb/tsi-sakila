package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    IFilmService filmService;

    @PostMapping
    public void createFilm(@RequestBody FilmDTO filmDTO) {
        filmService.createFilm(filmDTO);
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
    public void updateActorById(@PathVariable int id, @RequestBody FilmDTO filmDTO) {
        filmService.updateFilm(id, filmDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable int id) {
        filmService.deleteFilm(id);
    }
}
