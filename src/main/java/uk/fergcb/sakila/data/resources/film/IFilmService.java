package uk.fergcb.sakila.data.resources.film;

import org.springframework.data.domain.Pageable;

public interface IFilmService {
    FilmCollection readFilms(Pageable pageable);
    FilmCollection readFilmsByTitle(String title, Pageable pageable);
    Film readFilm(Integer id);
    Integer createFilm(FilmDTO filmDTO);
    void updateFilm(Integer id, FilmDTO filmDTO);
    void deleteFilm(Integer id);
}
