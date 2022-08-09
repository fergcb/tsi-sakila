package uk.fergcb.sakila.film;

public interface IFilmService {
    Iterable<Film> readFilms();
    Film readFilm(Integer id);
    Integer createFilm(FilmDTO filmDTO);
    void updateFilm(Integer id, FilmDTO filmDTO);
    void deleteFilm(Integer id);
}
