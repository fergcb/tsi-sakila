package uk.fergcb.sakila.actor;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
    List<Actor> findByFullNameContainingIgnoreCase(String fullName);
}
