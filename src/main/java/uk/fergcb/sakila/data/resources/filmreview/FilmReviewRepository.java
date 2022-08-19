package uk.fergcb.sakila.data.resources.filmreview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmReviewRepository extends PagingAndSortingRepository<FilmReview, Integer> {
    Page<FilmReview> findAllByFilmId(Integer filmId, Pageable pageable);
}
