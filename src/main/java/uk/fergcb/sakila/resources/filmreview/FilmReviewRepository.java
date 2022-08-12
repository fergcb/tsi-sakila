package uk.fergcb.sakila.resources.filmreview;

import org.springframework.data.repository.CrudRepository;

public interface FilmReviewRepository extends CrudRepository<FilmReview, Integer> {
    Iterable<FilmReview> findAllByFilmId(Integer filmId);
}
