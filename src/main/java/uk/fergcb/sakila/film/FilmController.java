package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.fergcb.sakila.actor.Actor;
import uk.fergcb.sakila.actor.ActorDTO;
import uk.fergcb.sakila.language.LanguageRepository;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final LanguageRepository languageRepository;

    public FilmController(FilmRepository filmRepository, LanguageRepository languageRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
    }

    // TODO: Add Create/Update actions for Film collection
    @PostMapping
    public @ResponseBody Film createFilm(@RequestBody FilmDTO filmDTO) {
        Film film = new Film(filmDTO, languageRepository);
        return filmRepository.save(film);
    }

    @GetMapping
    public @ResponseBody Iterable<Film> getFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable int id) {
        return filmRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No film exists with that id."));
    }

    @PatchMapping("/{id}")
    public @ResponseBody Film updateActorById(@PathVariable int id, @RequestBody FilmDTO filmDTO) {
        Film film = filmRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No film exists with that id."));
        film.updateFromDTO(filmDTO, languageRepository);
        return filmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable int id) {
        filmRepository.deleteById(id);
    }
}
