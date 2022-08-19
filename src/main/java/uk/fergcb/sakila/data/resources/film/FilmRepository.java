package uk.fergcb.sakila.data.resources.film;

import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
