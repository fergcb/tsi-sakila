package uk.fergcb.sakila.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.fergcb.sakila.filmactor.FilmActor;
import uk.fergcb.sakila.filmactor.FilmActorRepository;
import uk.fergcb.sakila.filmcategory.FilmCategory;
import uk.fergcb.sakila.filmcategory.FilmCategoryRepository;

import java.util.List;
import java.util.Set;

@Service
public class FilmService implements IFilmService {

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    private final FilmCategoryRepository filmCategoryRepository;

    @Autowired
    private final FilmActorRepository filmActorRepository;

    public FilmService(FilmRepository filmRepository, FilmCategoryRepository filmCategoryRepository, FilmActorRepository filmActorRepository) {
        this.filmRepository = filmRepository;
        this.filmCategoryRepository = filmCategoryRepository;
        this.filmActorRepository = filmActorRepository;
    }

    /**
     * Read all Film resources from the collection
     * @return an iterable collection of Films
     */
    @Override
    public Iterable<Film> readFilms() {
        return filmRepository.findAll();
    }

    /**
     * Read a single Film from the collection, if it exists
     * @param id The unique ID of the Film resource to read
     * @return The Film entity, or an empty Optional
     */
    @Override
    public Film readFilm(Integer id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No film exists with that id."));
    }

    /**
     * Create a new Film resource from a Data Transfer Object
     * @param filmDTO the data to create the Film with
     */
    @Override
    public void createFilm(FilmDTO filmDTO) {
        Film film = filmRepository.save(new Film(filmDTO));
        Integer filmId = film.getFilmId();

        // Create link table entries for categories and actors
        createFilmCategories(filmId, filmDTO.getCategoryIds());
        createFilmActors(filmId, filmDTO.getActorIds());
    }

    /**
     * Create link table records to represent film/category relationships
     * @param filmId The ID of a film belonging to a category
     * @param categoryIds The IDs of the categories the film belongs to
     */
    private void createFilmCategories(Integer filmId, Set<Integer> categoryIds) {
        if (categoryIds == null) return;

        // Create the FilmCategory entities
        List<FilmCategory> filmCategories = categoryIds
                .stream()
                .map(categoryId ->  new FilmCategory(filmId, categoryId))
                .toList();

        // Persist the entities
        filmCategoryRepository.saveAll(filmCategories);
    }

    /**
     * Create link table records to represent film/actor relationships
     * @param filmId The ID of a film starring the given actors
     * @param actorIds The IDs of the actors in the film
     */
    private void createFilmActors(Integer filmId, Set<Integer> actorIds) {
        if (actorIds == null) return;

        // Create the FilmActor entities
        List<FilmActor> filmActors = actorIds
                .stream()
                .map(actorId ->  new FilmActor(filmId, actorId))
                .toList();

        // Persist the entities
        filmActorRepository.saveAll(filmActors);
    }

    /**
     * Update an existing Film resource given a partial DTO
     * @param id The ID of the Film resource to update
     * @param filmDTO The data to overwrite the Film with
     */
    @Override
    public void updateFilm(Integer id, FilmDTO filmDTO) {
        Film film = filmRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No film exists with that id."));

        film.updateFromDTO(filmDTO);

        // Delete & create link table records to reflect changes in film category/cast
        updateFilmCategories(id, filmDTO.getCategoryIds());
        updateFilmActors(id, filmDTO.getActorIds());

        filmRepository.save(film);
    }

    /**
     * Create/delete FilmCategory link table records to reflect updates to a Film's Categories
     * @param filmId The ID of the Film resource being updated
     * @param categoryIds The set of IDs of Categories that the Film should belong to
     */
    private void updateFilmCategories(Integer filmId, Set<Integer> categoryIds) {
        if (categoryIds == null) return;

        Set<FilmCategory> existingCategoryLinks = filmCategoryRepository.findByFilmCategoryKeyFilmId(filmId);

        deleteUnwantedFilmCategories(categoryIds, existingCategoryLinks);
        createNewFilmCategories(filmId, categoryIds, existingCategoryLinks);
    }

