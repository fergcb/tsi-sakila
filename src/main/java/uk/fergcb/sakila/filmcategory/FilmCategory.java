package uk.fergcb.sakila.filmcategory;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryKey filmKey;

    public FilmCategory() {}

    public FilmCategory(Integer filmId, Integer categoryId) {
        this.filmKey = new FilmCategoryKey(filmId, categoryId);
    }

    public FilmCategoryKey getFilmKey() {
        return filmKey;
    }

    public void setFilmKey(FilmCategoryKey filmKey) {
        this.filmKey = filmKey;
    }
}
