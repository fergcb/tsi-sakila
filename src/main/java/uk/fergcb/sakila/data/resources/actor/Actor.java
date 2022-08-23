package uk.fergcb.sakila.data.resources.actor;

import org.hibernate.annotations.Formula;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasResource;
import uk.fergcb.sakila.data.resources.film.PartialFilm;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name="actor")
public class Actor extends HateoasResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    protected Integer actorId;

    @Column(name="first_name")
    protected String firstName;

    @Column(name="last_name")
    protected String lastName;

    @Formula("concat(first_name, ' ', last_name)")
    protected String fullName;

    @ManyToMany
    @JoinTable(name="film_actor",
        joinColumns = @JoinColumn(name="actor_id"),
        inverseJoinColumns = @JoinColumn(name="film_id")
    )
    protected List<PartialFilm> films;

    public Actor(ActorDTO actorDTO) {
        this.updateFromDTO(actorDTO);
    }

    public Actor() {}

    public void updateFromDTO(ActorDTO actorDTO) {
        this.setFirstName(actorDTO.getFirstName().orElse(firstName));
        this.setLastName(actorDTO.getLastName().orElse(lastName));
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
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

    public List<PartialFilm> getFilms() {
        return films;
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(ActorController.class).getActorById(getActorId())).withSelfRel()
        );
    }
}
