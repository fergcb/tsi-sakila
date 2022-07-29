package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.fergcb.sakila.filmcategory.FilmCategory;
import uk.fergcb.sakila.filmcategory.FilmCategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private final FilmRepository filmRepository;
    @Autowired
    private final FilmCategoryRepository filmCategoryRepository;

    public FilmController(FilmRepository filmRepository, FilmCategoryRepository filmCategoryRepository) {
        this.filmRepository = filmRepository;
        this.filmCategoryRepository = filmCategoryRepository;
    }


    @PostMapping
    public @ResponseBody Film createFilm(@RequestBody FilmDTO filmDTO) {
        Film film = filmRepository.save(new Film(filmDTO));
        Integer filmId = film.getFilmId();

        List<FilmCategory> filmCategories = filmDTO.getCategoryIds()
                .stream()
                .map(categoryId ->  new FilmCategory(filmId, categoryId))
                .toList();

        filmCategoryRepository.saveAll(filmCategories);

        return film;
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
