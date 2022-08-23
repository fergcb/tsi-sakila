package uk.fergcb.sakila.data.resources.actor;

import org.hibernate.annotations.Formula;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasResource;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name = "actor")
public class PartialActor extends HateoasResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Integer actorId;

    @Transient
    @Column(name="first_name")
    private String firstName;

    @Transient
    @Column(name="last_name")
    private String lastName;

    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(ActorController.class).getActorById(getActorId())).withSelfRel()
        );
    }
}
