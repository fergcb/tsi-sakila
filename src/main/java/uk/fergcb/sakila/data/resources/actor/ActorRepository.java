package uk.fergcb.sakila.data.resources.actor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Integer> {
    Page<Actor> findByFullNameContainingIgnoreCase(String fullName, Pageable pageable);
}
