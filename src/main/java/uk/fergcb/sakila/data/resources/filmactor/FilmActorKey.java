package uk.fergcb.sakila.data.resources.filmactor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FilmActorKey implements Serializable {
    @Column(name="film_id", nullable = false)
    private Integer filmId;

    @Column(name="actor_id", nullable = false)
    private Integer actorId;

    public FilmActorKey(Integer filmId, Integer actorId) {
        this.filmId = filmId;
        this.actorId = actorId;
    }

    public FilmActorKey() {}

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}
