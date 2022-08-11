package uk.fergcb.sakila.resources.filmactor;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FilmActorRepository extends CrudRepository<FilmActor, FilmActorKey> {
    Set<FilmActor> findByFilmActorKeyFilmId(Integer filmId);
}
