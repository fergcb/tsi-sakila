package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @PostMapping
    public @ResponseBody Film createFilm(@RequestBody FilmDTO filmDTO) {
        Film film = new Film(filmDTO);
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
        film.updateFromDTO(filmDTO);
        return filmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable int id) {
        filmRepository.deleteById(id);
    }
}
