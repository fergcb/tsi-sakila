package uk.fergcb.sakila.filmcategory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FilmCategoryKey implements Serializable {
    @Column(name="film_id", nullable = false)
    private Integer filmId;

    @Column(name="category_id", nullable = false)
    private Integer categoryId;

    public FilmCategoryKey(Integer filmId, Integer categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }

    public FilmCategoryKey() {}

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
