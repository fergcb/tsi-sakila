package uk.fergcb.sakila.film;

import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
