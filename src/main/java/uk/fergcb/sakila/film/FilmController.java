package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.fergcb.sakila.filmactor.FilmActor;
import uk.fergcb.sakila.filmactor.FilmActorRepository;
import uk.fergcb.sakila.filmcategory.FilmCategory;
import uk.fergcb.sakila.filmcategory.FilmCategoryRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final FilmCategoryRepository filmCategoryRepository;

    @Autowired
    private final FilmActorRepository filmActorRepository;

    public FilmController(FilmRepository filmRepository, FilmCategoryRepository filmCategoryRepository, FilmActorRepository filmActorRepository) {
        this.filmRepository = filmRepository;
        this.filmCategoryRepository = filmCategoryRepository;
        this.filmActorRepository = filmActorRepository;
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

        List<FilmActor> filmActors = filmDTO.getActorIds()
                .stream()
                .map(actorId ->  new FilmActor(filmId, actorId))
                .toList();

        filmActorRepository.saveAll(filmActors);

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

        // Delete unwanted film_actor records
        Set<Integer> actorIds = filmDTO.getActorIds();
        Set<FilmActor> existingActorLinks = filmActorRepository.findByFilmActorKeyFilmId(id);

        existingActorLinks.stream()
                .filter(filmActor -> !actorIds.contains(filmActor.getFilmActorKey().getActorId()))
                .forEach(filmActorRepository::delete);

        // Create new film_actor records
        List<Integer> linkedActors = existingActorLinks.stream()
                .map(filmActor -> filmActor.getFilmActorKey().getActorId())
                .toList();

        actorIds.stream()
                .filter(actorId -> !linkedActors.contains(actorId))
                .map(actorId -> new FilmActor(id, actorId))
                .forEach(filmActorRepository::save);


        // Delete unwanted film_category records
        Set<Integer> categoryIds = filmDTO.getCategoryIds();
        Set<FilmCategory> existingCategoryLinks = filmCategoryRepository.findByFilmCategoryKeyFilmId(id);

        existingCategoryLinks.stream()
                .filter(filmCategory -> !actorIds.contains(filmCategory.getFilmCategoryKey().getCategoryId()))
                .forEach(filmCategoryRepository::delete);

        // Create new film_category records
        List<Integer> linkedCategories = existingCategoryLinks.stream()
                .map(filmCategory -> filmCategory.getFilmCategoryKey().getCategoryId())
                .toList();

        categoryIds.stream()
                .filter(categoryId -> !linkedCategories.contains(categoryId))
                .map(categoryId -> new FilmCategory(id, categoryId))
                .forEach(filmCategoryRepository::save);

        return filmRepository.save(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable int id) {
        filmRepository.deleteById(id);
    }
}
