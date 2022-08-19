package uk.fergcb.sakila.data.resources.film;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends PagingAndSortingRepository<Film, Integer> {}
