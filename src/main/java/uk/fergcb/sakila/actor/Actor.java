package uk.fergcb.sakila.actor;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private int actorId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;

    @ManyToMany
    @JoinTable(name="film_actor",
        joinColumns = @JoinColumn(name="actor_id"),
        inverseJoinColumns = @JoinColumn(name="film_id")
    )
    private List<ActorFilm> films;

    public Actor(ActorDTO actorDTO) {
        this.updateFromDTO(actorDTO);
    }

    public Actor() {}

    public void updateFromDTO(ActorDTO actorDTO) {
        this.firstName = actorDTO.getFirstName().orElse(firstName);
        this.lastName = actorDTO.getLastName().orElse(lastName);
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public List<ActorFilm> getFilms() {
        return films;
    }
}
