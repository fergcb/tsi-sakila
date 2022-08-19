package uk.fergcb.sakila.data.resources.filmactor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @EmbeddedId
    private FilmActorKey filmActorKey;

    public FilmActor() {}

    public FilmActor(Integer filmId, Integer actorId) {
        this.filmActorKey = new FilmActorKey(filmId, actorId);
    }

    public FilmActorKey getFilmActorKey() {
        return filmActorKey;
    }

    public void setFilmActorKey(FilmActorKey filmKey) {
        this.filmActorKey = filmKey;
    }
}