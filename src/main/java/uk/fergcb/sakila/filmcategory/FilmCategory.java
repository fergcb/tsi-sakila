package uk.fergcb.sakila.filmcategory;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryKey filmCategoryKey;

    public FilmCategory() {}

    public FilmCategory(Integer filmId, Integer categoryId) {
        this.filmCategoryKey = new FilmCategoryKey(filmId, categoryId);
    }

    public FilmCategoryKey getFilmCategoryKey() {
        return filmCategoryKey;
    }

    public void setFilmCategoryKey(FilmCategoryKey filmCategoryKey) {
        this.filmCategoryKey = filmCategoryKey;
    }
}