    /**
     * Delete link table records linking a Film to a Category outside the set of Catgegories it should belong to
     * @param categoryIds The set of IDs of Categories that the Film should belong to
     * @param existingCategoryLinks The set of FilmCategory records that exist for this Film
     */
    private void deleteUnwantedFilmCategories(Set<Integer> categoryIds, Set<FilmCategory> existingCategoryLinks) {
        existingCategoryLinks.stream()
                .filter(filmCategory -> !categoryIds.contains(filmCategory.getFilmCategoryKey().getCategoryId()))
                .forEach(filmCategoryRepository::delete);
    }

    /**
     * Create link table records linking a Film to each Category that it should belong to, if they don't already exist
     * @param filmId The ID of the Film being updated
     * @param categoryIds The set of IDs of Categories that the Film should belong to
     * @param existingCategoryLinks The set of FilmCategory records that exist for this Film
     */
    private void createNewFilmCategories(Integer filmId, Set<Integer> categoryIds, Set<FilmCategory> existingCategoryLinks) {
        // Get a list of category IDs that already have FilmCategory records
        List<Integer> linkedCategories = existingCategoryLinks.stream()
                .map(filmCategory -> filmCategory.getFilmCategoryKey().getCategoryId())
                .toList();

        // Create FilmCategory links that don't already exist
        categoryIds.stream()
                .filter(categoryId -> !linkedCategories.contains(categoryId))
                .map(categoryId -> new FilmCategory(filmId, categoryId))
                .forEach(filmCategoryRepository::save);
    }

    /**
     * Create/delete FilmActor link table records to reflect updates to a Film's Actors
     * @param filmId The ID of the Film resource being updated
     * @param actorIds The set of IDs of Actors that should be in the Film's cast
     */
    private void updateFilmActors(Integer filmId, Set<Integer> actorIds) {
        if (actorIds == null) return;

        Set<FilmActor> existingActorLinks = filmActorRepository.findByFilmActorKeyFilmId(filmId);

        deleteUnwantedFilmActors(actorIds, existingActorLinks);
        createNewFilmActors(filmId, actorIds, existingActorLinks);
    }

    /**
     * Delete link table records linking a Film to an Actor outside the set of Actors in its cast
     * @param actorIds The set of IDs of Actors that should be in the Film's cast
     * @param existingActorLinks The set of FilmActor records that exist for this Film
     */
    private void deleteUnwantedFilmActors(Set<Integer> actorIds, Set<FilmActor> existingActorLinks) {
        existingActorLinks.stream()
                .filter(filmActor -> !actorIds.contains(filmActor.getFilmActorKey().getActorId()))
                .forEach(filmActorRepository::delete);
    }

    /**
     * Create link table records linking a Film to each Actor that should be in the cast, if they don't already exist
     * @param filmId The ID of the Film being updated
     * @param actorIds The set of IDs of Actors that should be in the Film's cast
     * @param existingActorLinks The set of FilmActor records that exist for this Film
     */
    private void createNewFilmActors(Integer filmId, Set<Integer> actorIds, Set<FilmActor> existingActorLinks) {
        // Get a list of actor IDs that already have FilmActor records
        List<Integer> linkedActors = existingActorLinks.stream()
                .map(filmActor -> filmActor.getFilmActorKey().getActorId())
                .toList();

        // Create FilmActor links that don't already exist
        actorIds.stream()
                .filter(actorId -> !linkedActors.contains(actorId))
                .map(actorId -> new FilmActor(filmId, actorId))
                .forEach(filmActorRepository::save);
    }

    /**
     * Delete a Film resource
     * @param id The ID of the Film resource to delete
     */
    @Override
    public void deleteFilm(Integer id) {
        filmRepository.deleteById(id);
    }
}
