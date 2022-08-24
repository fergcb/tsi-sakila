package uk.fergcb.sakila.data.resources.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.util.Strings.isEmpty;

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
    public @ResponseBody FilmCollection getFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        final Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 20
        );

        if (isEmpty(title)) {
            return filmService.readFilms(pageable);
        }

        return filmService.readFilmsByTitle(title, pageable);
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
    public ResponseEntity<Void> deleteFilmById(@PathVariable Integer id) {
        filmService.deleteFilm(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
